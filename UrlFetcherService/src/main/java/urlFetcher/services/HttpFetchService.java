package urlFetcher.services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import urlFetcher.models.UrlFetchTask;

import static urlFetcher.models.FetchStatus.FAILED;
import static urlFetcher.models.FetchStatus.SUCCESS;

@Service
public class HttpFetchService {
    private final RestTemplate restTemplate = new RestTemplate();

    public HttpFetchService() {
        super();
    }

    @Async
    public void fetch(UrlFetchTask task) {
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

