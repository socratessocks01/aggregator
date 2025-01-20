package com.entity.aggregation.service.imp;

import com.entity.aggregation.dto.TopNPayloadDTO;
import com.entity.aggregation.entity.OnboardedVideo;
import com.entity.aggregation.entity.WordFrequency;
import com.entity.aggregation.repository.OnboardedVideoRepository;
import com.entity.aggregation.repository.WordFrequencyRepository;
import com.entity.aggregation.service.FetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

@Service
public class FetchServiceImpl implements FetchService {

    @Value("${db.name}")
    private String dbName;

    @Autowired
    OnboardedVideoRepository onboardedVideoRepository;
    @Autowired
    WordFrequencyRepository wordFrequencyRepository;

    @Override
    public List<WordFrequency> fetchAllByVideoId(String videoId) {
        if (videoId == null || videoId.isEmpty()) {
            return List.of();
        }
        // check if video exists in repository
        if (!ifVideoExists(videoId)) {
            onboardVideoTranscript(videoId);
        }
        List<WordFrequency> wordFrequencies = wordFrequencyRepository.findAllByVideoId(videoId);
        return wordFrequencies;
    }

    private boolean ifVideoExists(String videoId) {
        Optional<OnboardedVideo> optionalOnboardedVideo = onboardedVideoRepository.findByVideoId(videoId);
        if (optionalOnboardedVideo.isPresent()) {
            return true;
        }
        return false;
    }


    @Override
    public List<WordFrequency> getTopNWordsByVideoId(TopNPayloadDTO topNPayloadDTO) {
        return wordFrequencyRepository.findTopNWordsByFrequency(topNPayloadDTO.getVideoId(), topNPayloadDTO.getLimit());
    }

    private void onboardVideoTranscript(String videoId) {
        String pythonPath = "/usr/bin/python3"; // Use "python" or "python3" as per your setup
        String scriptPath = "persist_transcript.py";

        try {
            // Create the process to call the Python script
            ProcessBuilder processBuilder = new ProcessBuilder(pythonPath, scriptPath, videoId, dbName);
            Process process = processBuilder.start();

            // Read the output of the Python script
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            // Read the error output
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((line = errorReader.readLine()) != null) {
                System.err.println("Python Error: " + line);
            }

            // Wait for the process to complete
            int exitCode = process.waitFor();
            System.out.println("Python script executed with exit code: " + exitCode);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
