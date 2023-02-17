package com.example.bloog.Repository;

import com.example.bloog.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser,Integer> {

    MyUser findMyUserById(Integer id);
    MyUser findMyUserByUsername(String username);
}
