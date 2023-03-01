package com.example.service;

import com.example.pojo.Favorites;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FavoritesServices2 {

    List<Favorites> selectAll();
    @Transactional
    Boolean save(Favorites favorites);
    @Transactional
    Boolean delete(Integer id);
}
