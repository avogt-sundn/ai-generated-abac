package com.example.abac.service;

import java.util.List;
import java.util.Objects;

import com.example.abac.entity.Policy;
import com.example.abac.entity.PolicyEffect;
import com.example.abac.entity.PolicyRule;
import com.example.abac.repo.PolicyRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PolicyEvaluationService implements PolicyEvaluator {

    @Inject
    PolicyRepository policyRepo;

    @Inject
    AttributeService attrService;

    @Override
    public boolean isAllowed(String subjectId, String actionId, String resourceId, String contextId) {
        List<Policy> policies = policyRepo.listAll();
        boolean anyAllow = false;
        for (Policy p : policies) {
            if (matches(p, subjectId, actionId, resourceId, contextId)) {
                if (p.effect == PolicyEffect.DENY) {
                    return false; // deny-overrides
                } else {
                    anyAllow = true;
                }
            }
        }
        return anyAllow;
    }

    private boolean matches(Policy policy, String sid, String aid, String rid, String cid) {
        for (PolicyRule r : policy.rules) {
            String actual = attrService.getAttribute(r.target_type, sid, aid, rid, cid, r.rule_key);
            if (!compare(actual, r.op, r.rule_value)) {
                return false;
            }
        }
        return true;
    }

    private boolean compare(String actual, String op, String expected) {
        if (actual == null)
            return false;
        switch (op) {
            case "=":
                return Objects.equals(actual, expected);
            case "!=":
                return !Objects.equals(actual, expected);
            default:
                return false;
        }
    }
}
