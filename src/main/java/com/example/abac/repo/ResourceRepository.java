package com.example.abac.repo;

import com.example.abac.entity.Resource;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ResourceRepository implements PanacheRepository<Resource> { }
