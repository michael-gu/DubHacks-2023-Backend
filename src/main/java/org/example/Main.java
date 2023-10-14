package org.example;

public class Main {
    public static void main(String[] args) throws Exception {
        SupportTicketGenerator test = new SupportTicketGenerator();
        String testTicket = "I'm calling the bank today because my account funds are broken. I tried logging in earlier today and it seems that my account is locked. I was not able to access the security code necessary to unfreeze my account, and your website told me to contact customer support. Could you provide assistance? Thanks!";
        System.out.println(test.generateTicket(testTicket));


    }
}