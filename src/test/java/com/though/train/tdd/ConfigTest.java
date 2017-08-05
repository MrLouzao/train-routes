package com.though.train.tdd;

import com.though.train.config.AppConfig;
import com.though.train.exception.BadFormatException;
import com.though.train.exception.NotFoundException;
import com.though.train.exception.PathAlreadyExistsException;
import com.though.train.model.Path;
import com.though.train.model.StationGraph;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ConfigTest {


    @Test
    public void create_graph_with_unique_register_check_stations_added() throws BadFormatException, NotFoundException, PathAlreadyExistsException{
        List<String> lines = new ArrayList<>();
        lines.add("AB5");

        StationGraph graph = AppConfig.createStationGraphFromLines(lines);
        Assert.assertTrue(graph.existsStationByName("A"));
        Assert.assertTrue(graph.existsStationByName("B"));
    }


    @Test
    public void create_graph_with_unique_register_check_path_between_exists() throws BadFormatException, NotFoundException, PathAlreadyExistsException{
        List<String> lines = new ArrayList<>();
        lines.add("AB5");

        StationGraph graph = AppConfig.createStationGraphFromLines(lines);
        Path pathBetweenStations =
                graph.getPath(graph.getNodeByStationId("A"), graph.getNodeByStationId("B"));

        Assert.assertNotNull(pathBetweenStations);
    }


    @Test
    public void create_graph_with_unique_register_check_path_between_distance() throws BadFormatException, NotFoundException, PathAlreadyExistsException{
        List<String> lines = new ArrayList<>();
        lines.add("AB5");

        StationGraph graph = AppConfig.createStationGraphFromLines(lines);
        Path pathBetweenStations =
                graph.getPath(graph.getNodeByStationId("A"), graph.getNodeByStationId("B"));

        Assert.assertSame(5, pathBetweenStations.getDistance());
    }



}
