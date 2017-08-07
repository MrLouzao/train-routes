package com.though.train.tdd;


import com.though.train.algorithm.StationGraphManager;
import com.though.train.config.AppConfig;
import com.though.train.exception.BadFormatException;
import com.though.train.exception.NotFoundException;
import com.though.train.exception.PathAlreadyExistsException;
import com.though.train.exception.PathSearchException;
import com.though.train.model.Node;
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
        Integer distance = manager.getDistanceBetweenStations("A", "R");
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


    @Test
    public void obtain_number_of_possible_routes() throws NotFoundException, PathSearchException {
        StationGraphManager manager = new StationGraphManager(this.graph);
        List<List<Node>> possibleRoutes = manager.getAllAvailableRoutesBetweenStations("A", "A");
        Assert.assertSame(5, possibleRoutes.size());
    }


    @Test
    public void obtain_number_of_possible_routes_with_less_than_4_stops() throws NotFoundException, PathSearchException {
        StationGraphManager manager = new StationGraphManager(this.graph);
        Integer numberOfStops = manager.getNumberOfRoutesWithLessThanGivenStops("A", "A", 4);
        Assert.assertSame(4, numberOfStops);
    }


    @Test
    public void obtain_number_of_possible_routes_with_exactly_3_stops() throws NotFoundException, PathSearchException {
        StationGraphManager manager = new StationGraphManager(this.graph);
        Integer numberOfStops = manager.getNumberOfRoutesExactlyGivenStops("A", "A", 3);
        Assert.assertSame(4, numberOfStops);
    }


    @Test
    public void obtain_shortest_route_between_two_nodes() throws NotFoundException, PathSearchException {
        StationGraphManager manager = new StationGraphManager(this.graph);
        Integer routeLength = manager.getShortestPathBetweenStations("A", "A");
        Assert.assertSame(9, routeLength);
    }


    @Test
    public void obtain_number_of_trips_with_repeated_stations_and_four_stops() throws NotFoundException, PathSearchException {
        StationGraphManager manager = new StationGraphManager(this.graph);
        Integer routesWithFourStops = manager.getNumberOfRoutesWithRepeatedNodesAndExactNumberOfStops("A", "A", 4);
        Assert.assertSame(1, routesWithFourStops);
    }


    @Test
    public void obtain_number_of_trips_with_repeated_stations_and_three_stops() throws NotFoundException, PathSearchException {
        StationGraphManager manager = new StationGraphManager(this.graph);
        Integer routesWithFourStops = manager.getNumberOfRoutesWithRepeatedNodesAndExactNumberOfStops("A", "A", 3);
        Assert.assertSame(4, routesWithFourStops);
    }

    @Test
    public void obtain_number_of_trips_with_repeated_stations_and_six_stops() throws NotFoundException, PathSearchException {
        StationGraphManager manager = new StationGraphManager(this.graph);
        Integer routesWithFourStops = manager.getNumberOfRoutesWithRepeatedNodesAndExactNumberOfStops("A", "A", 6);
        Assert.assertSame(17, routesWithFourStops);
    }


    @Test
    public void obtain_number_of_trips_with_repeated_stations_and_distance_less_than_12() throws NotFoundException, PathSearchException{
        StationGraphManager manager = new StationGraphManager(this.graph);
        Integer routesWithFourStops = manager.getNumberOfRoutesWithRepeatedNodesAndDistanceLimit("A", "A", 12);
        Assert.assertSame(2, routesWithFourStops);
    }


}
