package urlFetcher.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import urlFetcher.models.FetchStatus;
import urlFetcher.models.UrlContentResponse;
import urlFetcher.models.UrlFetchTask;
import urlFetcher.models.UrlStatusResponse;
import urlFetcher.repositories.UrlFetchRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UrlFetchService {
    private final UrlFetchRepository repository;
    private final HttpFetchService fetchService;

    public void submitUrls(List<String> urls) {
        urls.forEach(url -> {
            UrlFetchTask task = new UrlFetchTask(url);
            repository.save(task);
            fetchService.fetch(task);
        });
    }

    public List<UrlStatusResponse> getStatuses() {
        return repository.findAll().stream()
                .map(t -> new UrlStatusResponse(t.getUrl(), t.getStatus()))
                .toList();
    }

    public UrlContentResponse getContent(String url) {
        UrlFetchTask task = repository.findByUrl(url)
                .orElseThrow(() -> new RuntimeException("URL not found"));

        if (task.getStatus() != FetchStatus.SUCCESS) {
            throw new RuntimeException("Content not ready");
        }

        return new UrlContentResponse(task.getUrl(), task.getContent());
    }
}
