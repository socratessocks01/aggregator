package com.entity.aggregation.repository;

import com.entity.aggregation.entity.WordFrequency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WordFrequencyRepository extends JpaRepository<WordFrequency, Long> {
    Optional<WordFrequency> findByVideoIdAndWord(String videoId, String word);

    List<WordFrequency> findAllByVideoId(String videoId);
}
