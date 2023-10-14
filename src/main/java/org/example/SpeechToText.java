package org.example;
import com.google.cloud.speech.v1p1beta1.*;
import com.google.protobuf.ByteString;
import okio.ByteString;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



public class SpeechToText {


    public String convertSpeechtoText(String audioFilePath) throws IOException {
        // Set your Google Cloud project ID
        String projectId = "your-project-id";

        // Initialize the SpeechClient
        try (SpeechClient speechClient = SpeechClient.create()) {
            // Read the audio file and convert it to ByteString
            Path path = Paths.get(audioFilePath);
            byte[] data = Files.readAllBytes(path);
            ByteString audioBytes = ByteString.copyFrom(data);

            // Configure the audio settings
            RecognitionConfig config =
                    RecognitionConfig.newBuilder()
                            .setEncoding(AudioEncoding.LINEAR16)
                            .setSampleRateHertz(16000)
                            .setLanguageCode("en-US")
                            .build();

            // Configure the audio content
            RecognitionAudio audio = RecognitionAudio.newBuilder().setContent(audioBytes).build();

            // Perform speech recognition
            RecognizeResponse response = speechClient.recognize(config, audio);

            // Process the results
            List<SpeechRecognitionResult> results = response.getResultsList();
            for (SpeechRecognitionResult result : results) {
                // Print the transcribed text
                System.out.println("Transcript: " + result.getAlternatives(0).getTranscript());
            }
        }
    }
    }
}
