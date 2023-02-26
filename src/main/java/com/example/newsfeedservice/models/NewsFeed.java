package com.example.newsfeedservice.models;

import java.time.Instant;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Table("newsfeed")
@Data
@Builder
@ToString
public class NewsFeed {

	@PrimaryKeyColumn(name = "user_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private String userId;
	
	@PrimaryKeyColumn(name = "created_at", ordinal =2, ordering = Ordering.DESCENDING)
	private Instant createdAt;
	
	@PrimaryKeyColumn(name = "post_id", ordinal = 3)
	private String postId;	
	
}
