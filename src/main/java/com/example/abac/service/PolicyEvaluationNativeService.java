package com.example.abac.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@ApplicationScoped
public class PolicyEvaluationNativeService {

    @PersistenceContext
    EntityManager em;

    /**
     * Prüft, ob eine Policy für die Anfrage ALLOW oder DENY zurückgibt.
     * Gibt den Effekt als String zurück (z.B. "ALLOW", "DENY"), oder null wenn
     * keine Policy matched.
     */
    public String evaluateEffect(String subjectId, String subjectRole, String resourceId, String resourceType,
            String actionId, String contextId, String contextLocation) {
        String sql = "WITH request_attrs AS (\n" +
                "  SELECT 'subject' AS target_type, 'role' AS attr_key, :subjectRole AS attr_value\n" +
                "  UNION ALL\n" +
                "  SELECT 'resource', 'type', :resourceType\n" +
                "  UNION ALL\n" +
                "  SELECT 'action', 'id', :actionId\n" +
                "  UNION ALL\n" +
                "  SELECT 'context', 'location', :contextLocation\n" +
                ")\n" +
                "SELECT p.effect\n" +
                "FROM policy p\n" +
                "JOIN policy_rule r ON r.policy_id = p.id\n" +
                "JOIN request_attrs req\n" +
                "  ON req.target_type = r.target_type\n" +
                " AND req.attr_key = r.rule_key\n" +
                " AND ((r.op = '='  AND req.attr_value = r.rule_value)\n" +
                "   OR (r.op = '!=' AND req.attr_value <> r.rule_value))\n" +
                "GROUP BY p.id, p.effect\n" +
                "HAVING COUNT(*) = (SELECT COUNT(*) FROM policy_rule WHERE policy_id = p.id)";

        Query q = em.createNativeQuery(sql);
        q.setParameter("subjectRole", subjectRole);
        q.setParameter("resourceType", resourceType);
        q.setParameter("actionId", actionId);
        q.setParameter("contextLocation", contextLocation);
        List<?> result = q.getResultList();
        return result.isEmpty() ? null : (String) result.get(0);
    }
}
