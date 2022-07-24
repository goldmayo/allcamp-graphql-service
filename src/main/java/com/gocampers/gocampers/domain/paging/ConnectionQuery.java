package com.gocampers.gocampers.domain.paging;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ConnectionQuery<T> {
    private int totalCounts;
    private List<T> queryResults;
}
