package urlFetcher.repositories;

import org.springframework.stereotype.Repository;
import urlFetcher.models.UrlFetchTask;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UrlFetchRepository {
    private final ConcurrentHashMap<String, UrlFetchTask> store = new ConcurrentHashMap<>();

    public void save(UrlFetchTask task) {
        store.put(task.getUrl(), task);
    }

    public Collection<UrlFetchTask> findAll() {
        return store.values();
    }

    public Optional<UrlFetchTask> findByUrl(String url) {
        return Optional.ofNullable(store.get(url));
    }
}
