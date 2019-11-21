package com.mper.springboot.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@NoArgsConstructor
@EqualsAndHashCode
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "updated_date")
    private LocalDateTime updated;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
}
