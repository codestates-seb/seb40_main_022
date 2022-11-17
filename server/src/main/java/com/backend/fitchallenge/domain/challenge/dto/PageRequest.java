package com.backend.fitchallenge.domain.challenge.dto;

import lombok.Getter;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public final class PageRequest {

	private static final int MAX_SIZE = 2000;

	private int page;
	private int size =10;
	private Sort.Direction sort = Sort.Direction.DESC;
	private String filters;
	private List<Filter> filterEnums;

	public PageRequest(int page, String filters) {
		this.page = page <= 0 ? 1 : page;
		this.filters = filters;
	}

	public long getOffset() {
		return (long)(Math.max(1, page) - 1) * Math.min(size, MAX_SIZE);
	}

	public void filtersToEnum(String filters) {
		if (filters == null || filters.isEmpty() || filters.isBlank()) {
			this.filterEnums = null;
		} else {
			this.filterEnums = Arrays.stream(filters.split(","))
				.map(Filter::valueOf)
				.collect(Collectors.toList());
		}
	}


	// getter
	public org.springframework.data.domain.PageRequest of() {
		return org.springframework.data.domain.PageRequest.of(page , size);
	}

	@Getter
	public enum Filter {
		NoAnswer,
		NoAcceptedAnswer
	}

}

