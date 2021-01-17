package com.hackathon.endangeredspecies.posts;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<PostEntity, Integer> {
	
	public List<PostEntity> getByRoomId(int roomId);
	
	public List<PostEntity> getByUserId(int userId);
}

