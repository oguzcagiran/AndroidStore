package com.example.oguzc.projectshop;

/**
 * Created by oguzc on 12/18/2015.
 */
public class Cd {

    private String id;
    private String type;
    private String name;
    private String artist;
    private String cdNumber;

    public Cd(String id, String type, String name, String artist, String cdNumber) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.artist = artist;
        this.cdNumber = cdNumber;
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

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getCdNumber() {
        return cdNumber;
    }

    public void setCdNumber(String cdNumber) {
        this.cdNumber = cdNumber;
    }
}
