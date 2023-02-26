package com.example.newsfeedservice.models;

import java.time.Instant;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class Post {

	private String id;
	
	private Instant createdAt;
	
	private Instant lastModifiedAt;
	
	private String username;
	
	private String lastModifiedBy;
	
	private String imageUrl;
	
	private String caption;
	
	
}
