package urlFetcher.exception;

public class UnreachableUrlContentException extends RuntimeException {
    public UnreachableUrlContentException(String message, String url) {
        super(message + ": " + url);
    }
}