package com.entity.aggregation.controller;

import com.entity.aggregation.dto.TopNPayloadDTO;
import com.entity.aggregation.entity.WordFrequency;
import com.entity.aggregation.service.FetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fetch")
public class FetchController {

    @Autowired
    FetchService fetchService;

    @GetMapping("/All/{videoId}")
    public ResponseEntity<List<WordFrequency>> fetchAll(@PathVariable String videoId) {
        List<WordFrequency> wordFrequencies = fetchService.fetchAllByVideoId(videoId);
        return ResponseEntity.ok(wordFrequencies);
    }

    @GetMapping("/top-N-words")
    public ResponseEntity<List<WordFrequency>> getTopWords(@RequestBody TopNPayloadDTO topNPayloadDTO) {
        return ResponseEntity.ok(fetchService.getTopNWordsByVideoId(topNPayloadDTO));
    }
}
