package urlFetcher.dto;

import urlFetcher.model.FetchStatus;
import urlFetcher.model.UrlResource;

public record UrlStatusResponse(String url, FetchStatus status) {
    public static UrlStatusResponse fromUrlResource(UrlResource resource) {
        return new UrlStatusResponse(resource.getUrl(), resource.getStatus());
    }
}