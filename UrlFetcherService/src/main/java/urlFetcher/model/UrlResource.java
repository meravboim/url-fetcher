package urlFetcher.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import static urlFetcher.model.FetchStatus.PENDING;

@RequiredArgsConstructor
@Setter
@Getter
public class UrlResource {
    private final String url;
    private FetchStatus status = PENDING;
    private String content;
    private String error;
}
