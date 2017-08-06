package com.though.train.algorithm;

import com.though.train.exception.PathSearchException;
import com.though.train.model.Node;
import com.though.train.model.Path;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class GraphAlgorithmUtil {


    protected static List<Node> obtainAdjacentNodes(Node from, Map<Node,List<Path>> mapAdjacentNodes){
        List<Node> adjacentNodes = new ArrayList<>();
        List<Path> outPaths = mapAdjacentNodes.get(from);
        for(Path outPath : outPaths){
            adjacentNodes.add(outPath.getTo());
        }
        return adjacentNodes;
    }


    protected static void printAllNodesForRoute(List<Node> route){
        StringBuilder sb = new StringBuilder();
        sb.append("Route: ");
        for(Node node : route){
            sb.append(node.getId() + " ");
        }
        System.out.println(sb.toString());
    }


    protected static void printAllNodesForRoute(List<Node> route, Integer totalDistance){
        StringBuilder sb = new StringBuilder();
        sb.append("Route: ");
        for(Node node : route){
            sb.append(node.getId() + " ");
        }
        sb.append("; distance = " + totalDistance);
        System.out.println(sb.toString());
    }


    protected static Integer obtainDistanceBetweenNodes(Node from, Node to, Map<Node,List<Path>> mapAdjacentNodes){
        List<Path> outPaths = mapAdjacentNodes.get(from);
        for(Path path : outPaths){
            if(path.getTo().equals(to)){
                return path.getDistance();
            }
        }
        return null;
    }


    protected static List<Node> cloneNodeList(List<Node> list) throws PathSearchException {
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
    protected static void removeNodeFromVisitedNodesById(String nodeId, List<Node> route){
        for(int i=route.size(); i>0; i--){
            if(route.get(i-1).getId().equals(nodeId)){
                route.remove(i-1);
                break;//to break on first occurence
            }
        }
    }

}
