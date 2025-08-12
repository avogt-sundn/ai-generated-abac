package com.example.abac.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PolicyRule")
public class PolicyRule {
    @Id
    @GeneratedValue
    @Column(name = "id")
    public Long id;

    @ManyToOne
    @JoinColumn(name = "policy_id")
    public Policy policy;

    @Column(name = "rule_id")
    public Integer rule_id;
    @Column(name = "target_type")
    public String target_type;
    @Column(name = "rule_key")
    public String rule_key;
    @Column(name = "op")
    public String op;
    @Column(name = "rule_value")
    public String rule_value;
}
