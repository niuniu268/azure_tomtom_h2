package com.example.controller;

import com.example.pojo.Favorites;
import com.example.service.FavoritesServices2Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/favorites")
public class FavoritesController2 {

    @Autowired
    private TomtomController tomtomController;
    @Autowired
    private FavoritesServices2Impl favoritesServices2;


    @GetMapping
    @ResponseBody
    public List <Favorites> selectAll(){
        return favoritesServices2.selectAll();
    }

    @GetMapping("{no}")
    public Boolean Insert(@PathVariable Integer no){

        Favorites[] fArray = tomtomController.fArray;

        return favoritesServices2.save( fArray[no - 1] );

    }

    @DeleteMapping({"{id}"})
    public Boolean deleteItem(@PathVariable Integer id){
       return favoritesServices2.delete( id );
    }




}
