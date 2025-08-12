package com.example.abac.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;

@ApplicationScoped
public class AttributeService {

    @PersistenceContext
    EntityManager em;

    public String getAttribute(String targetType, String subjectId, String actionId, String resourceId, String contextId, String key) {
        String sql = null;
        switch (targetType) {
            case "subject":
                sql = "SELECT a.AttrValue FROM SubjectAttribute a WHERE a.SID = :id AND a.AttrKey = :key";
                return singleResultString(sql, subjectId, key);
            case "action":
                sql = "SELECT a.AttrValue FROM ActionAttribute a WHERE a.AID = :id AND a.AttrKey = :key";
                return singleResultString(sql, actionId, key);
            case "resource":
                sql = "SELECT a.AttrValue FROM ResourceAttribute a WHERE a.RID = :id AND a.AttrKey = :key";
                return singleResultString(sql, resourceId, key);
            case "context":
                sql = "SELECT a.AttrValue FROM ContextAttribute a WHERE a.CID = :id AND a.AttrKey = :key";
                return singleResultString(sql, contextId, key);
            default:
                return null;
        }
    }

    private String singleResultString(String jpql, String id, String key) {
        if (id == null) return null;
        Query q = em.createQuery(jpql);
        q.setParameter("id", id);
        q.setParameter("key", key);
        List<?> list = q.getResultList();
        if (list.isEmpty()) return null;
        return (String) list.get(0);
    }
}
