package urlFetcher.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import urlFetcher.models.UrlContentResponse;
import urlFetcher.models.UrlStatusResponse;
import urlFetcher.models.UrlSubmissionRequest;
import urlFetcher.services.UrlFetchService;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/urls")
@CrossOrigin
public class UrlFetchController {
    private final UrlFetchService urlFetchService;

    @PostMapping
    public void submitUrls(@RequestBody UrlSubmissionRequest request) {
        urlFetchService.submitUrls(request.urls());
    }

    @GetMapping
    public ResponseEntity<List<UrlStatusResponse>> getUrlStatuses() {
        return ResponseEntity.ok(urlFetchService.getStatuses());
    }

    @GetMapping("/content")
    public ResponseEntity<UrlContentResponse> getUrlContent(@RequestParam String url) {
        return ResponseEntity.ok(urlFetchService.getContent(url));
    }
}