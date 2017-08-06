package com.though.train.algorithm;

import com.though.train.exception.PathSearchException;
import com.though.train.model.Node;
import com.though.train.model.Path;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of Deep First Search algorithm for search paths
 */
public class GraphDeepFirstSearch {

    final static Logger LOG = Logger.getLogger(GraphDeepFirstSearch.class);

    private Map<Node, List<Path>> mapAdjacentNodes;


    public GraphDeepFirstSearch(Map<Node, List<Path>> mapAdjacentNodes) {
        this.mapAdjacentNodes = mapAdjacentNodes;
    }


    public List<List<Node>> obtainAllPossiblePaths(Node from, Node to) throws PathSearchException {

        if(LOG.isDebugEnabled()){
            LOG.debug("Executing path finding with class " + GraphDeepFirstSearch.class.getName());
        }

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
                this.searchAndPrintAllRoutes(adjacent, to, mapVisitedNodes, route, allAvailableRoutes);
            }
        }
        else {
            this.searchAndPrintAllRoutes(from, to, mapVisitedNodes, route, allAvailableRoutes);
        }


        if(LOG.isDebugEnabled()){
            LOG.debug("Number of found routes: " + allAvailableRoutes.size());
        }

        return allAvailableRoutes;
    }


    //Recursive function to obtain and print all routes
    private void searchAndPrintAllRoutes(Node from, Node to, Map<Node, Boolean> mapVisitedNodes, List<Node> route, List<List<Node>> constructedRoutes) throws PathSearchException {

        // Mark node as visited and add to current path
        mapVisitedNodes.put(from, true);
        route.add(from);


        // If alg reachs the destination, add the list of nodes to constructed routes
        if(from.equals(to)){
            if(LOG.isDebugEnabled()){
                String strRoute = GraphAlgorithmUtil.generateStringAllNodesForRoute(route);
                LOG.debug(strRoute);
            }
            constructedRoutes.add(GraphAlgorithmUtil.cloneNodeList(route));
        }

        //If algo not reachs the destination, process all available adjacent routes
        else{
            List<Node> adjacentNodes = GraphAlgorithmUtil.obtainAdjacentNodes(from, this.mapAdjacentNodes);
            for(Node adjacentNode : adjacentNodes){
                //Only proccess not visited nodes in order to not repeat paths
                if(!mapVisitedNodes.get(adjacentNode)){
                    this.searchAndPrintAllRoutes(adjacentNode, to, mapVisitedNodes, route, constructedRoutes);
                }
            }

        }

        //Finally, remove each node from path to go back within the tree (to re-visit parent nodes)
        mapVisitedNodes.put(from, false);
        GraphAlgorithmUtil.removeNodeFromVisitedNodesById(from.getId(), route);
    }


}
