package com.example.newsfeedservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.newsfeedservice.clients.PostServiceClient;
import com.example.newsfeedservice.models.Post;

import reactor.core.publisher.Flux;

@Service
public class PostService {
	
	@Autowired
	private PostServiceClient postServiceClient;
	
	@Autowired 
	private TokenService tokenService;

	public Flux<Post> findById(List<String> ids) {
		String token = tokenService.generateToken();
		return postServiceClient.getPostsById(ids, token).getBody();
		
	}

}
