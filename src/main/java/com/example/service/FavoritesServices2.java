package com.example.service;

import com.example.pojo.Favorites;

import java.util.List;

public interface FavoritesServices2 {

    List<Favorites> selectAll();

    Boolean save(Favorites favorites);

    Boolean delete(Integer id);
}
