package com.research_assistant.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.research_assistant.entity.GeminiResponse;
import com.research_assistant.entity.ResearchRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Map;

@Service
public class ResearchService {

    @Value("${gemini.api.url}")
    private String geminiUrl;
    @Value("${gemini.api.key}")
    private String geminiKey;

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public ResearchService(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
        this.webClient = webClientBuilder.build();
        this.objectMapper = objectMapper;
    }


    public String ProcessRequest(ResearchRequest request) {

        // 1. Build the prompt
        String prompt = buildPrompt(request);

        // 2. Query API model
        // Make the body compatible to AI model
        Map<String, Object> requestBody = Map.of(
                "contents" , new Object[] {
                        Map.of("parts", new Object[]{
                                Map.of("text", prompt)
                        })
                }
        );

        // 3. Make the API call
        String response = webClient.post()
                .uri(geminiUrl+geminiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // 4. Parse the response and
        // Return response
        return extractTextFromResponse(response);

    }

    private String extractTextFromResponse(String response) {
        try{
            GeminiResponse geminiResponse = objectMapper.readValue(response, GeminiResponse.class);
            if(geminiResponse.getCandidates() != null && !geminiResponse.getCandidates().isEmpty()){
                GeminiResponse.Candidate firstCandidate = geminiResponse.getCandidates().get(0);
                if(firstCandidate.getContent() != null &&
                firstCandidate.getContent().getParts() != null &&
                !firstCandidate.getContent().getParts().isEmpty()){
                    return firstCandidate.getContent().getParts().get(0).getText();
                }
            }
            return "Sorry there is some issue!!!";
        } catch (Exception e) {
            return "Error in parsing : "+e.getMessage();
        }
    }

    private String buildPrompt(ResearchRequest request){
        StringBuilder prompt = new StringBuilder();
        switch(request.getOperation()){
            case "summarize" :
                prompt.append("Provide a clear and concise summary of " +
                        "the following text in a few sentences:\n\n");
                break;
            case "suggest" :
                prompt.append("Based on the following content suggest related " +
                        "topics and further reading. Format the response with clear " +
                        "headings and bullet points:\n\n");
                break;
            case "mcq" :
                prompt.append("Provide the correct option number and the full text of the " +
                        "correct answer for the following multiple-choice questions. " +
                        "Present the answers in a numbered list, formatted as '1. (a) [Correct Answer Text]'," +
                        " '2. (b) [Correct Answer Text]', and so on, for all questions provided:\n\n");
                break;
            default :
                throw new IllegalArgumentException("Unknown Operation: "+request.getOperation());
        }

        prompt.append(request.getContent());
        return prompt.toString();
    }
}
