package com.mper.springboot.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "books")
@Data
@EqualsAndHashCode
public class Book extends BaseEntity {
    @Column(name = "title")
    private String title;

    @OneToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

}
