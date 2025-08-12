package com.example.abac.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Resource {
    @Id
    public String RID;
    public String Type;
}
