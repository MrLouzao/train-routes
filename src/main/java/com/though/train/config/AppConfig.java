package com.though.train.config;


import com.though.train.exception.BadFormatException;
import com.though.train.exception.ConfigurationException;
import com.though.train.exception.PathAlreadyExistsException;
import com.though.train.model.Path;
import com.though.train.model.Station;
import com.though.train.model.StationGraph;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public final class AppConfig {

    private static final String INPUT_FILENAME = "graph.txt";


    public static List<String> readFile(String filename) throws ConfigurationException, FileNotFoundException{
        List<String> lines = new ArrayList<>();
        FileReader file = obtainFileByName(filename);
        try(BufferedReader br = new BufferedReader(file)){
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


    private static FileReader obtainFileByName(String filename) throws FileNotFoundException {
        File file = new File(filename);
        if(file.exists()){
            return new FileReader(file);
        } else {
            file = new File( AppConfig.class.getClassLoader().getResource(filename).getFile() );
            if(file.exists()){
                return new FileReader(file);
            } else{
                throw new FileNotFoundException("Cant find file " + filename);
            }
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
