package com.yzy.could.utils;

import java.io.Serializable;
import java.util.Collections;

/**
 * 分页bean
 * 
 * @author wangy
 *
 */
public class PageBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8538033526041186324L;
	public static final int DEFUALT_PAGE_SIZE = 10;

	public int getPage() {
		if (page == 0) {
			page = 1;
		}
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public java.util.List<?> getRows() {
		if (this.rows == null) {
			return Collections.EMPTY_LIST;
		}
		return rows;
	}

	public void setRows(java.util.List<?> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		// this.setPage((total + 1) / this.getPageSize() + 1);
		this.total = total;
	}

	public int getPageSize() {
		if (this.pageSize == 0) {
			return DEFUALT_PAGE_SIZE;
		}
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 手动内存分页
	 * 
	 * @param rows
	 */
	public void setMemoryData(java.util.List<?> rows) {
		if (rows == null || rows.size() == 0) {
			return;
		}
		total = rows.size();
		int fromIndex = (this.getPage() - 1) * this.getPageSize();
		if (fromIndex > rows.size()) {
			fromIndex = total - this.getPageSize();
			if (fromIndex < 0)
				fromIndex = 0;
		}
		int toIndex = fromIndex + this.getPageSize();
		if (toIndex > total) {
			toIndex = total;
		}
		this.setRows(rows.subList(fromIndex, toIndex));
	}

	private int total;// 总记录数
	private int page;// 第几页
	private int pageSize;// 每页显示数量
	private java.util.List<?> rows;// 数据

}
