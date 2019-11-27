package com.mper.springboot.repository;

import com.mper.springboot.dao.Role;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends CrudRepository<Role, Long> {
    @Override
    @Modifying
    @Query("update Role set status='DELETED' where id = :id")
    void deleteById(@Param("id") Long id);

    Role findByName(String name);
}
