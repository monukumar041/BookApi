package com.Rest.API.dao;

import com.Rest.API.Entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    User findByuserName(String userName);
}
