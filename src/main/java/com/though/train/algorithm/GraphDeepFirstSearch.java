package com.though.train.algorithm;

import com.though.train.model.Node;
import com.though.train.model.Path;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of Deep First Search algorithm for search paths
 */
public class GraphDeepFirstSearch {


    private Map<Node, List<Path>> mapAdjacentNodes;


    public GraphDeepFirstSearch(Map<Node, List<Path>> mapAdjacentNodes) {
        this.mapAdjacentNodes = mapAdjacentNodes;
    }


    public List<List<Node>> printAllPossiblePaths(Node from, Node to) throws CloneNotSupportedException {

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
                this.searchAndPrintAllRoutes(adjacent, to, mapVisitedNodes, route, allAvailableRoutes);
            }
        }
        else {
            this.searchAndPrintAllRoutes(from, to, mapVisitedNodes, route, allAvailableRoutes);
        }

        System.out.println("Number of routes : " + allAvailableRoutes.size());
        return allAvailableRoutes;
    }


    //Recursive function to obtain and print all routes
    private void searchAndPrintAllRoutes(Node from, Node to, Map<Node, Boolean> mapVisitedNodes, List<Node> route, List<List<Node>> constructedRoutes) throws CloneNotSupportedException {

        // Mark node as visited and add to current path
        mapVisitedNodes.put(from, true);
        route.add(from);


        // If alg reachs the destination, add the list of nodes to constructed routes
        if(from.equals(to)){
            this.printAllNodesForRoute(route);
            constructedRoutes.add(this.cloneNodeList(route));
        }

        //If algo not reachs the destination, process all available adjacent routes
        else{
            List<Node> adjacentNodes = this.obtainAdjacentNodes(from);
            for(Node adjacentNode : adjacentNodes){
                //Only proccess not visited nodes in order to not repeat paths
                if(!mapVisitedNodes.get(adjacentNode)){
                    this.searchAndPrintAllRoutes(adjacentNode, to, mapVisitedNodes, route, constructedRoutes);
                }
            }

        }

        //Finally, remove each node from path to go back within the tree (to re-visit parent nodes)
        mapVisitedNodes.put(from, false);
        this.removeNodeFromVisitedNodesById(from.getId(), route);
    }


    public static List<Node> cloneNodeList(List<Node> list) throws CloneNotSupportedException {
        List<Node> clone = new ArrayList<Node>(list.size());
        for (Node item : list) clone.add((Node)item.clone());
        return clone;
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


    private void printAllNodesForRoute(List<Node> route){
        StringBuilder sb = new StringBuilder();
        sb.append("Route: ");
        for(Node node : route){
            sb.append(node.getId() + " ");
        }
        System.out.println(sb.toString());
    }


}
