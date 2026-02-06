package com.tmd.api.stick;


public interface AiDubboService {
    void induction(String prompt, Long uid, Long sid);

    String extractMailInsights(String context);

    java.util.List<String> generateReplySuggestions(String context, int count, String style);
}
