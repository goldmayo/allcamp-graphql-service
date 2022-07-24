package com.gocampers.gocampers.connection.impl;

import java.util.List;

import com.gocampers.gocampers.connection.CampConnection;

import graphql.relay.Edge;

public class CampConnectionImpl<T> implements CampConnection<T> {

    private final int totalCounts;

    private final List<Edge<T>> edges;
  
    private final graphql.relay.PageInfo pageInfo;
    
    public  CampConnectionImpl(int totalCounts, java.util.List<graphql.relay.Edge<T>> edges, graphql.relay.PageInfo pageInfo) {
        this.totalCounts = totalCounts;
        this.edges = edges;
        this.pageInfo = pageInfo;
    }
    public int getTotalCounts() {
        return totalCounts;
    }
    public  java.util.List<graphql.relay.Edge<T>> getEdges() {
      return edges;
    }
    
    public graphql.relay.PageInfo getPageInfo() {
      return pageInfo;
    }

}
