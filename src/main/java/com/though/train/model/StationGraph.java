package com.though.train.model;

import com.though.train.exception.NotFoundException;
import com.though.train.exception.PathAlreadyExistsException;

import java.util.Map;

public class StationGraph extends Graph {


    private Map<String, Station> mapStationByName;


    public StationGraph(Map<String, Station> mapStationByName) {
        super();
        this.mapStationByName = mapStationByName;
    }


    @Override
    public void addPath(Path path) throws PathAlreadyExistsException {
        super.addPath(path);
        Station from = (Station) path.getFrom();
        Station to = (Station) path.getTo();
        if(!this.mapStationByName.containsKey(from.getStationName())){
            this.mapStationByName.put(from.getStationName(), from);
        }
        if(!this.mapStationByName.containsKey(to.getStationName())){
            this.mapStationByName.put(to.getStationName(), to);
        }
    }


    @Override
    protected String pathAlreadyExistsMsg(Path path) {
        Station from = (Station) path.getFrom();
        Station to = (Station) path.getTo();
        return "Route between " + from.getStationName() + " and " + to.getStationName() + " already exists";
    }


    public Node getNodeByStationId(String stationName) throws NotFoundException{
        Station station = this.mapStationByName.get(stationName);
        if(station == null){
            throw new NotFoundException("Searched station "+stationName+" does not exists");
        }
        return station;
    }


}
