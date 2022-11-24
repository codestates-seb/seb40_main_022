package com.backend.fitchallenge.global.dto.request;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public final class PageRequest {

	private static final int MAX_SIZE = 2000;

	private int page;
	private int size = 10;
	private Sort.Direction sort = Sort.Direction.DESC;
	private String sortBy;
	private String filters;
	private Sort dynamicSort;
	private List<Filter> filterEnums;

	public PageRequest(int page, String filters) {
		this.page = page <= 0 ? 1 : page;
		this.filters = filters;
	}

	public long getOffset() {
		return (long)(Math.max(1, page) - 1) * Math.min(size, MAX_SIZE);
	}

	public void setDynamicSort() {
		if (sortBy == null || sortBy.isBlank()) {
			this.dynamicSort = Sort.by(sort, "recent");
		} else {
			this.dynamicSort = Sort.by(sort, "hot");
		}
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

