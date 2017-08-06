package com.though.train.algorithm.strategy;

import com.though.train.exception.PathSearchException;
import com.though.train.model.Node;

import java.util.List;

public interface GraphAlgorithmStrategy {

    List<List<Node>> obtainAllPossiblePaths(Node from, Node to, Integer parameter) throws PathSearchException;

}
