package com.though.train.model;

import com.though.train.exception.NotFoundException;
import com.though.train.exception.PathAlreadyExistsException;
import com.though.train.exception.PathSearchException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StationGraph extends Graph {


    private Map<String, Station> mapStationByName;


    public StationGraph() {
        super();
        this.mapStationByName = new HashMap<>();
    }


    @Override
    public void addPath(Path path) throws PathAlreadyExistsException {
        super.addPath(path);

        Station from = (Station) path.getFrom();
        Station to = (Station) path.getTo();
        if(!this.mapStationByName.containsKey(from.getId())){
            this.mapStationByName.put(from.getId(), from);
        }
        if(!this.mapStationByName.containsKey(to.getId())){
            this.mapStationByName.put(to.getId(), to);
        }

    }


    @Override
    public Path getPath(Node from, Node to){
        Station fromStation = (Station) from;
        Station toStation = (Station) to;

        if(super.mapNodePaths.containsKey(fromStation)){
            List<Path> pathsRelated = super.mapNodePaths.get(fromStation);
            for(Path path : pathsRelated){
                if(toStation.equals((Station)path.getTo())){
                    return path;
                }
            }
        }
        return null;
    }


    public Boolean existsStationByName(String stationName){
        return this.mapStationByName.containsKey(stationName);
    }


    @Override
    protected String pathAlreadyExistsMsg(Path path) {
        Station from = (Station) path.getFrom();
        Station to = (Station) path.getTo();
        return "Route between " + from.getId() + " and " + to.getId() + " already exists";
    }


    public Node getNodeByStationId(String stationName) throws NotFoundException{
        Station station = this.mapStationByName.get(stationName);
        if(station == null){
            throw new NotFoundException("Searched station "+stationName+" does not exists");
        }
        return station;
    }


    public void printAllPossiblePaths(String stationFrom, String stationTo) throws NotFoundException, PathSearchException {
        Station from = (Station)this.getNodeByStationId(stationFrom);
        Station to = (Station) this.getNodeByStationId(stationTo);
        super.printAllPossiblePaths((Node)from, (Node)to);
    }


    public List<List<Node>> obtainAllPossibleRoutes(String stationFrom, String stationTo) throws NotFoundException, PathSearchException {
        Station from = (Station)this.getNodeByStationId(stationFrom);
        Station to = (Station) this.getNodeByStationId(stationTo);
        return super.obtainAllPossibleRoutes((Node)from, (Node)to);
    }


    public List<List<Node>> obtainAllPossibleRoutesWithMaxDepthOnSearch(String stationFrom, String stationTo, Integer maxTrips) throws NotFoundException, PathSearchException {
        Station from = (Station)this.getNodeByStationId(stationFrom);
        Station to = (Station) this.getNodeByStationId(stationTo);
        return super.obtainAllPossibleRoutesWithMaxDepthOnSearch(from, to, maxTrips);
    }

    public List<List<Node>> obtainAllPossibleRoutesWithMaxDepthAndRepeatedNodes(String stationFrom, String stationTo, Integer exactTrips) throws PathSearchException, NotFoundException {
        Station from = (Station)this.getNodeByStationId(stationFrom);
        Station to = (Station) this.getNodeByStationId(stationTo);
        return super.obtainAllPossibleRoutesWithMaxDepthAndRepeatedNodes(from, to, exactTrips);
    }


    public List<List<Node>> obtainAllPossibleRoutesWithDistanceLimitAndRepeatedNodes(String stationFrom, String stationTo, Integer maxDistance) throws PathSearchException, NotFoundException {
        Station from = (Station)this.getNodeByStationId(stationFrom);
        Station to = (Station) this.getNodeByStationId(stationTo);
        return super.obtainAllPossibleRoutesWithDistanceLimitAndRepeatedNodes(from, to, maxDistance);
    }


}
