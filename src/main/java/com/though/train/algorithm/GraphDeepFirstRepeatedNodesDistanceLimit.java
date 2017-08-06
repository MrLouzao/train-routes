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
            List<Node> adjacents = this.obtainAdjacentNodes(from);
            route.add(from);
            for(Node adjacent : adjacents){
                route.add(adjacent);
                Integer distanceOriginToAdj = this.obtainDistanceBetweenNodes(from, adjacent);
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
            this.printAllNodesForRoute(route, currentDistance);
            constructedRoutes.add(this.cloneNodeList(route));
        }

        List<Node> adjacentNodes = this.obtainAdjacentNodes(from);
        for(Node adjacentNode : adjacentNodes){
            //Only proccess next node if the distance between nodes ir less than max
            Integer distanceOriginToAdj = this.obtainDistanceBetweenNodes(from, adjacentNode);
            if((currentDistance+distanceOriginToAdj) < maxDistance){
                route.add(adjacentNode);
                this.searchAndPrintAllRoutesWithDepthLimit(adjacentNode, to, route, constructedRoutes, currentDistance+distanceOriginToAdj, maxDistance);
            }

        }

        //Finally, remove each node from path to go back within the tree (to re-visit parent nodes)
        this.removeNodeFromVisitedNodesById(from.getId(), route);
    }


    private List<Node> cloneNodeList(List<Node> list) throws PathSearchException {
        List<Node> clone = new ArrayList<Node>(list.size());
        try{
            for (Node item : list) clone.add((Node)item.clone());
            return clone;
        }catch(CloneNotSupportedException ex){
            throw new PathSearchException("Error while processing route on search");
        }
    }


    /**
     * Remove first occurence of a node starting for the last element of the array
     * @param nodeId
     * @param route
     */
    private void removeNodeFromVisitedNodesById(String nodeId, List<Node> route){
        for(int i=route.size(); i>0; i--){
            if(route.get(i-1).getId().equals(nodeId)){
                route.remove(i-1);
                break;//to break on first occurence
            }
        }
    }


    private List<Node> obtainAdjacentNodes(Node from){
        List<Node> adjacentNodes = new ArrayList<>();
        List<Path> outPaths = this.mapAdjacentNodes.get(from);
        for(Path outPath : outPaths){
            adjacentNodes.add(outPath.getTo());
        }
        return adjacentNodes;
    }


    private Integer obtainDistanceBetweenNodes(Node from, Node to){
        List<Path> outPaths = this.mapAdjacentNodes.get(from);
        for(Path path : outPaths){
            if(path.getTo().equals(to)){
                return path.getDistance();
            }
        }
        return null;
    }


    private void printAllNodesForRoute(List<Node> route, Integer totalDistance){
        StringBuilder sb = new StringBuilder();
        sb.append("Route: ");
        for(Node node : route){
            sb.append(node.getId() + " ");
        }
        sb.append("; distance = " + totalDistance);
        System.out.println(sb.toString());
    }

}
