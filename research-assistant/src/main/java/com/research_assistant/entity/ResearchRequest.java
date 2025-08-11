package com.research_assistant.entity;

import lombok.Data;

@Data
public class ResearchRequest {

    private String content;
    // operation can be like summarize/elaborate/suggest similar/explain
    private String operation;

}
