package com.though.train.algorithm;

import com.though.train.exception.PathSearchException;
import com.though.train.model.Node;
import com.though.train.model.Path;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphDeepFirstRepeatedNodesDistanceLimit {

    private Map<Node, List<Path>> mapAdjacentNodes;


    public GraphDeepFirstRepeatedNodesDistanceLimit(Map<Node, List<Path>> mapAdjacentNodes) {
        this.mapAdjacentNodes = mapAdjacentNodes;
    }


    public List<List<Node>> printAllPossiblePaths(Node from, Node to, Integer maxDistance) throws PathSearchException {

        // Save all constructed routes
        List<List<Node>> allAvailableRoutes = new ArrayList<>();
        //Store all nodes
        List<Node> route = new ArrayList<>();


        // Set all visited nodes to false and mark all of then as not visited
        Map<Node, Boolean> mapVisitedNodes = new HashMap<>();
        for(Node node : this.mapAdjacentNodes.keySet()){
            mapVisitedNodes.put(node, false);
        }

        // If from = to, get all the next adjacent nodes as start node of the tree
        if(from.equals(to)){
            List<Node> adjacents = GraphAlgorithmUtil.obtainAdjacentNodes(from, this.mapAdjacentNodes);
            route.add(from);
            for(Node adjacent : adjacents){
                route.add(adjacent);
                Integer distanceOriginToAdj = GraphAlgorithmUtil.obtainDistanceBetweenNodes(from, adjacent, this.mapAdjacentNodes);
                this.searchAndPrintAllRoutesWithDepthLimit(adjacent, to, route, allAvailableRoutes, distanceOriginToAdj, maxDistance);
            }
        }
        else {
            this.searchAndPrintAllRoutesWithDepthLimit(from, to, route, allAvailableRoutes, 0, maxDistance);
        }


        System.out.println("Number of routes : " + allAvailableRoutes.size());
        return allAvailableRoutes;
    }


    //Recursive function to obtain and print all routes
    private void searchAndPrintAllRoutesWithDepthLimit(Node from, Node to, List<Node> route, List<List<Node>> constructedRoutes, Integer currentDistance, Integer maxDistance) throws PathSearchException {

        // If alg reachs the destination, add the list of nodes to constructed routes
        if(from.equals(to)){
            GraphAlgorithmUtil.printAllNodesForRoute(route, currentDistance);
            constructedRoutes.add(GraphAlgorithmUtil.cloneNodeList(route));
        }

        List<Node> adjacentNodes = GraphAlgorithmUtil.obtainAdjacentNodes(from, this.mapAdjacentNodes);
        for(Node adjacentNode : adjacentNodes){
            //Only proccess next node if the distance between nodes ir less than max
            Integer distanceOriginToAdj = GraphAlgorithmUtil.obtainDistanceBetweenNodes(from, adjacentNode, this.mapAdjacentNodes);
            if((currentDistance+distanceOriginToAdj) < maxDistance){
                route.add(adjacentNode);
                this.searchAndPrintAllRoutesWithDepthLimit(adjacentNode, to, route, constructedRoutes, currentDistance+distanceOriginToAdj, maxDistance);
            }

        }

        //Finally, remove each node from path to go back within the tree (to re-visit parent nodes)
        GraphAlgorithmUtil.removeNodeFromVisitedNodesById(from.getId(), route);
    }


}
