package com.though.train.tdd;


import com.though.train.algorithm.StationGraphManager;
import com.though.train.config.AppConfig;
import com.though.train.exception.BadFormatException;
import com.though.train.exception.NotFoundException;
import com.though.train.exception.PathAlreadyExistsException;
import com.though.train.model.Path;
import com.though.train.model.StationGraph;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ComputeDistancesTest {

    private StationGraph graph;


    @Before
    public void setUp() throws PathAlreadyExistsException, BadFormatException {
        List<String> lines = new ArrayList<>();
        lines.add("AB5");
        lines.add("BC4");
        lines.add("BD2");
        lines.add("CA3");
        lines.add("DE1");
        lines.add("ED3");
        lines.add("EA1");

        lines.add("AX3");
        lines.add("XY2");
        lines.add("XZ5");
        lines.add("XM2");
        lines.add("YA4");
        lines.add("ZA9");
        lines.add("MA7");

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


    /*@Test
    public void obtain_number_of_possible_paths(){
        StationGraphManager manager = new StationGraphManager(this.graph);
        Set<List<Path>> possiblePaths = manager.findRoutesBetweenStations("A", "A");
        Assert.assertSame(2, possiblePaths.size());
    }*/


    @Test
    public void print_all_possible_routes() throws NotFoundException, CloneNotSupportedException {
        StationGraphManager manager = new StationGraphManager(this.graph);
        manager.printAllRoutes("A", "A");
    }


}
