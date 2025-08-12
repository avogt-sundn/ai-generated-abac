
package com.example.abac.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "SubjectAttr")
@IdClass(SubjectAttributeId.class)
public class SubjectAttribute {
    @Id
    @Column(name = "SID")
    public String SID;
    @Id
    @Column(name = "AttrKey")
    public String AttrKey;
    @Column(name = "AttrValue")
    public String AttrValue;
}
