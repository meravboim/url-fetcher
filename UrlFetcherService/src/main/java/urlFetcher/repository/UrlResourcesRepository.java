package urlFetcher.repository;

import org.springframework.stereotype.Repository;
import urlFetcher.model.UrlResource;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UrlResourcesRepository {
    private final ConcurrentHashMap<String, UrlResource> urlResources = new ConcurrentHashMap<>();

    public void save(UrlResource urlResource) {
        urlResources.put(urlResource.getUrl(), urlResource);
    }

    public Collection<UrlResource> findAll() {
        return urlResources.values();
    }

    public Optional<UrlResource> findByUrl(String url) {
        return Optional.ofNullable(urlResources.get(url));
    }
}
