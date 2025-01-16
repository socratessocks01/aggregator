package com.entity.aggregation.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "word_frequencies")
public class WordFrequency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "video_id", nullable = false)
    private String videoId;

    @Column(nullable = false)
    private String word;

    @Column(nullable = false)
    private Integer count;
    
}
