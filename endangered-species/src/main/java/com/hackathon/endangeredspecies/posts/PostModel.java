package com.hackathon.endangeredspecies.posts;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class PostModel {
	  
private Integer id; 

	private Integer roomId;  
	private Integer userId;
	private String title;    
	private String description;   
	private Integer likes;  
	private String imageUrl; 
	  
	public PostModel(Integer id, Integer roomId, Integer userId, String title, String description, Integer likes,
			String imageUrl) { 
		super();  
		this.id = id;     
		this.roomId = roomId;  
		this.userId = userId;
		this.title = title;
		this.description = description;     
		this.likes = likes;  
		this.imageUrl = imageUrl;
	}

	public Integer getId() { 
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	
}
