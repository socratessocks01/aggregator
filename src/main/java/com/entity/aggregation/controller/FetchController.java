package com.entity.aggregation.controller;

import com.entity.aggregation.dto.TopNPayloadDTO;
import com.entity.aggregation.service.FetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fetch")
public class FetchController {

    @Autowired
    FetchService fetchService;

    public ResponseEntity<?> fetchTopN(@RequestBody TopNPayloadDTO topNPayloadDTO) {
        String transcript = fetchService.fetch(topNPayloadDTO);
        return ResponseEntity.ok(transcript);
    }
}
