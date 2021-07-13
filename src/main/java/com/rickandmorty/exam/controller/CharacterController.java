package com.rickandmorty.exam.controller;

import com.rickandmorty.exam.config.RickAndMortyGetData;
import com.rickandmorty.exam.model.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CharacterController {

    @Autowired
    RickAndMortyGetData rickAndMortyGetData;

    @GetMapping("/character")
    public List<Character> getCharacter() {
        return rickAndMortyGetData.characterList;
    }

}
