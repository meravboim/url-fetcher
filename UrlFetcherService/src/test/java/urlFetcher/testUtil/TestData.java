package urlFetcher.testUtil;

import urlFetcher.model.UrlResource;

import static urlFetcher.model.FetchStatus.FAILED;
import static urlFetcher.model.FetchStatus.SUCCESS;

public final class TestData {
    public static final String URL_EXAMPLE = "http://example.com";
    public static final String URL_PENDING = "http://wait";
    public static final String URL_OK = "http://ok";
    public static final String URL_BAD = "http://bad";
    public static final String CONTENT = "Im ok";
    public static final String ERROR_MESSAGE = "boom";

    public static UrlResource createSuccessResource(String url, String content) {
        UrlResource resource = new UrlResource(url);
        resource.setStatus(SUCCESS);
        resource.setContent(content);
        return resource;
    }

    public static UrlResource createFailedResource(String url, String error) {
        UrlResource resource = new UrlResource(url);
        resource.setStatus(FAILED);
        resource.setError(error);
        return resource;
    }
}
