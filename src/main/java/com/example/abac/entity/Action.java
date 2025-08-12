package com.example.abac.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Action {
    @Id
    public String AID;
    public String Name;
}
