package com.hackathon.endangeredspecies.posts;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {
	
	@Autowired
	private PostService ps;
	
	@Autowired
	private PostRepository pr;  
	
	@RequestMapping(value = "/posts", method = RequestMethod.GET)
	public PostModel createPost(@RequestParam int roomId,
								@RequestParam int userId,
								@RequestParam String title,
								@RequestParam String description,
								@RequestParam String imageUrl) {
		
		int id = ps.createPost(roomId, userId, title, description, 0, imageUrl);
		return new PostModel(id, roomId, userId, title, description, 0, imageUrl);
		
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
