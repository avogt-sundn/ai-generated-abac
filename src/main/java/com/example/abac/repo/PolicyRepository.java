package com.example.abac.repo;

import com.example.abac.entity.Policy;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PolicyRepository implements PanacheRepository<Policy> { }
