package com.hzdz.ls.db.pagehelper;

import com.github.pagehelper.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageContent<T> {
    public final List<T> array;
    public final int pageNo;
    public final int pageSize;
    public final long total;
    public final int totalPage;

    public PageContent(Page<T> page){
        this.array = page.getResult();
        this.pageNo = page.getPageNum();
        this.pageSize = page.getPageSize();
        this.total = page.getTotal();
        this.totalPage = page.getPages();
    }

    public PageContent(List<T> dataList, int currentPageNo, int currentPageSize,
                       long totalCount, int totalPageCount){
        this.array = dataList;
        this.pageNo = currentPageNo;
        this.pageSize = currentPageSize;
        this.total = totalCount;
        this.totalPage = totalPageCount;
    }

    public static Map<String, Object> getContent(Long totalHits, Integer pageNo,
                                                 Integer pageSize, int MAX_PAGE){
        Map<String, Object> map = new HashMap<>();
        int totalPage = (int) Math.ceil(totalHits/1.0/pageSize);
        totalPage = totalPage < MAX_PAGE ? totalPage : MAX_PAGE;
        int totalCount = totalHits.intValue();
        map.put("pageNo", pageNo);
        map.put("pageSize", pageSize);
        map.put("total", totalCount);
        map.put("totalPage", totalPage);
        return map;
    }
}
