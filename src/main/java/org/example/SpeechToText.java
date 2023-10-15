package org.example;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.speech.v1p1beta1.*;
import com.google.protobuf.ByteString;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class SpeechToText {


    public String convertSpeechtoText(String audioFilePath) throws IOException {
        // Set your Google Cloud project ID
        String projectId = "dubhacks-backend";

        String keyPath = "src/main/java/org/example/dubhacks-backend-20c6e7fa6aca.json";
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(keyPath))
                .createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));

        // Initialize the SpeechClient
        try (SpeechClient speechClient = SpeechClient.create(SpeechSettings.newBuilder().setCredentialsProvider(FixedCredentialsProvider.create(credentials)).build())) {
            // Read the audio file and convert it to ByteString
            Path path = Paths.get(audioFilePath);
            byte[] data = Files.readAllBytes(path);
            ByteString audioBytes = ByteString.copyFrom(data);

            // Configure the audio settings
            RecognitionConfig config =
                    RecognitionConfig.newBuilder()
                            .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
                            .setLanguageCode("en-US")
                            .build();

            // Configure the audio content
            RecognitionAudio audio = RecognitionAudio.newBuilder().setContent(audioBytes).build();

            // Perform speech recognition
            RecognizeResponse response = speechClient.recognize(config, audio);

            // Process the results
            List<SpeechRecognitionResult> results = response.getResultsList();
            String output = "Transcript: ";
            for (SpeechRecognitionResult result : results) {
                // Print the transcribed text
                output += result.getAlternatives(0).getTranscript();
            }
            return output;
        }
    }
}
