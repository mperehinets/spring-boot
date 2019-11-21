package com.mper.springboot.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
@EqualsAndHashCode
public class Role extends BaseEntity {
    @Column(name = "name")
    private String name;


    @ManyToMany(mappedBy = "roles")
    private List<User> users;

}
