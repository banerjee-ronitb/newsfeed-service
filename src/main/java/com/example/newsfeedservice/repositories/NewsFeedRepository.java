package com.example.newsfeedservice.repositories;

import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import com.example.newsfeedservice.models.NewsFeed;

@Repository
public interface NewsFeedRepository extends CassandraRepository<NewsFeed, String> {

	Slice<NewsFeed> findByUserId(String username, CassandraPageRequest request);
}
