package com.example.bloog.Service;

import com.example.bloog.API.ApiException;
import com.example.bloog.Model.Blog;
import com.example.bloog.Model.MyUser;
import com.example.bloog.Repository.BlogRepository;
import com.example.bloog.Repository.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private  final BlogRepository blogRepository;
    private final MyUserRepository myUserRepository;


    public List<Blog> getAllBlog() {
        return blogRepository.findAll();
    }

    public void addBlog(Blog blog, Integer user){
        MyUser myUser=myUserRepository.findMyUserById(user);
        blog.setMyUser(myUser);

        blogRepository.save(blog);
    }
    public void updateBlog(Integer id , Blog blog , Integer user){
        Blog oldBlog=blogRepository.findBlogById(id);
        MyUser myUser=myUserRepository.findMyUserById(user);
        if (oldBlog==null){
            throw new ApiException("Blog not found");
        }else if(oldBlog.getMyUser().getId()!=user){
            throw new ApiException("Unfortunately you are not authorized to access this Blog!");
        }
        blog.setId(id);
        blog.setBody(blog.getBody());
        blog.setTitle(blog.getTitle());
        blog.setMyUser(myUser);
        blogRepository.save(blog);
    }
    public void deleteBlog(Integer id, Integer user){
        Blog blog=blogRepository.findBlogById(id);
        if (blog==null){
            throw new ApiException("Blog not found");
        }else if(blog.getMyUser().getId()!=user){
            throw new ApiException("Unfortunately you are not authorized to access this Blog!!");
        }

        blogRepository.delete(blog);
    }
    public List <Blog>getAllUserBlog(Integer user){
        MyUser myUser=myUserRepository.findMyUserById(user);
        List<Blog> blogs=blogRepository.findAllBlogByMyUser(myUser);
        if (blogs.isEmpty()){
            throw new ApiException("Empty!");
        }
        return blogs;
    }
    public Blog getBlogById(Integer blog_id , Integer user_id){
        Blog blog=blogRepository.findBlogById(blog_id);
        if (blog==null){
            throw new ApiException("Blog not Found");
        }
        if (blog.getMyUser().getId()!=user_id){
            throw new ApiException("Unfortunately you are not authorized to access this Blog!");
        }
        return blog;
    }
    public Blog getBlogByTitle(String title,Integer user_id){
        Blog blog=blogRepository.findBlogByTitle(title);
        if(blog==null){
            throw new ApiException("Blog Not Found");
        }
        if(blog.getMyUser().getId()!=user_id){
            throw new ApiException("Unfortunately you are not authorized to access this Blog!");
        }
        return blog;
    }

}
