package com.example.abac;

import com.example.abac.service.PolicyEvaluationService;
import jakarta.inject.Inject;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class PolicyEvaluationServiceTest {

    @Inject
    PolicyEvaluationService evalService;

    @Test
    public void testAliceCanReadMedRecordInHospital() {
        boolean allowed = evalService.isAllowed("s_alice","a_read","r_med1","c_hospital");
        assertTrue(allowed, "Alice should be allowed");
    }

    @Test
    public void testBobCannotWriteMedRecord() {
        boolean allowed = evalService.isAllowed("s_bob","a_write","r_med1","c_hospital");
        assertFalse(allowed, "Bob should be denied (deny-overrides)");
    }
}
