package org.example;

public class Main {
    public static void main(String[] args) throws Exception {
        SpeechToText transcriber = new SpeechToText();
        String path1 = "src/main/java/org/example/sampleAudio.wav";
        String path2 = "src/main/java/org/example/frenchsample.wav";
        String output = transcriber.convertSpeechtoText(path2);

        System.out.println(output);
        SupportTicketGenerator test = new SupportTicketGenerator();
        String testTicket = "I'm calling the bank today because my account funds are broken. I tried logging in earlier today and it seems that my account is locked. I was not able to access the security code necessary to unfreeze my account, and your website told me to contact customer support. Could you provide assistance? Thanks!";
        System.out.println(test.generateTicket(output));
        //System.out.println(test.generateTicket(testTicket));

        //SupportTicketGenerator test1 = new SupportTicketGenerator();
        //test1.uploadTicket("test");


    }
}