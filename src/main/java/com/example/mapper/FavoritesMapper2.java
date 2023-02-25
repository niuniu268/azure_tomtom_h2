package com.example.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.Favorites;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FavoritesMapper2 extends BaseMapper <Favorites> {
}
