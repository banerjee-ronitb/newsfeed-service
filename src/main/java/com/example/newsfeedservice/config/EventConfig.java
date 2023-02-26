package com.example.newsfeedservice.config;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.newsfeedservice.messaging.UserPostEvent;
import com.example.newsfeedservice.service.FeedGeneratorService;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class EventConfig {

	@Autowired
	private FeedGeneratorService feedGeneratorService;

	@Bean
	public Consumer<UserPostEvent> postEventConsumer() {

		return postEvent -> {
			log.info("News Feed Service - Received UserPostEvent"+ postEvent.getPostId() + " "+postEvent.getEventId()+" "+postEvent.getCreatedAt());
			feedGeneratorService.generateFeeds(postEvent);
		};
	}

}
