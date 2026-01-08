package urlFetcher.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import urlFetcher.model.UrlResource;

import static urlFetcher.model.FetchStatus.FAILED;
import static urlFetcher.model.FetchStatus.SUCCESS;

@RequiredArgsConstructor
@Service
@Slf4j
public class HttpFetchService {
    private final RestTemplate restTemplate;

    @Async
    public void fetch(UrlResource task) {
        try {
            log.debug("Fetching URL: {}", task.getUrl());
            String body = restTemplate.getForObject(task.getUrl(), String.class);
            task.setContent(body);
            task.setStatus(SUCCESS);
            log.info("Successfully fetched url={}", task.getUrl());
        } catch (Exception e) {
            task.setStatus(FAILED);
            task.setError(e.getMessage());
            log.warn("Failed to fetch url={}, error={}", task.getUrl(), e.getMessage());
        }
    }
}
