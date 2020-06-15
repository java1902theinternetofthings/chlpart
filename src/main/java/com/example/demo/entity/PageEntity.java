package com.example.demo.entity;

import org.springframework.data.domain.Page;

public class PageEntity {
	
	private Integer totalPage;
	
	private Page page;

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	
}
