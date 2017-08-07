package com.though.train.model;

import com.though.train.algorithm.GraphAlgorithmExecutor;
import com.though.train.algorithm.strategy.*;
import com.though.train.exception.PathAlreadyExistsException;
import com.though.train.exception.PathSearchException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Graph {


    protected Map<Node, List<Path>> adjacentNodes;
    private GraphAlgorithmExecutor strategyToExecute;


    protected Graph() {
        this.adjacentNodes = new HashMap<Node, List<Path>>();
        this.strategyToExecute = new GraphAlgorithmExecutor();
    }


    public void addPath(Path path) throws PathAlreadyExistsException{
        if(this.adjacentNodes.containsKey(path.getFrom())){
            List<Path> paths = this.adjacentNodes.get(path.getFrom());
            if(paths.contains(path)){
                throw new PathAlreadyExistsException( this.pathAlreadyExistsMsg(path) );
            }
            this.adjacentNodes.get(path.getFrom()).add(path);
        }

        else {
            List<Path> paths = new ArrayList<Path>();
            paths.add(path);
            this.adjacentNodes.put(path.getFrom(), paths);
        }
    }


    public Path getPath(Node from, Node to){
        if(this.adjacentNodes.containsKey(from)){
            List<Path> pathsRelated = this.adjacentNodes.get(from);
            for(Path path : pathsRelated){
                if(to.equals(path.getTo())){
                    return path;
                }
            }
        }
        return null;
    }


    public List<List<Node>> obtainAllPossibleRoutesNoRepeatedNodes(Node from, Node to) throws PathSearchException {
        this.strategyToExecute.setStrategy( GraphAlgorithmExecutor.GraphStrategy.DFS_NO_REPEAT_NODES, this.adjacentNodes );
        return this.strategyToExecute.obtainAllPossiblePathsStrategy(from, to, null);
    }


    public List<List<Node>> obtainAllPossibleRoutesWithMaxDepthOnSearch(Node from, Node to, Integer maxSteps) throws PathSearchException {
        this.strategyToExecute.setStrategy( GraphAlgorithmExecutor.GraphStrategy.DFS_LIMIT_DEPTH, this.adjacentNodes );
        return this.strategyToExecute.obtainAllPossiblePathsStrategy(from, to, maxSteps);
    }


    public List<List<Node>> obtainAllPossibleRoutesWithMaxDepthAndRepeatedNodes(Node from, Node to, Integer maxSteps) throws PathSearchException{
        this.strategyToExecute.setStrategy( GraphAlgorithmExecutor.GraphStrategy.DFS_REPEAT_NODES, this.adjacentNodes );
        return this.strategyToExecute.obtainAllPossiblePathsStrategy(from, to, maxSteps);
    }


    public List<List<Node>> obtainAllPossibleRoutesWithDistanceLimitAndRepeatedNodes(Node from, Node to, Integer maxDistance) throws PathSearchException {
        this.strategyToExecute.setStrategy( GraphAlgorithmExecutor.GraphStrategy.DFS_LIMIT_DISTANCE, this.adjacentNodes );
        return this.strategyToExecute.obtainAllPossiblePathsStrategy(from, to, maxDistance);
    }


    protected String pathAlreadyExistsMsg(Path path){
        return "Path already exists in graph";
    }

}
