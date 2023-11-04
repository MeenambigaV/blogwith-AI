package com.example.topic;

// BlogService.java
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BlogService {
    @Value("${openai.api.key}")
    private String apiKey;
    private Blog blog;

    public Blog generateBlog(String topic) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.openai.com/v1/engines/davinci-codex/completions";

        // Define the headers for the API request
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + apiKey);

        // Define the API request body
        String requestBody = "{\"prompt\": \"Create a blog about " + topic + ". The blog should include a paragraph, bullet list, images, and a short quiz related to the topic.\", \"max_tokens\": 500, \"n\": 1, \"stop\": null, \"temperature\": 0.7}";

        // Make the API request and get the response
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

        // Process the response and create a Blog object
        String responseJson = response.getBody();
        // Parse the response JSON and create a Blog object
        // ...

        return blog;
    }
}