package com.supervilain.gamecrawlin.Model;

import org.springframework.context.annotation.Profile;

/**
 * @author A600413 - Christophe Vilain
 *         09/09/2016
 */
public class ProcessTuple {
    private String key;
    private String value;

    public ProcessTuple() {}

    public ProcessTuple(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
