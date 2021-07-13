package com.rickandmorty.exam.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Data
public class Episode {

    private String name;

    private String air_date;

    private String url;

    private String created;

    private String episode;

    private List<String> character = new ArrayList<>();

}
