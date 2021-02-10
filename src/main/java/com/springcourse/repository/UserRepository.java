package com.springcourse.repository;

import com.springcourse.domain.enums.Role;
import com.springcourse.domain.vo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    //Optional is a best way to handle null values
    @Query(" select u from user u where email = ?1 and password = ?2 ")
    Optional<User> login (String email, String password);

    @Transactional(readOnly = false)
    @Modifying
    @Query(" update user set role = ?2 where id = ?1 ")
    int updateRole(Long id, Role role);

    Optional<User> findByEmail(String email);
}
