package com.though.train.tdd;


import com.though.train.algorithm.StationGraphManager;
import com.though.train.config.AppConfig;
import com.though.train.exception.BadFormatException;
import com.though.train.exception.NotFoundException;
import com.though.train.exception.PathAlreadyExistsException;
import com.though.train.model.StationGraph;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ComputeDistancesTest {

    private StationGraph graph;


    @Before
    public void setUp() throws PathAlreadyExistsException, BadFormatException {
        List<String> lines = new ArrayList<>();
        lines.add("AB5");
        lines.add("BC4");
        lines.add("BD2");

        this.graph = AppConfig.createStationGraphFromLines(lines);
    }


    @Test
    public void compute_distances_between_sibling_nodes() throws NotFoundException {
        StationGraphManager manager = new StationGraphManager(this.graph);
        Integer distance = manager.getDistanceBetweenStations("A","B");
        Assert.assertSame(5, distance);
    }


    @Test(expected = NotFoundException.class)
    public void compute_distance_between_sibling_nodes_path_not_exists() throws NotFoundException {
        StationGraphManager manager = new StationGraphManager(this.graph);
        Integer distance = manager.getDistanceBetweenStations("A", "X");
    }


    @Test
    public void compute_distance_of_route() throws NotFoundException {
        StationGraphManager manager = new StationGraphManager(this.graph);
        List<String> stationList = new ArrayList<>();
        stationList.add("A");
        stationList.add("B");
        stationList.add("D");

        Integer routeDistance = manager.getDistanceOfRoute(stationList);
        Assert.assertSame(7, routeDistance);
    }




}
