package com.example.oguzc.projectshop;

/**
 * Created by oguzc on 12/18/2015.
 */
public class Dvd {

    private String id;
    private String type;
    private String name;
    private String director;
    private String time;
    private String year;

    public Dvd(String id, String type, String name, String director, String time, String year) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.director = director;
        this.time = time;
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
