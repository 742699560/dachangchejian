package com.dccj.uitl;

import javax.servlet.http.HttpServletRequest;

public class Pagination {

	// 当前页码
	protected int page;
	// 每页行数
	protected int rows;
	// 排序字段
	protected String sidx;
	// 排序方式
	protected String sord;

	// 页码总数
	protected int total;
	// 数据总数
	protected int count;

	public Pagination() {
		page = 1;
		rows = 10;
		sidx = "";
		sord = "desc";
	}

	public Pagination(HttpServletRequest request) {
		if (request.getParameter("page") != null) {
			page = Integer.valueOf(request.getParameter("page"));
			rows = Integer.valueOf(request.getParameter("rows"));
			
		} else {
			page = 1;
			rows = 10;
			sidx = "";
			sord = "desc";
		}
	}

	/**
	 * 当前页码,前台返回的
	 * 
	 * @return
	 */
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * 每页行数,前台返回的数据
	 * 
	 * @return
	 */
	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * 排序字段,
	 * 
	 * @return
	 */
	public String getSidx() {
		return this.sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	/**
	 * 排序方式, 值的范围"asc":"desc"
	 * 
	 * @return
	 */
	public String getSord() {
		return this.sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int GetFirstResult() {
		return (this.page - 1) * this.rows;
	}

	public int GetMaxResults() {
		return this.rows;
	}

}
