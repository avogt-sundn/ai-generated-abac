
package com.example.abac.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "ResourceAttr")
@IdClass(ResourceAttributeId.class)
public class ResourceAttribute {
    @Id
    @Column(name = "RID")
    public String RID;
    @Id
    @Column(name = "AttrKey")
    public String AttrKey;
    @Column(name = "AttrValue")
    public String AttrValue;
}
