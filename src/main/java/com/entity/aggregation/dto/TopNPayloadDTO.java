package com.entity.aggregation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopNPayloadDTO {
    private String videoId;
    private int limit;

    public boolean isValid(){
        if(this.videoId==null || this.videoId.isEmpty() || this.videoId.isBlank()){
            return false;
        }
        return true;
    }
}
