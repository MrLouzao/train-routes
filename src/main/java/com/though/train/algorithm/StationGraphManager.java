package com.though.train.algorithm;


import com.though.train.exception.NotFoundException;
import com.though.train.exception.PathSearchException;
import com.though.train.model.Node;
import com.though.train.model.Path;
import com.though.train.model.Station;
import com.though.train.model.StationGraph;

import java.util.ArrayList;
import java.util.List;

public class StationGraphManager {


        private StationGraph graph;


        public StationGraphManager(StationGraph graph){
            this.graph = graph;
        }


        public Integer getDistanceBetweenStations(String stationFrom, String stationTo) throws NotFoundException{
            Station from = (Station)this.graph.getNodeByStationId(stationFrom);
            Station to = (Station)this.graph.getNodeByStationId(stationTo);

            Path obtainedPath = this.graph.getPath(from, to);
            if(obtainedPath == null){
                throw new NotFoundException("Cant find path from " + from.getId() + " to " + to.getId());
            } else {
                return obtainedPath.getDistance();
            }
        }


        public Integer getDistanceOfRoute(List<String> listStations) throws NotFoundException{
            List<String> clonedListStations = new ArrayList<>(listStations);

            Integer distance = 0;
            while(!clonedListStations.isEmpty()){

                if(clonedListStations.size() == 1){
                    break;
                }

                String stationFrom = clonedListStations.get(0);
                String stationTo = clonedListStations.get(1);
                clonedListStations.remove(0);

                distance += this.getDistanceBetweenStations(stationFrom, stationTo);
            }

            return distance;
        }


        public Integer getNumberOfRoutesWithLessThanGivenStops(String stationFrom, String stationTo, Integer numberOfStops) throws NotFoundException, PathSearchException {
            List<List<Node>> availableRoutes = this.getAllAvailableRoutesBetweenStations(stationFrom, stationTo);
            int counter = 0;
            for(List<Node> route : availableRoutes){
                if(route.size()-1<numberOfStops){
                    counter++;
                }
            }
            return counter;
        }


        public Integer getNumberOfRoutesWithMaxNumberOfStops(String stationFrom, String stationTo, Integer numberOfStops) throws NotFoundException, PathSearchException {
            List<List<Node>> availableRoutes = this.getAllAvailableRoutesBetweenStations(stationFrom, stationTo);
            int counter = 0;
            for(List<Node> route : availableRoutes){
                if(route.size()-1<=numberOfStops){
                    counter++;
                }
            }
            return counter;
        }


        public Integer getNumberOfRoutesExactlyGivenStops(String stationFrom, String stationTo, Integer numberOfStops) throws NotFoundException, PathSearchException {
            List<List<Node>> availableRoutes = this.graph.obtainAllPossibleRoutesWithMaxDepthOnSearch(stationFrom, stationTo, numberOfStops);
            int counter = 0;
            for(List<Node> route : availableRoutes){
                Integer numberStopsForRoute = route.size()-1;
                if( numberStopsForRoute.equals(numberOfStops) ){
                    counter++;
                }
            }
            return counter;
        }


        public List<List<Node>> getRoutesWithRepeatedNodesAndMaxGivenStops(String stationFrom, String stationTo, Integer numberOfStops) throws NotFoundException, PathSearchException {
            List<List<Node>> routes = this.graph.obtainAllPossibleRoutesWithMaxDepthAndRepeatedNodes(stationFrom, stationTo, numberOfStops);
            return routes;
        }


        public Integer getNumberOfRoutesWithRepeatedNodesAndExactNumberOfStops(String stationFrom, String stationTo, Integer numberOfStops) throws NotFoundException, PathSearchException {
            List<List<Node>> availableRoutes = this.getRoutesWithRepeatedNodesAndMaxGivenStops(stationFrom, stationTo, numberOfStops);
            int counter = 0;
            for(List<Node> route : availableRoutes){
                Integer numberStopsForRoute = route.size()-1;
                if( numberStopsForRoute.equals(numberOfStops) ){
                    counter++;
                }
            }
            return counter;
        }


        public Integer getNumberOfRoutesWithRepeatedNodesAndDistanceLimit(String stationFrom, String stationTo, Integer maxDistance) throws NotFoundException, PathSearchException {
            List<List<Node>> routes = this.graph.obtainAllPossibleRoutesWithDistanceLimitAndRepeatedNodes(stationFrom, stationTo, maxDistance);
            return routes.size();
        }


        public List<List<Node>> getAllAvailableRoutesBetweenStations(String stationFrom, String stationTo) throws NotFoundException, PathSearchException {
            return this.graph.obtainAllPossibleRoutesNoRepeatNodes(stationFrom, stationTo);
        }


        public Integer getShortestPathBetweenStations(String stationFrom, String stationTo) throws NotFoundException, PathSearchException {
            List<List<Node>> availableRoutes = this.getAllAvailableRoutesBetweenStations(stationFrom, stationTo);

            Integer bestDistance = null;
            for(List<Node> route : availableRoutes){

                Integer distanceOfRoute =
                        this.getDistanceOfRoute( this.buildListOfNodeId(route) );

                if(bestDistance == null || distanceOfRoute < bestDistance){
                    bestDistance = distanceOfRoute;
                }

            }

            return bestDistance;
        }


        private List<String> buildListOfNodeId(List<Node> nodes){
            List<String> nodeIds = new ArrayList<>();
            for(Node node : nodes){
                nodeIds.add(node.getId());
            }
            return nodeIds;
        }


}
