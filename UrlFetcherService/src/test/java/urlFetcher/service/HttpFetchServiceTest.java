package urlFetcher.service;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import urlFetcher.model.UrlResource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import static urlFetcher.model.FetchStatus.FAILED;
import static urlFetcher.model.FetchStatus.SUCCESS;
import static urlFetcher.testUtil.TestData.*;

@ExtendWith(MockitoExtension.class)
class HttpFetchServiceTest {
    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    HttpFetchService fetchService;

    @Test
    void givenValidResponse_whenFetch_thenSetsContentAndSuccess() {
        val resource = new UrlResource(URL_OK);
        when(restTemplate.getForObject(URL_OK, String.class)).thenReturn(CONTENT);

        fetchService.fetch(resource);

        assertEquals(CONTENT, resource.getContent());
        assertEquals(SUCCESS, resource.getStatus());
        assertNull(resource.getError());
    }

    @Test
    void givenRestTemplateThrows_whenFetch_thenSetsErrorAndFailedStatus() {
        val resource = new UrlResource(URL_BAD);
        when(restTemplate.getForObject(URL_BAD, String.class)).thenThrow(new RuntimeException(ERROR_MESSAGE));

        fetchService.fetch(resource);

        assertNull(resource.getContent());
        assertEquals(FAILED, resource.getStatus());
        assertEquals(ERROR_MESSAGE, resource.getError());
    }
}