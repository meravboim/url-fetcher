package urlFetcher.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
@Getter
public class UrlFetchTask {
    private final String url;
    private FetchStatus status;
    private String content;
    private String error;
}
