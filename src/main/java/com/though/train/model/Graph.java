package com.though.train.model;

import com.though.train.algorithm.GraphDeepFirstRepeatedNodes;
import com.though.train.algorithm.GraphDeepFirstRepeatedNodesDistanceLimit;
import com.though.train.algorithm.GraphDeepFirstSearch;
import com.though.train.algorithm.GraphDeepFirstWithLimitStopsSearch;
import com.though.train.exception.PathAlreadyExistsException;
import com.though.train.exception.PathSearchException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Graph {


    protected Map<Node, List<Path>> mapNodePaths;


    protected Graph() {
        this.mapNodePaths = new HashMap<Node, List<Path>>();
    }


    public void addPath(Path path) throws PathAlreadyExistsException{

        if(this.mapNodePaths.containsKey(path.getFrom())){
            List<Path> paths = this.mapNodePaths.get(path.getFrom());
            if(paths.contains(path)){
                throw new PathAlreadyExistsException( this.pathAlreadyExistsMsg(path) );
            }
            this.mapNodePaths.get(path.getFrom()).add(path);
        }

        else {
            List<Path> paths = new ArrayList<Path>();
            paths.add(path);
            this.mapNodePaths.put(path.getFrom(), paths);
        }

    }


    public Path getPath(Node from, Node to){
        if(this.mapNodePaths.containsKey(from)){
            List<Path> pathsRelated = this.mapNodePaths.get(from);
            for(Path path : pathsRelated){
                if(to.equals(path.getTo())){
                    return path;
                }
            }
        }
        return null;
    }


    public List<Path> computeShortestPath(Node from, Node to){
        //TODO implement method
        return null;
    }


    public Integer computeDistancesForPaths(List<Path> paths){
        return paths.parallelStream()
                .mapToInt(Path::getDistance)
                .sum();
    }


    /**
     * Print all paths following the DFS algorithm
     * @param from
     * @param to
     */
    public void printAllPossiblePaths(Node from, Node to) throws PathSearchException {
        GraphDeepFirstSearch dfsSearch = new GraphDeepFirstSearch(this.mapNodePaths);
        dfsSearch.printAllPossiblePaths(from, to);
    }


    public List<List<Node>> obtainAllPossibleRoutes(Node from, Node to) throws PathSearchException {
        GraphDeepFirstSearch dfsSearch = new GraphDeepFirstSearch(this.mapNodePaths);
        return dfsSearch.printAllPossiblePaths(from, to);
    }


    public List<List<Node>> obtainAllPossibleRoutesWithMaxDepthOnSearch(Node from, Node to, Integer maxSteps) throws PathSearchException {
        GraphDeepFirstWithLimitStopsSearch dfsWithLimitSearch = new GraphDeepFirstWithLimitStopsSearch(this.mapNodePaths);
        return dfsWithLimitSearch.printAllPossiblePaths(from, to, maxSteps);
    }


    public List<List<Node>> obtainAllPossibleRoutesWithMaxDepthAndRepeatedNodes(Node from, Node to, Integer maxSteps) throws PathSearchException{
        GraphDeepFirstRepeatedNodes dfsWithRepeatedNodesSearch = new GraphDeepFirstRepeatedNodes(this.mapNodePaths);
        return dfsWithRepeatedNodesSearch.printAllPossiblePaths(from, to, maxSteps);
    }


    public List<List<Node>> obtainAllPossibleRoutesWithDistanceLimitAndRepeatedNodes(Node from, Node to, Integer maxDistance) throws PathSearchException {
        GraphDeepFirstRepeatedNodesDistanceLimit dfsWithDistanceLimit = new GraphDeepFirstRepeatedNodesDistanceLimit(this.mapNodePaths);
        return dfsWithDistanceLimit.printAllPossiblePaths(from, to, maxDistance);
    }


    protected String pathAlreadyExistsMsg(Path path){
        return "Path already exists in graph";
    }

}
