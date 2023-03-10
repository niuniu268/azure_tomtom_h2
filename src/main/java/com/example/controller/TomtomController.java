package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.example.pojo.Address;
import com.example.pojo.Favorites;
import com.example.pojo.Latitude;
import com.example.service.TomtomServicesImpl;
//import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

@RestController
@RequestMapping("/search")
public class TomtomController {
    @Autowired
    private TomtomServicesImpl tomtomServices;

    Favorites[] fArray = new Favorites[3];




    @GetMapping("/{start}/{destination}/{way}")
    @ResponseBody
    public Map[] searchPoint(@PathVariable String start, @PathVariable String destination, @PathVariable String way, HttpServletRequest httpServletRequest) throws URISyntaxException {


        Map[] arraylist = new Map[4];

        boolean matches = destination.matches( "(\\w*\\s)*station$" );
        System.out.println(matches );

        if(matches==true){
            arraylist[3] = tomtomServices.getCommunalData(start, destination);
        }


        String latitude = tomtomServices.getLatitude( start );

        String latitude1 = tomtomServices.getLatitude( destination );

        Map pedestrian = tomtomServices.itinerary( latitude, latitude1, "pedestrian", 0 );

        arraylist[0] = pedestrian;
        fArray[0] = tomtomServices.fillInFavoritesList( start, destination, "pedestrian", 0 );

        if (!Objects.equals( way, "pedestrian" )){

            Map car1 = tomtomServices.itinerary( latitude, latitude1, "car",0 );

            arraylist[1] = car1;
            fArray[1] = tomtomServices.fillInFavoritesList( start, destination, "car", 0 );

            Map car2 = tomtomServices.itinerary( latitude, latitude1, "car",1 );

            arraylist[2] = car2;
            fArray[2] = tomtomServices.fillInFavoritesList( start, destination, "car", 1 );

        }

//        for (Map map : arraylist) {
//
//            System.out.println(map );
//
//        }
//
//        for (int i = 0; i < fArray.length; i++) {
//
//            System.out.println(fArray[i] );
//
//        }


        return arraylist;


    }


    @GetMapping("/abbe/{start}/{destination}")
    @ResponseBody
    public ResponseEntity<Map> searchStation(@PathVariable String start, @PathVariable String destination, HttpServletRequest request ) throws URISyntaxException {

        String header = request.getHeader( "x-api-key" );
        String abbe = "Abbe";
        int i = abbe.hashCode( );

        if(!header.equals( i+"" )){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        String latitude = tomtomServices.getLatitude( start );

        String latitude1 = tomtomServices.getLatitude( destination );

        Map pedestrian = tomtomServices.itinerary( latitude, latitude1, "pedestrian", 0 );


        HttpHeaders headers = new HttpHeaders( );
        headers.add( "x-api-key", i+"" );

        ResponseEntity <Map> entity = new ResponseEntity <>(pedestrian, headers, HttpStatus.ACCEPTED );


        return entity;

    }


}
