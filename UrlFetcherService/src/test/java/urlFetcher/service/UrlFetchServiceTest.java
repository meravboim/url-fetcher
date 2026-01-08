package urlFetcher.service;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import urlFetcher.dto.UrlContentResponse;
import urlFetcher.dto.UrlStatusResponse;
import urlFetcher.exception.UnreachableUrlContentException;
import urlFetcher.model.UrlResource;
import urlFetcher.repository.UrlResourcesRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static urlFetcher.model.FetchStatus.*;
import static urlFetcher.testUtil.TestData.*;

@ExtendWith(MockitoExtension.class)
class UrlFetchServiceTest {
    @Mock
    UrlResourcesRepository repository;

    @Mock
    HttpFetchService fetchService;

    @InjectMocks
    UrlFetchService urlFetchService;

    @Test
    void givenNullOrEmpty_whenSubmitUrls_thenNoInteractions() {
        urlFetchService.submitUrls(null);
        urlFetchService.submitUrls(List.of());

        verifyNoInteractions(repository);
        verifyNoInteractions(fetchService);
    }

    @Test
    void givenUrls_whenSubmitUrls_thenSavedAndFetched() {
        ArgumentCaptor<UrlResource> captor = ArgumentCaptor.forClass(UrlResource.class);

        urlFetchService.submitUrls(List.of(URL_EXAMPLE));

        verify(repository, times(1)).save(captor.capture());
        UrlResource saved = captor.getValue();
        assertEquals(URL_EXAMPLE, saved.getUrl());
        verify(fetchService, times(1)).fetch(saved);
    }

    @Test
    void givenRepositoryHasResources_whenGetStatuses_thenReturnsMappedList() {
        val successResource = createSuccessResource(URL_OK, CONTENT);
        val failedResource = createFailedResource(URL_BAD, ERROR_MESSAGE);
        when(repository.findAll()).thenReturn(List.of(successResource, failedResource));

        List<UrlStatusResponse> statuses = urlFetchService.getStatuses();

        assertEquals(2, statuses.size());
        assertTrue(statuses.stream().anyMatch(s -> s.url().equals(URL_OK) && s.status() == SUCCESS));
        assertTrue(statuses.stream().anyMatch(s -> s.url().equals(URL_BAD) && s.status() == FAILED));
    }

    @Test
    void givenMissingUrl_whenGetContent_thenThrows() {
        when(repository.findByUrl(URL_EXAMPLE)).thenReturn(Optional.empty());

        assertThrows(UnreachableUrlContentException.class, () -> urlFetchService.getContent(URL_EXAMPLE));
    }

    @Test
    void givenPendingUrl_whenGetContent_thenThrows() {
        val resource = new UrlResource(URL_PENDING);
        resource.setStatus(PENDING);
        when(repository.findByUrl(URL_PENDING)).thenReturn(Optional.of(resource));

        assertThrows(UnreachableUrlContentException.class, () -> urlFetchService.getContent(URL_PENDING));
    }

    @Test
    void givenReadyUrl_whenGetContent_thenReturnsContent() {
        val resource = createSuccessResource(URL_OK, CONTENT);
        when(repository.findByUrl(URL_OK)).thenReturn(Optional.of(resource));

        UrlContentResponse response = urlFetchService.getContent(URL_OK);

        assertEquals(URL_OK, response.url());
        assertEquals(CONTENT, response.content());
    }

    @Test
    void givenFailedUrlWithError_whenGetContent_thenReturnsErrorMessage() {
        val resource = createFailedResource(URL_BAD, ERROR_MESSAGE);
        when(repository.findByUrl(URL_BAD)).thenReturn(Optional.of(resource));

        UrlContentResponse response = urlFetchService.getContent(URL_BAD);

        assertEquals(ERROR_MESSAGE, response.content());
    }
}
