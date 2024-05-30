package com.scratchmachine.scratchmachine.controller;

import com.scratchmachine.scratchmachine.dto.request.AllocateScratchCardRequest;
import com.scratchmachine.scratchmachine.dto.request.GenerateScratchCardRequest;
import com.scratchmachine.scratchmachine.dto.response.Response;
import com.scratchmachine.scratchmachine.service.ScratchCardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/scratch-card")
public class ScratchCardController {

    @Autowired
    private ScratchCardService scratchCardService;

    @PostMapping("/generate-scratch-cards")
    public ResponseEntity<Response> generateScratchCards(@RequestBody @Valid GenerateScratchCardRequest request) {

        scratchCardService.generateScratchCards(request);

        Response response = new Response();
        response.setMessage("Scratch cards successfully generated");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/allocate-scratch-cards")
    public ResponseEntity<Response> allocateScratchCards(@RequestBody AllocateScratchCardRequest request) {

        scratchCardService.allocateScratchCards(request);

        Response response = new Response();
        response.setMessage("Scratch cards successfully allocated");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
