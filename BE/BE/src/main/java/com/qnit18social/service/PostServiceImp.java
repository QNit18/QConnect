package com.qnit18social.service;

import com.qnit18social.models.Post;
import com.qnit18social.models.User;
import com.qnit18social.reposity.PostRepository;
import com.qnit18social.reposity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PostServiceImp implements PostService{

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Override
    public Post createNewPost(Post post, Integer userId) throws Exception {

        User user = userService.findUserById(userId);

        Post newPost = new Post();
        newPost.setCaption(post.getCaption());
        newPost.setImage(post.getImage());
        newPost.setCreatedAt(LocalDateTime.now());
        newPost.setVideo(post.getVideo());
        newPost.setUser(user);

        return postRepository.save(newPost);
    }

    @Override
    public String deletePost(Integer postId, Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        Post post = findPostById(postId);

        if(!Objects.equals(post.getUser().getId(), user.getId())){
            throw new Exception("==> You cant delete pots!");
        }
        postRepository.delete(post);
        return "==> Post was deleted!";
    }

    @Override
    public List<Post> findPostByUserId(Integer userId) {
        return postRepository.findPostByUserId(userId);
    }

    @Override
    public Post findPostById(Integer postId) throws Exception {
        Optional<Post> post = postRepository.findById(postId);
        if(post.isEmpty()){
            throw new Exception("==> Post not found with id: " + postId);
        }
        return post.get();
    }

    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Post savedPost(Integer postId, Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        Post post = findPostById(postId);

        if (user.getSavedPost().contains(post)) {
            user.getSavedPost().remove(post);
        }else {
            user.getSavedPost().add(post);
        }
        userRepository.save(user);
        return post;
    }

    @Override
    public Post likePost(Integer postId, Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        Post post = findPostById(postId);

        if(post.getLiked().contains(user)) {
            post.getLiked().remove(user);
        }else{
            post.getLiked().add(user);
        }

        return postRepository.save(post);
    }
}
