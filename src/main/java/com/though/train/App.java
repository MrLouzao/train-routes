package com.though.train;

import com.sun.applet2.preloader.event.ConfigEvent;
import com.though.train.exception.ConfigurationException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ConfigurationException
    {

        if(args.length == 0){
            throw new ConfigurationException("Must specify an input file");
        }

        System.out.println("File: " + args[0]);



    }
}
