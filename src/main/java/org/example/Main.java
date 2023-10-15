package org.example;

public class Main {
    public static void main(String[] args) throws Exception {
        String demoPath1 = "src/main/java/org/example/audioSamples/demoAudioKaylee.wav";
        String demoPath2 = "src/main/java/org/example/audioSamples/demoAudioMichael.wav";
        String demoPath3 = "src/main/java/org/example/audioSamples/demoAudioStutter.wav";
        String demoTMobile = "/Users/michaelgu/Desktop/dubhacks demo files/demo.wav";

        SpeechToText transcriber = new SpeechToText();
        String output = transcriber.convertSpeechtoText(demoPath3);

        //prints transcription
        System.out.println(output);

        SupportTicketGenerator test = new SupportTicketGenerator();

        //prints ticket
        System.out.println(test.generateTicket(output));
    }
}