package com.mper.springboot.repository;

import com.mper.springboot.dao.Book;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends CrudRepository<Book, Long> {
    @Override
    @Modifying
    @Query("update Book set status='DELETED' where id = :id")
    void deleteById(@Param("id") Long id);
}
