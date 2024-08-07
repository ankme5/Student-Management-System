package com.techeazy.backend.Repo;

import com.techeazy.backend.Entity.Users;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDao extends JpaRepository<Users,Long> {


    Users findByUsername(String username);

    @Modifying
    @Transactional
    @Query("update Users set token= :token where username= :username")
    void updateToken(@Param("token") String token,@Param("username") String username);

}
