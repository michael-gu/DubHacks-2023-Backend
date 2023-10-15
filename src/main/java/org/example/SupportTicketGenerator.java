package org.example;

import okhttp3.*;
import org.json.simple.*;



public class SupportTicketGenerator {
    OkHttpClient client;
    FirebaseFirestore db;
    private static final String API_KEY = "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";
    private static final String OPENAI_API_URL = "https://api.openai.com/v1/engines/gpt-3.5-turbo-instruct/completions";
    public SupportTicketGenerator() {
        this.client = new OkHttpClient();
        this.db = FirebaseFirestore.getInstance();
    }

    public String generateTicket(String inputText) throws Exception {
        String prompt = "Here is transcribed audio text from a customer support inquiry: \"" + inputText + "\"" +
                "\n" +
                "Take this text and give me a numbered list with three things: \n" +
                "1. Name from the text, or ONLY THE TEXT “N/A” if no name is detected \n" +
                "2. An 80 word or less summary of the assistance the text is needing \n" +
                "3. Up to five identifying tags in a list separated by commas that may be relevant to the support agent receiving this output (do not use any tags mentioning customer support)";

        JSONObject requestBody = new JSONObject();
        requestBody.put("prompt", prompt);
        requestBody.put("max_tokens", 150);  // Adjust the max_tokens as needed for the desired length of the summary

        RequestBody body = RequestBody.create(requestBody.toString(), MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url(OPENAI_API_URL)
                .post(body)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
            Object obj = JSONValue.parse(responseBody);
            JSONObject jsonResponse = (JSONObject) obj;

            JSONArray choicesArray = (JSONArray) jsonResponse.get("choices");
            JSONObject firstChoice = (JSONObject) choicesArray.get(0);
            return (String) firstChoice.get("text");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "failed";
    }

    public void uploadTicket() {

    }
}
