package cn.tedu.ttms.common.web;

import java.util.List;

public class Page {
    private Integer pageCurrent;//当前页
    private int pageSize;//每页显示记录条数
	private int pageCount;//总页数
    private List<?> dataList;//每页显示的数据
    private int startIndex;//开始数据
    private int rowCount;//总记录数
    
    
    
    
	public Integer getPageCurrent() {
		return pageCurrent;
	}
	public void setPageCurrent(Integer pageCurrent) {
		this.pageCurrent = pageCurrent;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<?> getDataList() {
		return dataList;
	}
	public void setDataList(List<?> dataList) {
		this.dataList = dataList;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
    public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }
	@Override
	public String toString() {
		return "Page [pageCurrent=" + pageCurrent + ", pageSize=" + pageSize + ", pageCount=" + pageCount
				+ ", dataList=" + dataList + ", startIndex=" + startIndex + ", rowCount=" + rowCount + "]";
	}
   
}