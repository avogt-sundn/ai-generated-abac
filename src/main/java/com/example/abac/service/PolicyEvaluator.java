package com.example.abac.service;

public interface PolicyEvaluator {
    /**
     * Returns true if the request is allowed, false otherwise.
     */
    boolean isAllowed(String subjectId, String actionId, String resourceId, String contextId);
}
