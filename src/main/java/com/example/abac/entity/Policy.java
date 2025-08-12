package com.example.abac.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="Policy")
public class Policy {
    @Id
    public Long id;
    public String name;

    @Enumerated(EnumType.STRING)
    public PolicyEffect effect;

    @OneToMany(mappedBy = "policy", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<PolicyRule> rules = new ArrayList<>();
}
