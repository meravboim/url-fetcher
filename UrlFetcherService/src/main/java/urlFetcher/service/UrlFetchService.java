package urlFetcher.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import urlFetcher.dto.UrlContentResponse;
import urlFetcher.dto.UrlStatusResponse;
import urlFetcher.exception.UnreachableUrlContentException;
import urlFetcher.model.UrlResource;
import urlFetcher.repository.UrlResourcesRepository;

import java.util.List;

import static urlFetcher.model.FetchStatus.SUCCESS;

@Slf4j
@Service
@RequiredArgsConstructor
public class UrlFetchService {
    private final UrlResourcesRepository urlResourcesRepository;
    private final HttpFetchService fetchService;

    public void submitUrls(List<String> urls) {
        urls.forEach(this::saveAndFetchUrl);
    }

    private void saveAndFetchUrl(String url) {
        try {
            UrlResource urlResource = new UrlResource(url);
            urlResourcesRepository.save(urlResource);
            fetchService.fetch(urlResource);
        } catch (Exception e) {
            log.error("Failed to initialize fetch for URL: {}", url, e);
        }
    }

    public List<UrlStatusResponse> getStatuses() {
        return urlResourcesRepository.findAll().stream().map(UrlStatusResponse::fromUrlResource).toList();
    }

    public UrlContentResponse getContent(String url) {
        UrlResource urlResource = urlResourcesRepository.findByUrl(url)
                .orElseThrow(() -> new UnreachableUrlContentException("URL not found", url));

        validateContentAvailability(urlResource);

        return UrlContentResponse.fromUrlResource(urlResource);
    }

    private void validateContentAvailability(UrlResource resource) {
        if (resource.getStatus() != SUCCESS && resource.getError() == null) {
            throw new UnreachableUrlContentException("Content not available", resource.getUrl());
        }
    }
}
