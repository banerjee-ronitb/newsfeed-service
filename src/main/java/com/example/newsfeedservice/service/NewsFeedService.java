package com.example.newsfeedservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import com.example.newsfeedservice.dto.PagedResult;
import com.example.newsfeedservice.models.NewsFeed;
import com.example.newsfeedservice.models.Post;
import com.example.newsfeedservice.repositories.NewsFeedRepository;

import reactor.core.publisher.Flux;

@Service
public class NewsFeedService {

	@Autowired
	private NewsFeedRepository newsfeedRepository;
	
	@Autowired
	private PostService postService;
	

	public PagedResult<Post> getUserFeed(String username, Optional<Integer> page, Optional<Integer> size, String token) {

		CassandraPageRequest request = CassandraPageRequest.of(page.orElse(1), size.orElse(5));
		Slice<NewsFeed> result = newsfeedRepository.findByUserId(username, request);
		Flux<Post> posts = getPosts(result, token);
		return PagedResult.<Post>builder().page(result.getNumber()).size(result.getSize()).content(posts.collectList().block())
				.last(result.hasNext()).build();

	}

	private Flux<Post> getPosts(Slice<NewsFeed> feeds, String token) {
		List<String> ids = feeds.stream().map(NewsFeed :: getPostId).collect(Collectors.toList());
		return postService.findById(ids);
	}

}
