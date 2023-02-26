package com.example.newsfeedservice.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PagedResult<T> {

	private int page;
	
	private int size;
	
	private long totalElements;
	
	private long totalPages;
	
	private boolean last;
	
	private List<T> content;
	
}
