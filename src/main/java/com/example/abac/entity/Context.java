package com.example.abac.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Context {
    @Id
    public String CID;
    public String Description;
}
