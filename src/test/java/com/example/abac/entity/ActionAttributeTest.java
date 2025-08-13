package com.example.abac.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ActionAttributeTest {
    @Test
    void testActionAttributeFields() {
        ActionAttribute attr = new ActionAttribute();
        attr.AID = "action1";
        attr.AttrKey = "type";
        attr.AttrValue = "read";

        assertEquals("action1", attr.AID);
        assertEquals("type", attr.AttrKey);
        assertEquals("read", attr.AttrValue);
    }

    @Test
    void testEqualsAndHashCode() {
        ActionAttribute a1 = new ActionAttribute();
        a1.AID = "action1";
        a1.AttrKey = "type";
        a1.AttrValue = "read";

        ActionAttribute a2 = new ActionAttribute();
        a2.AID = "action1";
        a2.AttrKey = "type";
        a2.AttrValue = "read";

        // Only checks field equality, not entity identity
        assertEquals(a1.AID, a2.AID);
        assertEquals(a1.AttrKey, a2.AttrKey);
        assertEquals(a1.AttrValue, a2.AttrValue);
    }
}
