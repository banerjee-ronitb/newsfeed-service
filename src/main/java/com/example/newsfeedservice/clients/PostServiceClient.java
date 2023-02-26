package com.example.newsfeedservice.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.datastax.oss.driver.shaded.guava.common.net.HttpHeaders;
import com.example.newsfeedservice.models.Post;

import reactor.core.publisher.Flux;

@FeignClient(name = "POST-SERVICE")
public interface PostServiceClient {

	@RequestMapping(method = RequestMethod.POST, value= "/posts/in" )
	public ResponseEntity<Flux<Post>> getPostsById(@RequestBody List<String> ids, @RequestHeader(HttpHeaders.AUTHORIZATION) String token);
	
}
