package com.though.train.algorithm;


import com.though.train.algorithm.strategy.*;
import com.though.train.exception.PathSearchException;
import com.though.train.model.Node;
import com.though.train.model.Path;

import java.util.List;
import java.util.Map;

public class GraphAlgorithmExecutor {


    public enum GraphStrategy{
        DFS_NO_REPEAT_NODES,
        DFS_REPEAT_NODES,
        DFS_LIMIT_DISTANCE,
        DFS_LIMIT_DEPTH
    }


    private GraphAlgorithmStrategy strategy = null;


    public void setStrategy(GraphStrategy selectedStrategy, Map<Node, List<Path>> adjacentNodesMap){

        if( GraphStrategy.DFS_NO_REPEAT_NODES.equals(selectedStrategy) ){
            this.strategy = new GraphDeepFirstSearch(adjacentNodesMap);
        } else if( GraphStrategy.DFS_REPEAT_NODES.equals(selectedStrategy) ){
            this.strategy = new GraphDeepFirstRepeatedNodes(adjacentNodesMap);
        } else if( GraphStrategy.DFS_LIMIT_DISTANCE.equals(selectedStrategy) ){
            this.strategy = new GraphDeepFirstRepeatedNodesDistanceLimit(adjacentNodesMap);
        } else if( GraphStrategy.DFS_LIMIT_DEPTH.equals(selectedStrategy) ){
            this.strategy = new GraphDeepFirstWithLimitStopsSearch(adjacentNodesMap);
        }

    }


    public List<List<Node>> obtainAllPossiblePathsStrategy(Node from, Node to, Integer param) throws PathSearchException {
        if(this.strategy == null){
            throw new PathSearchException("No algorithm selected to perform the path search");
        }
        return this.strategy.obtainAllPossiblePaths(from,  to, param);
    }


}
