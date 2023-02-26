package com.example.newsfeedservice.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.datastax.oss.driver.shaded.guava.common.net.HttpHeaders;

@FeignClient(name = "graph-service")
public interface GraphServiceClient {

	
	@RequestMapping(method = RequestMethod.GET, value = "/users/followers/{username}", consumes = "application/json")
	public ResponseEntity<List<String>> getFollowers(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable("username") String username);
}
