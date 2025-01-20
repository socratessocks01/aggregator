package com.entity.aggregation.repository;

import com.entity.aggregation.entity.WordFrequency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WordFrequencyRepository extends JpaRepository<WordFrequency, Long> {
    Optional<WordFrequency> findByVideoIdAndWord(String videoId, String word);

    List<WordFrequency> findAllByVideoId(String videoId);

    // New method to fetch top N words by frequency for a specific video
    @Query(value = "SELECT wf FROM WordFrequency wf WHERE wf.videoId = :videoId ORDER BY wf.count DESC limit :topN", nativeQuery = false)
    List<WordFrequency> findTopNWordsByFrequency(@Param("videoId") String videoId, @Param("topN") int topN);
}
