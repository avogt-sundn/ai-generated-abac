package com.example.abac.repo;

import com.example.abac.entity.Action;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ActionRepository implements PanacheRepository<Action> { }
