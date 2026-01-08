package urlFetcher.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import urlFetcher.model.UrlResource;

import static urlFetcher.model.FetchStatus.FAILED;
import static urlFetcher.model.FetchStatus.SUCCESS;

@RequiredArgsConstructor
@Service
public class HttpFetchService {
    private final RestTemplate restTemplate;

    @Async
    public void fetch(UrlResource task) {
        try {
            String body = restTemplate.getForObject(task.getUrl(), String.class);
            task.setContent(body);
            task.setStatus(SUCCESS);
        } catch (Exception e) {
            task.setStatus(FAILED);
            task.setError(e.getMessage());
        }
    }
}
