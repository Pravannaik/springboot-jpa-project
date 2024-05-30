package com.scratchmachine.scratchmachine.controller;

import com.scratchmachine.scratchmachine.datasetup.service.DataSetupService;
import com.scratchmachine.scratchmachine.dto.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/data-setup")
public class DataSetupController {

    @Autowired
    private DataSetupService dataSetupService;

    @PutMapping("/load-data")
    public ResponseEntity<Response> loadAll() {
        dataSetupService.loadAllDataTable();
        Response response = new Response();
        response.setMessage("All data has been loaded to table");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/flush-all")
    public ResponseEntity<Response> flushAll() {
        dataSetupService.deleteAllDataTable();

        Response response = new Response();
        response.setMessage("All data has been flushed from table");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
