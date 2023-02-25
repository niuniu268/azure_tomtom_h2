package com.example.service;

import com.example.mapper.FavoritesMapper2;
import com.example.pojo.Favorites;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.List;
@Service
public class FavoritesServices2Impl implements FavoritesServices2{
@Autowired
    private FavoritesMapper2 favoritesMapper2;


    @Override
    public List <Favorites> selectAll () {
        return favoritesMapper2.selectList( null );
    }

    @Override
    public Boolean save (Favorites favorites) {
        return favoritesMapper2.insert( favorites ) > 0;
    }

    @Override
    public Boolean delete (Integer id) {
        return favoritesMapper2.deleteById( id ) > 0;
    }
}
