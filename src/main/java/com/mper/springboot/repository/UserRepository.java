package com.mper.springboot.repository;

import com.mper.springboot.dao.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    @Modifying
    @Query("update User set status='DELETED' where id = :id")
    void deleteById(@Param("id") Long id);

    User findUserByEmail(String email);
}
