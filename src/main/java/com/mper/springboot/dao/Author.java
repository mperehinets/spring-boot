package com.mper.springboot.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "Authors")
@PrimaryKeyJoinColumn(name = "user_id")
@Data
@EqualsAndHashCode
public class Author extends User {

    @Column(name = "education")
    private String education;

}
