package com.though.train.model;

import com.though.train.algorithm.GraphDeepFirstSearch;
import com.though.train.exception.PathAlreadyExistsException;

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
    public void printAllPossiblePaths(Node from, Node to) throws CloneNotSupportedException {
        GraphDeepFirstSearch dfsSearch = new GraphDeepFirstSearch(this.mapNodePaths);
        dfsSearch.printAllPossiblePaths(from, to);
    }


    protected String pathAlreadyExistsMsg(Path path){
        return "Path already exists in graph";
    }

}
