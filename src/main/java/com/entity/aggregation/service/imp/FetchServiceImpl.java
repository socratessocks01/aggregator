package com.entity.aggregation.service.imp;

import com.entity.aggregation.dto.TopNPayloadDTO;
import com.entity.aggregation.service.FetchService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FetchServiceImpl implements FetchService {


    @Override
    public String fetch(TopNPayloadDTO topNPayloadDTO) {
        if (!validate(topNPayloadDTO)) {
            return "ERROR";
        }
        // check if video exists in repository
        if (ifVideoExists(topNPayloadDTO.getVideoId())) {
            // fetch and return from database

        } else {
            // onboard using script
            onboardVideoTranscript(topNPayloadDTO.getVideoId());
            // fetch and return from database
        }
        return "";
    }

    private void onboardVideoTranscript(String videoId) {
        String pythonPath = "/usr/bin/python3"; // Use "python" or "python3" as per your setup
        String scriptPath = "persist_transcript.py";

        try {
            // Create the process to call the Python script
            ProcessBuilder processBuilder = new ProcessBuilder(pythonPath, scriptPath);
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

    private boolean validate(TopNPayloadDTO topNPayloadDTO) {
        return true;
    }

}
