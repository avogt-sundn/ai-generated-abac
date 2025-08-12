package com.example.abac;

import com.example.abac.service.AttributeService;
import jakarta.inject.Inject;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class AttributeServiceTest {

    @Inject
    AttributeService attributeService;

    @Test
    public void testGetAttribute() {
        String role = attributeService.getAttribute("subject","s_alice",null,null,null,"role");
        assertEquals("doctor", role);
    }
}
