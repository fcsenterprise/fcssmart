package br.com.fabriciocs.erp.view.controller;

import java.util.Collection;

public class PaginationDataTableResult<T> {
	private int iTotalRecords;
	private int iTotalDisplayRecords;
	private String sEcho;
	private Collection<T> aaData;

	public PaginationDataTableResult(int iTotalRecords,
			int iTotalDisplayRecords, String sEcho, Collection<T> data) {
		super();
		this.iTotalRecords = iTotalRecords;
		this.iTotalDisplayRecords = iTotalDisplayRecords;
		this.sEcho = sEcho;
		this.aaData = data;
	}

	public PaginationDataTableResult(Long count, Long iTotalDisplayRecords,
			String sEcho, Collection<T> data) {
		this(count.intValue(), iTotalDisplayRecords.intValue(), sEcho, data);
	}

	public int getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public Collection<T> getAaData() {
		return aaData;
	}

	public void setAaData(Collection<T> aaData) {
		this.aaData = aaData;
	}

	@Override
	public String toString() {
		return "PaginationDataTableResult [iTotalRecords=" + iTotalRecords
				+ ", iTotalDisplayRecords=" + iTotalDisplayRecords + ", sEcho="
				+ sEcho + ", data=" + aaData + "]";
	}

}
