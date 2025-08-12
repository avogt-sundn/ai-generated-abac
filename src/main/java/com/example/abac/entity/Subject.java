package com.example.abac.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Subject {
    @Id
    public String SID;
    public String Name;
}
