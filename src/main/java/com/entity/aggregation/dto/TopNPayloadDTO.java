package com.entity.aggregation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopNPayloadDTO {
    String videoId;
    int limit;
}
