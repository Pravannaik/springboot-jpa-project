package com.scratchmachine.scratchmachine.service.impl;

import com.scratchmachine.scratchmachine.dto.response.ReportResponse;
import com.scratchmachine.scratchmachine.helper.ReportHelper;
import com.scratchmachine.scratchmachine.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportHelper reportHelper;

    @Override
    public Collection<ReportResponse> generateUserReport(Long userId) {
        return reportHelper.generateUserReport(userId);
    }
}
