package com.example.aiplayermod.ai;

import com.example.aiplayermod.AIPlayerMod;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class LanguageModelService {
    private static final String API_ENDPOINT = "https://api.openai.com/v1/chat/completions";
    private final HttpClient client;
    private String apiKey;
    
    public LanguageModelService() {
        this.client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
    }
    
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
    
    public String processCommand(String command) {
        if (apiKey == null || apiKey.isEmpty()) {
            AIPlayerMod.LOGGER.error("API key not set for language model service");
            return "错误：未设置API密钥";
        }
        
        try {
            String jsonBody = String.format("{"
                + "\"model\": \"gpt-3.5-turbo\","
                + "\"messages\": ["
                + "{\"role\": \"system\", \"content\": \"你是一个Minecraft中的AI玩家，需要对玩家的指令做出响应。\"},"
                + "{\"role\": \"user\", \"content\": \"%s\"}"
                + "]"
                + "}", command);
            
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_ENDPOINT))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + apiKey)
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();
            
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            if (response.statusCode() == 200) {
                JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
                String aiResponse = jsonResponse.getAsJsonArray("choices")
                        .get(0).getAsJsonObject()
                        .getAsJsonObject("message")
                        .get("content").getAsString();
                return aiResponse;
            } else {
                AIPlayerMod.LOGGER.error("Error calling language model API: " + response.statusCode());
                return "错误：API调用失败";
            }
        } catch (IOException | InterruptedException e) {
            AIPlayerMod.LOGGER.error("Error processing AI command", e);
            return "错误：处理命令时发生异常";
        }
    }
    
    public String parseAIResponse(String response) {
        // 这里可以添加更多的响应解析逻辑
        // 例如：将AI的自然语言响应转换为具体的游戏行为指令
        return response;
    }
}