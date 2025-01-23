package com.entity.aggregation.controller;

import com.entity.aggregation.dto.FrequencyOfWordsPayloadDTO;
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
        if (videoId.isEmpty() || videoId.isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        List<WordFrequency> wordFrequencies = fetchService.fetchAllByVideoId(videoId);
        return ResponseEntity.ok(wordFrequencies);
    }

    @GetMapping("/top-N-words")
    public ResponseEntity<List<WordFrequency>> getTopWords(@RequestBody TopNPayloadDTO topNPayloadDTO) {
        if (!topNPayloadDTO.isValid()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(fetchService.getTopNWordsByVideoId(topNPayloadDTO));
    }

    @GetMapping("/words-list-frequency")
    public ResponseEntity<List<WordFrequency>> getFrequencyOfWords(@RequestBody FrequencyOfWordsPayloadDTO frequencyOfWordsPayloadDTO) {
        if (!frequencyOfWordsPayloadDTO.isValid()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(fetchService.getFrequenciesByVideoIdAndWords(frequencyOfWordsPayloadDTO));
    }
}
