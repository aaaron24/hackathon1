package com.hackathon.endangeredspecies.posts;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "Post")
@Table(name = "posts")
public class PostEntity {
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	private int id;
	
	private Integer roomId;
	private Integer userId;
	
	private String title;
	private String description;
	private Integer likes;
	private String allUsersThatLiked; 
	private String imageUrl;
	
	public PostEntity(int roomId, int userId, String title, String description, Integer likes, String imageUrl) {
		super();
		this.roomId = roomId;
		this.userId = userId;
		this.title = title;
		this.description = description;
		this.likes = likes;
		this.imageUrl = imageUrl;
		this.allUsersThatLiked = "";
	}
	
	public PostEntity() {
		
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
	
	public void addLike(String username) {
		if (this.allUsersThatLiked.indexOf(username)==-1) {
			this.likes++;
			this.allUsersThatLiked = this.allUsersThatLiked + username + ", ";
		}
		else {
			int index = this.allUsersThatLiked.indexOf(username);
			this.allUsersThatLiked = this.allUsersThatLiked.substring(0, index) + this.allUsersThatLiked.substring(index + username.length()+2);
			this.likes--;
		}
	}
	
	
	public int getId() {
		return this.id;
	}
	
}
