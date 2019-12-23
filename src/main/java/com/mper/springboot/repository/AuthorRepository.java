package com.mper.springboot.repository;

import com.mper.springboot.dao.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AuthorRepository extends CrudRepository<Author, Long> {



}
