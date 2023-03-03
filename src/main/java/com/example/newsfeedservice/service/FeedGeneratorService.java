package com.example.newsfeedservice.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.newsfeedservice.messaging.UserPostEvent;
import com.example.newsfeedservice.models.NewsFeed;
import com.example.newsfeedservice.repositories.NewsFeedRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FeedGeneratorService {

	@Autowired
	private TokenService tokenGenerationService;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private NewsFeedRepository newsFeedRepository;

	@SuppressWarnings("unchecked")
	public void generateFeeds(UserPostEvent postEvent) {

		String token = tokenGenerationService.generateToken();

		log.info("Service Access Token" + token);
		log.info("Username " + postEvent.getUsername());
		log.info("Username " + postEvent.getLastModifiedBy());
		log.info("Username " + postEvent.getPostId());
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(token);
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<List> responseEntity = restTemplate.exchange(
				"http://graph-service:8080/users/followers/{username}", HttpMethod.GET, entity, List.class,
				new HashMap<String, String>() {
					{
						put("username", postEvent.getUsername());
					}
				});

		responseEntity.getBody().forEach(item -> {
			String name = (String) item;
			NewsFeed feed = NewsFeed.builder().createdAt(postEvent.getCreatedAt()).postId(postEvent.getPostId())
					.userId(name).build();
			newsFeedRepository.save(feed);

		});

	}

}
