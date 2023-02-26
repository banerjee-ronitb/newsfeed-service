package com.example.newsfeedservice.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.newsfeedservice.models.Post;
import com.example.newsfeedservice.service.NewsFeedService;

import com.datastax.oss.driver.shaded.guava.common.net.HttpHeaders;
import com.example.newsfeedservice.dto.PagedResult;

@RestController
public class NewsFeedController {
	
	@Autowired
	private NewsFeedService newsFeedService;
	
	
	@GetMapping()
	@PreAuthorize("hasAuthority('SCOPE_profile')")
	public ResponseEntity<PagedResult<Post>> getFeeds(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestParam(required = false) String page, @RequestParam(required = false) String size){
		PagedResult<Post> pagedResult =  newsFeedService.getUserFeed("", Optional.of(page).map(p -> Integer.parseInt(p)), Optional.of(size).map(s -> Integer.parseInt(s)), token);
		
		return  ResponseEntity.ok(pagedResult);
	}

}
