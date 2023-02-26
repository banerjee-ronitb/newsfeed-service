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

import com.example.newsfeedservice.clients.GraphServiceClient;
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
				//"eyJraWQiOiJsNzNXM0lab1BfeDY4YjBMMzhyVzJPQ2lOOTdpR2xPOE5wQno5VVE3dUhJIiwiYWxnIjoiUlMyNTYifQ.eyJ2ZXIiOjEsImp0aSI6IkFULmNNTi1OYnZLMWZPbVZTOUVKOXFwQkpvTF8ySDc0OVh5NlRITE9KNWc2TjgiLCJpc3MiOiJodHRwczovL2Rldi02Mzk1NDkzOS5va3RhLmNvbS9vYXV0aDIvZGVmYXVsdCIsImF1ZCI6ImFwaTovL2RlZmF1bHQiLCJpYXQiOjE2NzU2MDM2NTcsImV4cCI6MTY3NTYwNzI1NywiY2lkIjoiMG9hNzQ1MXY4MXY0RWVVSUU1ZDciLCJzY3AiOlsiY3VzdG9tIl0sInN1YiI6IjBvYTc0NTF2ODF2NEVlVUlFNWQ3In0.bqWR_MLJahZKOJUu4Kkxxwb--OFGzFU6wwoJXpKYv4OxpaqbHil7GUtbacchKe9jqG4ouDSoKRR9qPSAwRR1B2JtFMVVG171bbPy9D64PPaJsvzvKGd6PHSKPVHBcoEKxsNgY7CXCGvyGv0ui16Y68P253wOEMsAJCHVrpfwRTVcI1rsPtR_7-cpomNfz47rMPTt-yVf3ecDgt8l39vQ_aqplxPbhwAoQJ8RnCNwVkmELqyHfNJhydmk0k7mV2Aj7Hk6mA3NzdyrhrP_-COh9ai2FGDDaUPGL2rp1UZU5Em3gYUjJwkEzHqwArY6VoKBOX9hRd75hSYUyPg57-LkeA";
		log.info("Service Access Token" + token);
		log.info("Username "+postEvent.getUsername());
		log.info("Username "+postEvent.getLastModifiedBy());
		log.info("Username "+postEvent.getPostId());
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(token);
		HttpEntity < String > entity = new HttpEntity < String > ("parameters", headers);
		//RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List> responseEntity = restTemplate.exchange("http://graph-service/users/followers/{username}",HttpMethod.GET, entity, List.class, new HashMap<String, String>() {{
			put("username", postEvent.getUsername());
		}} );
		
		responseEntity.getBody().forEach(item -> {
			String name = (String) item;
			NewsFeed feed = NewsFeed.builder().createdAt(postEvent.getCreatedAt()).postId(postEvent.getPostId())
					.userId(name).build();
			newsFeedRepository.save(feed);

		});

	}

}
