package com.though.train;

import com.though.train.algorithm.StationGraphManager;
import com.though.train.config.AppConfig;
import com.though.train.exception.*;
import com.though.train.model.StationGraph;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

/**
 * tests specified for the thought works test
 */
public class ThoughtWorksTest {


    private StationGraphManager manager;


    @Before
    public void setUp() throws PathAlreadyExistsException, BadFormatException, ConfigurationException, FileNotFoundException {
        String filePath = this.getClass().getClassLoader().getResource("graph.txt").getFile();
        List<String> lines = AppConfig.readFile(filePath);
        StationGraph graph = AppConfig.createStationGraphFromLines(lines);
        this.manager = new StationGraphManager(graph);
    }


    @Test
    public void get_distance_route_A_B_C() throws NotFoundException {
        String[] stations = new String[]{"A", "B", "C"};
        Integer distance = this.manager.getDistanceOfRoute( Arrays.asList(stations) );
        Assert.assertSame(9, distance);
    }


    @Test
    public void get_distance_route_A_D() throws NotFoundException {
        String[] stations = new String[]{"A", "D"};
        Integer distance = this.manager.getDistanceOfRoute( Arrays.asList(stations) );
        Assert.assertSame(5, distance);
    }


    @Test
    public void get_distance_route_A_D_C() throws NotFoundException {
        String[] stations = new String[]{"A", "D", "C"};
        Integer distance = this.manager.getDistanceOfRoute( Arrays.asList(stations) );
        Assert.assertSame(13, distance);
    }


    @Test
    public void get_distance_route_A_E_B_C_D() throws NotFoundException {
        String[] stations = new String[]{"A", "E", "B", "C", "D"};
        Integer distance = this.manager.getDistanceOfRoute( Arrays.asList(stations) );
        Assert.assertSame(22, distance);
    }


    @Test(expected = NotFoundException.class)
    public void get_distance_route_A_E_D() throws NotFoundException {
        String[] stations = new String[]{"A", "E", "D"};
        Integer distance = this.manager.getDistanceOfRoute( Arrays.asList(stations) );
    }


    @Test
    public void get_number_of_trips_from_C_to_C_with_3_stops_maximum() throws NotFoundException, PathSearchException {
        //Expected: 2
        Integer numberOfTrips = this.manager.getNumberOfRoutesWithMaxNumberOfStops("C", "C", 3);
        Assert.assertSame(2, numberOfTrips);
    }


    @Test
    public void get_number_of_trips_from_A_to_C_with_exactly_4_stops() throws NotFoundException, PathSearchException {
        //Expected: 3
        Integer numberOfTrips = this.manager.getNumberOfRoutesWithRepeatedNodesAndExactNumberOfStops("A", "C", 4);
        Assert.assertSame(3, numberOfTrips);
    }


    @Test
    public void get_length_of_shortest_path_from_A_to_C() throws NotFoundException, PathSearchException {
        //Expected: 9
        Integer bestDistance = this.manager.getShortestPathBetweenStations("A", "C");
        Assert.assertSame(9, bestDistance);
    }


    @Test
    public void get_length_of_shortest_path_from_B_to_B() throws NotFoundException, PathSearchException {
        //Expected: 9
        Integer bestDistance = this.manager.getShortestPathBetweenStations("B", "B");
        Assert.assertSame(9, bestDistance);
    }


    @Test
    public void get_number_of_different_routes_from_C_to_C_with_distance_less_than_30() throws NotFoundException, PathSearchException {
        //Expected: 7
        Integer numberOfRoutes = this.manager.getNumberOfRoutesWithRepeatedNodesAndDistanceLimit("C", "C", 30);
        Assert.assertSame(7, numberOfRoutes);
    }


}
