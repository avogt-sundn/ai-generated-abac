package com.example.abac.repo;

import com.example.abac.entity.Subject;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SubjectRepository implements PanacheRepository<Subject> { }
