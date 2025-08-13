package com.example.abac.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class PolicyEvaluatorTest {
    @Inject
    PolicyEvaluationService service;
    @Inject
    PolicyEvaluationNativeService nativeService;

    @Test
    void testIsAllowedWithStandardService() {
        PolicyEvaluator evaluator = service;
        boolean allowed = evaluator.isAllowed("s_alice", "a_read", "r_med1", "c_hospital");
        assertTrue(allowed, "Standard service: Alice (doctor) should be allowed to read medical record in hospital");
    }

    @Test
    void testIsAllowedWithNativeService() {
        PolicyEvaluator evaluator = nativeService;
        boolean allowed = evaluator.isAllowed("s_alice", "a_read", "r_med1", "c_hospital");
        assertTrue(allowed, "Native service: Alice (doctor) should be allowed to read medical record in hospital");
    }
}
