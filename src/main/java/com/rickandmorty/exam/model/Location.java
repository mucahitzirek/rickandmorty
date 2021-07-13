package com.rickandmorty.exam.model;

import lombok.Data;

import javax.persistence.Entity;
import java.util.List;

@Data
public class Location {


    private int id;

    private String name;

    private String type;

    private String dimension;

    private List<String> residents;

    private String url;

    private String created;

}
