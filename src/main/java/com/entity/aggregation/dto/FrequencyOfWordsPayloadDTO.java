package com.entity.aggregation.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FrequencyOfWordsPayloadDTO {
    private String videoId;
    private List<String> words;
}
