package com.example.AOManager.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseForList<T> {
    private long totalResult;
    private int currentPage;
    private int totalPage;
    private int numPerPage;
    private T data;
}
