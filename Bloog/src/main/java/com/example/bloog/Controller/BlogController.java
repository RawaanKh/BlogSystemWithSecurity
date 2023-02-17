package com.example.bloog.Controller;

import com.example.bloog.API.ApiResponse;
import com.example.bloog.Model.Blog;
import com.example.bloog.Model.MyUser;
import com.example.bloog.Service.BlogService;
import com.example.bloog.Service.MyUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/blog")
public class BlogController {
    private  final BlogService blogService;
    private  final MyUserService myUserService;

    @GetMapping("/all-blog")
    public ResponseEntity getAllBlog(){
        return ResponseEntity.status(200).body(blogService.getAllBlog());
    }

    @PostMapping("/add-blog")
    public ResponseEntity addTodo(@RequestBody @Valid Blog blog, @AuthenticationPrincipal MyUser myUser){
        blogService.addBlog(blog,myUser.getId());
        return ResponseEntity.status(201).body(new ApiResponse("Blog Added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateBlog(@RequestBody @Valid Blog blog, @PathVariable Integer id, @AuthenticationPrincipal MyUser myUser){
        blogService.updateBlog(id,blog,myUser.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Blog Updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBlog(@PathVariable Integer id, @AuthenticationPrincipal MyUser myUser){
        blogService.deleteBlog(id,myUser.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Blog deleted"));
    }

    @GetMapping("/my-blogs")
    public ResponseEntity getMyTodos(@AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(blogService.getAllUserBlog(myUser.getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity getBlogById(@PathVariable Integer id , @AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(blogService.getBlogById(id , myUser.getId()));
    }

    @GetMapping("/title/{title}")
    public ResponseEntity getBlogByTitle(@PathVariable String title,@AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(blogService.getBlogByTitle(title, myUser.getId()));
    }


}
