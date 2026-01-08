package urlFetcher.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import urlFetcher.dto.UrlContentResponse;
import urlFetcher.dto.UrlStatusResponse;
import urlFetcher.dto.UrlSubmissionRequest;
import urlFetcher.service.UrlFetchService;

import java.util.List;

@RequiredArgsConstructor
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