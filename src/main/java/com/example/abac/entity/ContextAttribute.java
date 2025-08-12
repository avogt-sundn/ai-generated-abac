
package com.example.abac.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ContextAttr")
@IdClass(ContextAttributeId.class)
public class ContextAttribute {
    @Id
    @Column(name = "CID")
    public String CID;
    @Id
    @Column(name = "AttrKey")
    public String AttrKey;
    @Column(name = "AttrValue")
    public String AttrValue;
}
