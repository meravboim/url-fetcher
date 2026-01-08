package urlFetcher.dto;

import urlFetcher.model.UrlResource;

public record UrlContentResponse(String url, String content) {
    public static UrlContentResponse fromUrlResource(UrlResource resource) {
        String content = (resource.getContent() != null) ? resource.getContent() : resource.getError();

        return new UrlContentResponse(resource.getUrl(), content);
    }
}