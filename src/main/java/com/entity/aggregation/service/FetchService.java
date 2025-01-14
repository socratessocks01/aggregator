package com.entity.aggregation.service;

import com.entity.aggregation.dto.TopNPayloadDTO;

public interface FetchService {
    String fetch(TopNPayloadDTO topNPayloadDTO);

}
