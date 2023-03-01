package com.example.service;

import com.alibaba.fastjson.JSON;
import com.example.pojo.*;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TomtomServicesImpl {
    @Autowired
    private RestTemplate restTemplate;

    @Value( "${key}" )
    private String key ;



    public Favorites fillInFavoritesList(String start, String destination, String way, Integer routeType) throws URISyntaxException {

        String latitude = getLatitude( start );

        String latitude1 = getLatitude( destination );

        Map itinerary = itinerary( latitude, latitude1, way, routeType );

        Object routes = itinerary.get( "routes" );
        String s = JSON.toJSONString( routes );

        List <Routes> routes1 = JSON.parseArray( s, Routes.class );

        Routes routes2 = routes1.get( 0 );

        Travels summary = routes2.getSummary( );

        Favorites data = new Favorites();

        data.setStart( start );
        data.setDestination( destination );
        data.setWay( way );
        data.setDistance( summary.getLengthInMeters() );
        data.setDuration( summary.getTravelTimeInSeconds() );

        return data;
    }


    public Map itinerary (String start, String destination, String way, Integer routeType) throws URISyntaxException {

        String uri;

        if (routeType == 0){
             uri = "https://api.tomtom.com/routing/1/calculateRoute/"+start+":"+destination+"/json?language=en-US&instructionsType=text&travelMode="+way+"&key="+key;
        } else {
             uri = "https://api.tomtom.com/routing/1/calculateRoute/"+start+":"+destination+"/json?language=en-US&instructionsType=text&routeType=eco&travelMode="+way+"&key="+key;

        }

        ResponseEntity <Map> entity = restTemplate.getForEntity( new URI( uri ), Map.class );



        return entity.getBody();
    }



    public String getLatitude(String address) throws URISyntaxException {


        String naddress = address.replaceAll( "\\s+", "%20" );


        String uri = "https://api.tomtom.com/search/2/geocode/"+naddress+"&countrySet=SWE.json?key="+key;

        ResponseEntity <Map> entity = restTemplate.getForEntity( new URI( uri ), Map.class );


        Map body = entity.getBody( );

        Object results = body.get( "results" );


        String s = JSON.toJSONString( results );

        List <Address> list = JSON.parseArray( s, Address.class );

        Address address1 = list.get( 0 );

        Latitude position = address1.getPosition( );


        return position.getLat()+","+position.getLon();

    }

    public Map getCommunalData(String start, String destination){

        String[] split1 = start.split( "\\s" );
        String[] split2 = destination.split( "\\s" );

        System.out.println(split1[0] );
        System.out.println(split2[0] );

        String uri = "https://komtrafficapp-komtrafficapplication.azuremicroservices.io/search/"+split1[0]+"/"+split2[0];

        LinkedMultiValueMap <String, String> valueMap = new LinkedMultiValueMap <>( );
        valueMap.add( "x-api-key","2033732" );

        ResponseEntity <Object> exchange = restTemplate.exchange( uri, HttpMethod.GET, new HttpEntity <>( valueMap ), Object.class );

        System.out.println(exchange );

        Object body = exchange.getBody( );

        String s = body.toString( );

        String substring = s.substring( 1, s.length( ) - 1 );

        Map <String, String> map = Stream.of( substring.split( "," ) ).
                map( se -> se.split( "=" ) ).
                collect( Collectors.toMap( arr -> arr[0], arr -> arr[1] ) );

        return map;

    }
}
