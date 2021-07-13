package com.rickandmorty.exam.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Character {

    private long id;

    private String name;

    private String status;

    private String species;

    private String type;

    private String gender;

    private Origin origin;

    private LocationCharacter location;

    private String image;

    private List<String> episode = new ArrayList<>();

    private Date created;


}


