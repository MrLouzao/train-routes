package com.though.train.config;


import com.though.train.exception.BadFormatException;
import com.though.train.exception.ConfigurationException;
import com.though.train.exception.PathAlreadyExistsException;
import com.though.train.model.Path;
import com.though.train.model.Station;
import com.though.train.model.StationGraph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class AppConfig {

    private static final String INPUT_FILENAME = "graph.txt";


    public static List<String> readFile(String filename) throws ConfigurationException{
        List<String> lines = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line = null;
            while( (line = br.readLine()) != null){
                lines.add(line);
            }
            return lines;
        } catch(FileNotFoundException ex){
            throw new ConfigurationException("Cant find file " + filename);
        } catch(IOException ex){
            throw new ConfigurationException("Cant read file " + filename);
        }
    }


    public static StationGraph createStationGraphFromLines(List<String> lines) throws BadFormatException, PathAlreadyExistsException {
        StationGraph graph = new StationGraph();
        for(String line : lines){
            Path path = createPathFromLine(line);
            graph.addPath(path);
        }
        return graph;
    }


    private static Path createPathFromLine(String line)throws BadFormatException, PathAlreadyExistsException {
        String stationFromStr = line.substring(0,1);
        String stationToStr = line.substring(1,2);
        String distanceStr = line.substring(2, line.length());

        Station stationFrom = new Station(stationFromStr);
        Station stationTo = new Station(stationToStr);
        Path path = new Path();
        path.setFrom(stationFrom);
        path.setTo(stationTo);
        path.setDistance( Integer.valueOf(distanceStr) );
        return path;
    }


}
