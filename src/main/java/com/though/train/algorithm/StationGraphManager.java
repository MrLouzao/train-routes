package com.though.train.algorithm;


import com.though.train.exception.NotFoundException;
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

        public Integer getShortestPathBetweenStations(String stationFrom, String stationTo){
            //TODO implement algorithm
            return null;
        }

}
