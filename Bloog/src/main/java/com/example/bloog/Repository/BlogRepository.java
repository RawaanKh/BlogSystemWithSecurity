package com.example.bloog.Repository;

import com.example.bloog.Model.Blog;
import com.example.bloog.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Integer> {
    Blog findBlogById(Integer id);
    List<Blog> findAllBlogByMyUser(MyUser myUser);
    Blog findBlogByTitle(String title);

}
