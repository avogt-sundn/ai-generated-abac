
package com.example.abac.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "ActionAttr")
@IdClass(ActionAttributeId.class)
public class ActionAttribute {
    @Id
    @Column(name = "AID")
    public String AID;
    @Id
    @Column(name = "AttrKey")
    public String AttrKey;
    @Column(name = "AttrValue")
    public String AttrValue;
}
