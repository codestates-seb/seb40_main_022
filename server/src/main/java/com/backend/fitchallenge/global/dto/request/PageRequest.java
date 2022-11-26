package com.backend.fitchallenge.global.dto.request;

import lombok.Getter;
import org.springframework.data.domain.Sort;

@Getter
public final class PageRequest {

	private static final int MAX_SIZE = 2000;

	private int page;
	private int size = 10;
	private Sort.Direction direction = Sort.Direction.DESC;
	private Sort dynamicSort;

	public PageRequest(int page, String sort) {
		this.page = page <= 0 ? 1 : page;
		if (sort == null || sort.isBlank()) {
			this.dynamicSort = Sort.by(direction, "recent");
		} else {
			this.dynamicSort = Sort.by(direction, "hot");
		}
	}

	public long getOffset() {
		return (long)(Math.max(1, page) - 1) * Math.min(size, MAX_SIZE);
	}

	// getter
	public org.springframework.data.domain.PageRequest of() {
		return org.springframework.data.domain.PageRequest.of(page - 1 , size);
	}
}

