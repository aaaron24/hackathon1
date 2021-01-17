package com.hackathon.endangeredspecies.posts;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PostService {

	@Autowired
	private PostRepository pr;
	
	public int createPost(int roomId, int userId, String title, String description, Integer likes, String imageUrl) {
		PostEntity pe = new PostEntity(roomId, userId, title, description, likes, imageUrl);
		pr.save(pe);
		return pe.getId();
	}
	
	public PostEntity getPost(int id) {
		Optional<PostEntity> pe = pr.findById(id);
		if (pe.isPresent()) {
			PostEntity post = pe.get();
			return post;
		}
		return null;
	}
	
	public void deletePost(int id) {
		pr.deleteById(id);
	}
	
	public void editPost(int id, String title, String description, Integer likes, String imageUrl) {
		Optional<PostEntity> pe = pr.findById(id);
		if (pe.isPresent()) {
			PostEntity post = pe.get();
			post.setTitle(title);
			post.setDescription(description);
			post.setImageUrl(imageUrl);
		}
	}
	
	public int addLike(int id, String userString) {
		Optional<PostEntity> pe = pr.findById(id);
		if (pe.isPresent()) {
			PostEntity post = pe.get();
			post.addLike(userString);
			return post.getLikes();
		}
		return -1;
	}
	
}
