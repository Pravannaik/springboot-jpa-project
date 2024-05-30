package com.scratchmachine.scratchmachine.service;

import com.scratchmachine.scratchmachine.dto.response.ReportResponse;

import java.util.Collection;

public interface ReportService {
    Collection<ReportResponse> generateUserReport(Long userId);
}
