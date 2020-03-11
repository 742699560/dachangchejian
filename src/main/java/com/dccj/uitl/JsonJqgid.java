package com.dccj.uitl;

import java.util.List;
import java.util.Map;

/**
 * 通过此对象封装控制层返回的JSON结果
 * 便于对控制层返回数据进行统一格式化,
 * 友好性管理*/
public class JsonJqgid {
    public static final int SUCCESS=200;
    public static final int ERROR=0;
	/**服务端的响应状态*/
	private int state;
	/**信息(给用户的提示)*/
	private String Result;
	/**
	 * page
	 */
	private PageObject page;
	
	/**具体业务数据*/
	private List<Map<String, Object>>  rows;
	
	public JsonJqgid() {
		this.state=SUCCESS;
		this.Result="true";
	}
	public JsonJqgid(Map<String, Object> map) {		
		map.put("Result", Result);
		map.put("page", page.getPageCurrent());
		map.put("total", page.getPageCount());
		map.put("rows", rows);
		return;
	}
	
	
	public void setPage(PageObject page) {
		this.page = page;
	}
	public JsonJqgid(Throwable e){
		this.state=ERROR;
		this.Result=e.getMessage();
	}
	public String getResult() {
		return Result;
	}
	public List<Map<String, Object>> getRows() {
		return rows;
	}
	public int getState() {
		return state;
	}
	public PageObject getPage() {
		return page;
	}
}
