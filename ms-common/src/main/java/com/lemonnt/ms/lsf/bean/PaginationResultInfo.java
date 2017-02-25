package com.lemonnt.ms.lsf.bean;

import java.util.List;

public class PaginationResultInfo<T> {

	private List<T> rows;
	private int total;

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public PaginationResultInfo() {
	}

	public PaginationResultInfo(List<T> rows, int total) {
		this.rows = rows;
		this.total = total;
	}

}
