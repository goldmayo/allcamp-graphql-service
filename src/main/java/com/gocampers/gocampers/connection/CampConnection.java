package com.gocampers.gocampers.connection;

import graphql.relay.Connection;

public abstract interface CampConnection<T> extends Connection<T>{

    public abstract int getTotalCounts();

    public abstract  java.util.List<graphql.relay.Edge<T>> getEdges();
  
    public abstract graphql.relay.PageInfo getPageInfo();
}
