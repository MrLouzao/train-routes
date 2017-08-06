package com.though.train;

import com.though.train.algorithm.StationGraphManager;
import com.though.train.config.AppConfig;
import com.though.train.exception.*;
import com.though.train.model.StationGraph;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ConfigurationException, PathAlreadyExistsException, BadFormatException, FileNotFoundException, PathSearchException {

        if(args.length == 0){
            throw new ConfigurationException("Must specify an input file");
        }

        StationGraphManager manager = setUp(args[0]);

        /** Start here the  execution for the assignment*/
        System.out.println("OUTPUT #1: " + get_distance_route_A_B_C(manager));
        System.out.println("OUTPUT #2: " + get_distance_route_A_D(manager));
        System.out.println("OUTPUT #3: " + get_distance_route_A_D_C(manager));
        System.out.println("OUTPUT #4: " + get_distance_route_A_E_B_C_D(manager));
        System.out.println("OUTPUT #5: " + get_distance_route_A_E_D(manager));
        System.out.println("OUTPUT #6: " + get_number_of_trips_from_C_to_C_with_3_stops_maximum(manager));
        System.out.println("OUTPUT #7: " + get_number_of_trips_from_A_to_C_with_exactly_4_stops(manager));
        System.out.println("OUTPUT #8: " + get_length_of_shortest_path_from_A_to_C(manager));
        System.out.println("OUTPUT #9: " + get_length_of_shortest_path_from_B_to_B(manager));
        System.out.println("OUTPUT #10: " + get_number_of_different_routes_from_C_to_C_with_distance_less_than_30(manager));

    }


     private static StationGraphManager setUp(String filename) throws ConfigurationException, PathAlreadyExistsException, BadFormatException, FileNotFoundException {
         List<String> linesFromFile = AppConfig.readFile(filename);
         StationGraph graph = AppConfig.createStationGraphFromLines(linesFromFile);
         return new StationGraphManager(graph);
     }


    public static String get_distance_route_A_B_C(StationGraphManager manager) {
        try{
            String[] stations = new String[]{"A", "B", "C"};
            Integer distance = manager.getDistanceOfRoute( Arrays.asList(stations) );
            return distance.toString();
        } catch(NotFoundException ex){
            return "NO SUCH ROUTE";
        }
    }


    public static String get_distance_route_A_D(StationGraphManager manager) {
        try{
            String[] stations = new String[]{"A", "D"};
            Integer distance = manager.getDistanceOfRoute( Arrays.asList(stations) );
            return distance.toString();
        } catch(NotFoundException ex){
            return "NO SUCH ROUTE";
        }
    }


    public static String get_distance_route_A_D_C(StationGraphManager manager) {
        try{
            String[] stations = new String[]{"A", "D", "C"};
            Integer distance = manager.getDistanceOfRoute( Arrays.asList(stations) );
            return distance.toString();
        } catch(NotFoundException ex){
            return "NO SUCH ROUTE";
        }
    }


    public static String get_distance_route_A_E_B_C_D(StationGraphManager manager) {
        try{
            String[] stations = new String[]{"A", "E", "B", "C", "D"};
            Integer distance = manager.getDistanceOfRoute( Arrays.asList(stations) );
            return distance.toString();
        } catch(NotFoundException ex){
            return "NO SUCH ROUTE";
        }
    }


    public static String get_distance_route_A_E_D(StationGraphManager manager) {
        try{
            String[] stations = new String[]{"A", "E", "D"};
            Integer distance = manager.getDistanceOfRoute( Arrays.asList(stations) );
            return distance.toString();
        } catch(NotFoundException ex){
            return "NO SUCH ROUTE";
        }
    }


    public static String get_number_of_trips_from_C_to_C_with_3_stops_maximum(StationGraphManager manager) throws PathSearchException {
        try{
            Integer numberOfRoutes = manager.getNumberOfRoutesWithMaxNumberOfStops("C", "C", 3);
            return numberOfRoutes.toString();
        } catch(NotFoundException ex){
            return "NO SUCH ROUTE";
        }
    }


    public static String get_number_of_trips_from_A_to_C_with_exactly_4_stops(StationGraphManager manager) throws PathSearchException {
        try{
            Integer numberOfRoutes = manager.getNumberOfRoutesWithRepeatedNodesAndExactNumberOfStops("A", "C", 4);
            return numberOfRoutes.toString();
        } catch(NotFoundException ex){
            return "NO SUCH ROUTE";
        }
    }


    public static String get_length_of_shortest_path_from_A_to_C(StationGraphManager manager) throws PathSearchException {
        try{
            Integer distance = manager.getShortestPathBetweenStations("A", "C");
            return distance.toString();
        } catch(NotFoundException ex){
            return "NO SUCH ROUTE";
        }
    }


    public static String get_length_of_shortest_path_from_B_to_B(StationGraphManager manager) throws PathSearchException {
        try{
            Integer distance = manager.getShortestPathBetweenStations("B", "B");
            return distance.toString();
        } catch(NotFoundException ex){
            return "NO SUCH ROUTE";
        }
    }


    public static String get_number_of_different_routes_from_C_to_C_with_distance_less_than_30(StationGraphManager manager) throws PathSearchException {
        try{
            Integer numberOfRoutes = manager.getNumberOfRoutesWithRepeatedNodesAndDistanceLimit("C", "C", 30);
            return numberOfRoutes.toString();
        } catch(NotFoundException ex){
            return "NO SUCH ROUTE";
        }
    }


}
