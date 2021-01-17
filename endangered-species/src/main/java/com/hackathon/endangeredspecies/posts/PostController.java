package com.hackathon.endangeredspecies.posts;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
public class PostController {
	
	@Autowired
	private PostService ps;
	
	@Autowired
	private PostRepository pr;  
	
	@PutMapping("/posts")
	public PostModel createPost( @RequestBody PostModel p) {
		
		int id = ps.createPost(p.getRoomId(), p.getUserId(), p.getTitle(), p.getDescription(), 0, p.getImageUrl());
		return new PostModel(id, p.getRoomId(), p.getUserId(), p.getTitle(), p.getDescription(), 0, p.getImageUrl());
		
	}
	
	@PostMapping("/posts")
	public PostModel changePost( @RequestBody PostModel p) {
		
		ps.editPost(p.getId(), p.getTitle(), p.getDescription(), null, p.getImageUrl());
		return new PostModel(p.getId(), null, null, null, null, null, null);
		
	}
	
	@DeleteMapping("/posts")
	public void deletePost( @RequestBody PostModel p ) {
		ps.deletePost(p.getId());
	}
	
	@GetMapping("/posts/{roomId}")
	public List<PostModel> getAllPostsFromRoom(  @PathVariable("roomId") int roomId) {
		List<PostModel> models = new ArrayList<>();
		List<PostEntity> entities = pr.getByRoomId(roomId);
		for (int i=0; i<entities.size(); i++) {
			PostEntity post = entities.get(i);
			models.add(new PostModel(post.getId(), roomId, post.getUserId(), post.getTitle(), post.getDescription(), post.getLikes(), post.getImageUrl()));
		}
		return models;
	}
	
	@GetMapping("/posts/user/{userId}")
	public List<PostModel> getAllPostsByUser( @PathVariable("userId") int userId) {
		List<PostModel> models = new ArrayList<>();
		List<PostEntity> entities = pr.getByUserId(userId);
		for (int i=0; i<entities.size(); i++) {
			PostEntity post = entities.get(i);
			models.add(new PostModel(post.getId(), post.getRoomId(), post.getUserId(), post.getTitle(), post.getDescription(), post.getLikes(), post.getImageUrl()));
		}
		return models;
	}
	
	@PostMapping("/posts/like/{username}/{postId}")
	public int likePost( @PathVariable("postId") int postId, @PathVariable("username") String username) {
		return ps.addLike(postId, username);   
	}
	
	
	

}
