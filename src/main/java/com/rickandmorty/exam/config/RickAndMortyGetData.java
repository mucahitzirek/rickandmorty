package com.rickandmorty.exam.config;

import com.rickandmorty.exam.model.*;
import com.rickandmorty.exam.model.Character;
import com.rickandmorty.exam.utils.Utils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class RickAndMortyGetData {

    Info infoModel = new Info();
    Character characterModel = null;
    public List<Character> characterList = new ArrayList<>();
    public Origin originModel = new Origin();
    public LocationCharacter locationCharacterModel = new LocationCharacter();
    Location locationModel = new Location();
    public List<Location> locationList = new ArrayList<>();
    List<String> residentsList = new ArrayList<>();
    Episode episodeModel = new Episode();
    public List<Episode> episodeList = new ArrayList<>();
    List<String> listEpisodeCharacterUrl = new ArrayList<>();
    List<String> characters = null;


    @PostConstruct
    public void init() throws IOException, ParseException {

        JSONObject responseCharacter = Utils.GET_API("https://rickandmortyapi.com/api/character");
        JSONObject responseLocation = Utils.GET_API("https://rickandmortyapi.com/api/location");
        JSONObject responseEpisode = Utils.GET_API("https://rickandmortyapi.com/api/episode");

        JSONObject data = (JSONObject) responseCharacter.get("info");
        JSONArray characterArray = (JSONArray) responseCharacter.get("results");
        JSONArray location = (JSONArray) responseLocation.get("results");
        JSONArray episode = (JSONArray) responseEpisode.get("results");

        //info Model
        infoModel.setCount(data.get("count").toString());
        infoModel.setPages(data.get("pages").toString());
        infoModel.setNext(data.get("next").toString());

        //Character Model
        for (int i = 0; i < characterArray.size(); i++) {
            characterModel = new Character();
            characters = new ArrayList<>();
            characters.clear();
            characterModel.setId(Long.parseLong(((JSONObject) characterArray.get(i)).get("id").toString()));
            characterModel.setName(((JSONObject) characterArray.get(i)).get("name").toString());
            characterModel.setStatus(((JSONObject) characterArray.get(i)).get("status").toString());
            characterModel.setSpecies(((JSONObject) characterArray.get(i)).get("species").toString());
            characterModel.setType(((JSONObject) characterArray.get(i)).get("type").toString());
            characterModel.setGender(((JSONObject) characterArray.get(i)).get("gender").toString());

            JSONObject origin = (JSONObject) ((JSONObject) characterArray.get(i)).get("origin");

            originModel.setName(origin.get("name").toString());
            originModel.setUrl(origin.get("url").toString());
            characterModel.setOrigin(originModel);

            JSONObject locationCharacter = (JSONObject) ((JSONObject) characterArray.get(i)).get("location");

            for (int l = 0; l < locationCharacter.size(); l++) {
                locationCharacterModel.setName(locationCharacter.get("name").toString());
                locationCharacterModel.setUrl(locationCharacter.get("url").toString());
                characterModel.setLocation(locationCharacterModel);
            }
            characterModel.setImage(((JSONObject) characterArray.get(i)).get("image").toString());

            JSONArray episodCharacter = (JSONArray) ((JSONObject) characterArray.get(i)).get("episode");

            for (int j = 0; j < episodCharacter.size(); j++) {
                characters.add((String) episodCharacter.get(j));
            }
            characterModel.setEpisode(characters);
            characterList.add(characterModel);

        }
        System.out.println(characterList);

        //LocationModel
        for (int j = 0; j < location.size(); j++) {
            residentsList.clear();
            locationModel.setId(Integer.parseInt(((JSONObject) location.get(j)).get("id").toString()));
            locationModel.setType(((JSONObject) location.get(j)).get("type").toString());
            locationModel.setName(((JSONObject) location.get(j)).get("name").toString());
            JSONArray locationResidents = (JSONArray) ((JSONObject) location.get(j)).get("residents");
            for (int i = 0; i < locationResidents.size(); i++) {
                residentsList.add(locationResidents.get(i).toString());
            }

            locationModel.setResidents(residentsList);
            locationModel.setUrl(((JSONObject) location.get(j)).get("url").toString());
            locationModel.setCreated(((JSONObject) location.get(j)).get("created").toString());
            locationList.add(locationModel);
        }

        //Episode Model
        for (int i = 0; i < episode.size(); i++) {
            listEpisodeCharacterUrl.clear();
            JSONArray episodeCharacterArray = (JSONArray) ((JSONObject) episode.get(i)).get("characters");

            episodeModel.setName(((JSONObject) episode.get(i)).get("name").toString());
            episodeModel.setAir_date(((JSONObject) episode.get(i)).get("air_date").toString());
            episodeModel.setEpisode(((JSONObject) episode.get(i)).get("episode").toString());

            for (int e = 0; e < episodeCharacterArray.size(); e++) {
                listEpisodeCharacterUrl.add(episodeCharacterArray.get(e).toString());
            }

            episodeModel.setCharacter(listEpisodeCharacterUrl);
            episodeModel.setUrl(((JSONObject) episode.get(i)).get("url").toString());
            episodeModel.setCreated(((JSONObject) episode.get(i)).get("created").toString());
            episodeList.add(episodeModel);

        }

    }

}