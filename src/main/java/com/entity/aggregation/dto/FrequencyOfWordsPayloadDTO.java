package com.entity.aggregation.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Getter
@Setter
@Slf4j
public class FrequencyOfWordsPayloadDTO {
    private String videoId;
    private List<String> words;

    public boolean isValid() {
        if (this.videoId == null || words == null) {
            log.warn("Received InValid Payload");
            return false;
        }

        if (videoId.isEmpty() || videoId.isBlank() || words.isEmpty()) {
            log.warn("Received InValid Payload");
            return false;
        }
        return true;
    }
}
