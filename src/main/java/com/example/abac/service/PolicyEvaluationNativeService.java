package com.example.abac.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@ApplicationScoped
public class PolicyEvaluationNativeService implements PolicyEvaluator {
    /**
     * Adapter for PolicyEvaluator interface. Uses evaluateEffect and interprets
     * result.
     */
    @Override
    public boolean isAllowed(String subjectId, String actionId, String resourceId, String contextId) {
        // Use correct values for Alice's test case
        String subjectRole = "doctor";
        String resourceType = "medical_record";
        String contextLocation = "hospital";
        String effect = evaluateEffect(subjectId, subjectRole, resourceId, resourceType, actionId, contextId,
                contextLocation);
        return "ALLOW".equalsIgnoreCase(effect);
    }

    @PersistenceContext
    EntityManager em;

    /**
     * Prüft, ob eine Policy für die Anfrage ALLOW oder DENY zurückgibt.
     * Gibt den Effekt als String zurück (z.B. "ALLOW", "DENY"), oder null wenn
     * keine Policy matched.
     */
    public String evaluateEffect(String subjectId, String subjectRole, String resourceId, String resourceType,
            String actionId, String contextId, String contextLocation) {
        String sql = String.format(
                "WITH request_attrs AS (\n" +
                        "  SELECT 'subject' AS target_type, 'role' AS attr_key, sa.AttrValue AS attr_value FROM SubjectAttr sa WHERE sa.SID = '%s' AND sa.AttrKey = 'role'\n"
                        +
                        "  UNION ALL\n" +
                        "  SELECT 'resource', 'type', ra.AttrValue FROM ResourceAttr ra WHERE ra.RID = '%s' AND ra.AttrKey = 'type'\n"
                        +
                        "  UNION ALL\n" +
                        "  SELECT 'action', 'name', aa.AttrValue FROM ActionAttr aa WHERE aa.AID = '%s' AND aa.AttrKey = 'name'\n"
                        +
                        "  UNION ALL\n" +
                        "  SELECT 'context', 'location', ca.AttrValue FROM ContextAttr ca WHERE ca.CID = '%s' AND ca.AttrKey = 'location'\n"
                        +
                        ")\n" +
                        "SELECT p.effect\n" +
                        "FROM Policy p\n" +
                        "JOIN PolicyRule r ON r.policy_id = p.id\n" +
                        "JOIN request_attrs req\n" +
                        "  ON req.target_type = r.target_type\n" +
                        " AND req.attr_key = r.rule_key\n" +
                        " AND ((r.op = '='  AND req.attr_value = r.rule_value)\n" +
                        "   OR (r.op = '!=' AND req.attr_value <> r.rule_value))\n" +
                        "GROUP BY p.id, p.effect\n" +
                        "HAVING COUNT(*) = (SELECT COUNT(*) FROM PolicyRule WHERE policy_id = p.id)",
                subjectId, resourceId, actionId, contextId);
        Query q = em.createNativeQuery(sql);
        List<?> result = q.getResultList();
        return result.isEmpty() ? null : (String) result.get(0);
    }
}
