package com.melody.service;

import java.util.List;

public class Pager<T>
{
  private List<T> dataList = null;

  private int totalCount = 0;

  private int pageSize = 10;

  private int currentPage = 0;

  private int pageCount = 1;

  private int offset = 1;

  public int getOffset() {

     offset = currentPage * pageSize;

    /*if (offset > totalCount) {
      offset = pageCount - 1 * pageSize;
    }*/
    return offset;
  }

  public void setOffset(int offset) {
    this.offset = offset;
  }

  public Pager(Integer pageSize, Integer totalCount, Integer currentPage)
  {
    if (pageSize == null)
    {
      pageSize = Integer.valueOf(15);
    }

    setPageSize(pageSize.intValue());

    setTotalCount(totalCount.intValue());

    Double pageNum = Double.valueOf(Math.ceil((getTotalCount() + 0.0D) / pageSize.intValue()));

    setCurrentPage(currentPage.intValue());

    setPageCount(pageNum.intValue());
  }

  public Pager(int totalCount, int pageSize, int currentPage, List<T> dataList)
  {
    this.dataList = dataList;
    this.pageSize = pageSize;
    this.currentPage = currentPage;
    this.totalCount = totalCount;
  }

  public Pager() {
  }

  public List<T> getDataList() {
    return this.dataList;
  }

  public void setDataList(List<T> dataList) {
    this.dataList = dataList;
  }

  public int getPageSize() {
    return this.pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getCurrentPage() {
    return this.currentPage;
  }

  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }

  public int getPageCount()
  {
    return this.pageCount;
  }

  public int getTotalCount()
  {
    return this.totalCount;
  }

  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }

  public void setPageCount(int pageCount) {
    this.pageCount = pageCount;
  }


  @Override
  public String toString() {
    return "Pager{" +
            "dataList=" + dataList +
            ", totalCount=" + totalCount +
            ", pageSize=" + pageSize +
            ", currentPage=" + currentPage +
            ", pageCount=" + pageCount +
            '}';
  }
}
