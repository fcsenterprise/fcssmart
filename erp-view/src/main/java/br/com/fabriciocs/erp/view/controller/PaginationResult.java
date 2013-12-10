package br.com.fabriciocs.erp.view.controller;

import java.util.Collection;

public class PaginationResult<T> {
	private Long total;
	private Collection<T> data;
	private Long pageCount;

	public PaginationResult(Long total, Collection<T> data,
			Long pageCount) {
		super();
		this.total = total;
		this.data = data;
		this.pageCount = pageCount;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Collection<T> getData() {
		return data;
	}

	public void setData(Collection<T> data) {
		this.data = data;
	}

	public Long getPageCount() {
		return pageCount;
	}

	public void setPageCount(Long pageCount) {
		this.pageCount = pageCount;
	}

	@Override
	public String toString() {
		return "EmpresaPagination [total=" + total + ", data=" + data
				+ ", pageCount=" + pageCount + "]";
	}

}
