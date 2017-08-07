package com.though.train.model;

import java.io.Serializable;


public abstract class Node implements Serializable, Cloneable{

    private String id;

    public Node(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Node))
            return false;
        return this.id.equals(((Node)obj).getId());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
