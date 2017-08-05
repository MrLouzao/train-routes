package com.though.train;

import com.though.train.algorithm.StationGraphManager;
import com.though.train.config.AppConfig;
import com.though.train.exception.BadFormatException;
import com.though.train.exception.ConfigurationException;
import com.though.train.exception.NotFoundException;
import com.though.train.exception.PathAlreadyExistsException;
import com.though.train.model.StationGraph;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * tests specified for the thought works test
 */
public class ThoughtWorksTest {


    private StationGraphManager manager;


    @Before
    public void setUp() throws PathAlreadyExistsException, BadFormatException, ConfigurationException {
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


    public void get_number_of_trips_from_C_to_C_with_3_stops_maximum(){
        //Expected: 2
    }


    public void get_number_of_trips_from_A_to_CS_with_exactly_4_stops(){
        //Expected: 3
    }


    public void get_length_of_shortest_path_from_A_to_C(){
        //Expected: 9
    }


    public void get_length_of_shortest_path_from_B_to_B(){
        //Expected: 9
    }


    public void get_number_of_different_routes_from_C_to_C_with_distance_less_than_30(){
        //Expected: 7
    }

}
