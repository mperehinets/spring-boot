package com.mper.springboot.repository;

import com.mper.springboot.dao.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {

}
