package urlFetcher.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(UnreachableUrlContentException.class)
    public ResponseEntity<String> handleUnreachableUrl(UnreachableUrlContentException ex) {
        log.error(ex.getMessage());

        return ResponseEntity.status(NOT_FOUND).body(ex.getMessage());
    }
}
