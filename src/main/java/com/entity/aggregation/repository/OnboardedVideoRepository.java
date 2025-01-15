package com.entity.aggregation.repository;

import com.entity.aggregation.entity.OnboardedVideo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OnboardedVideoRepository extends JpaRepository<OnboardedVideo, Long> {
    Optional<OnboardedVideo> findByVideoId(String videoId);
}