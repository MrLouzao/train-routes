package com.though.train.exception;

/**
 * Configuration exception
 */
public class ConfigurationException extends Exception{

    private String msg;

    public ConfigurationException(String msg) {
        this.msg = msg;
    }

}
