package com.though.train.model;

public class StationGraph extends Graph {

    @Override
    protected String pathAlreadyExistsMsg(Path path) {
        Station from = (Station) path.getFrom();
        Station to = (Station) path.getTo();
        return "Route between " + from.getStationName() + " and " + to.getStationName() + " already exists";
    }
}
