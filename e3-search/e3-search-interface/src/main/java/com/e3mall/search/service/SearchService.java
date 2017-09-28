package com.e3mall.search.service;

import com.e3mall.pojo.SearchResult;

public interface SearchService {
    SearchResult search(String keyword, int page, int rows) throws Exception;
}
