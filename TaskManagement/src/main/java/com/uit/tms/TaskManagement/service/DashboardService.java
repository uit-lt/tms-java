package com.uit.tms.TaskManagement.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardService {

    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalTasks", 10);
        stats.put("completedTasks", 6);
        stats.put("pendingTasks", 4);
        return stats;
    }
}
