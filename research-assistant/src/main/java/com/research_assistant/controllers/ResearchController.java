package com.research_assistant.controllers;

import com.research_assistant.entity.ResearchRequest;
import com.research_assistant.services.ResearchService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/research")
public class ResearchController {

    private final ResearchService researchService;

    @PostMapping("/help")
    public ResponseEntity<String> processRequest(@RequestBody ResearchRequest request){
        String result = researchService.ProcessRequest(request);
        return ResponseEntity.ok(result);
    }

}
