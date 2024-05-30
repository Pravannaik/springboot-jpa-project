package com.scratchmachine.scratchmachine.controller;

import com.scratchmachine.scratchmachine.dto.response.ReportResponse;
import com.scratchmachine.scratchmachine.dto.response.Response;
import com.scratchmachine.scratchmachine.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/get-report")
    public ResponseEntity<Response> generateUserReport(@RequestParam(required = false) Long userId) {

        Collection<ReportResponse> reportResponse = reportService.generateUserReport(userId);

        Response response = new Response();
        response.setData(reportResponse);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
