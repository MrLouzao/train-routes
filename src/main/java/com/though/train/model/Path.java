package com.though.train.model;

public class Path {

    private Node from;
    private Node to;
    private Integer distance;


    public Path() {
    }

    public Path(Node from, Node to, Integer distance) {
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public Node getFrom() {
        return from;
    }

    public void setFrom(Node from) {
        this.from = from;
    }

    public Node getTo() {
        return to;
    }

    public void setTo(Node to) {
        this.to = to;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

}
