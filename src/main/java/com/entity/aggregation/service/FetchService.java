package com.entity.aggregation.service;

import com.entity.aggregation.dto.TopNPayloadDTO;
import com.entity.aggregation.entity.WordFrequency;

import java.util.List;

public interface FetchService {
    List<WordFrequency> fetchAllByVideoId(String videoId);

    List<WordFrequency> getTopNWordsByVideoId(TopNPayloadDTO topNPayloadDTO);

}
