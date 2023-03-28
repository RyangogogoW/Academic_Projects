package com.example.thecaps;

import java.util.List;
import java.util.Map;

import ca.roumani.i2c.Country;
import ca.roumani.i2c.CountryDB;

public class Game {
    private CountryDB db;

    public Game(){
        this.db= new CountryDB();
    }

    public String qa(){
        List<String> capitals=db.getCapitals();
        int n = capitals.size();
        int index=(int)(n*Math.random());
        String c=capitals.get(index);
        Map<String, Country> data = db.getData();
        Country ref=data.get(c);

        String countryName=ref.getName();
        String capitalName= ref.getCapital();

        if(Math.random()<0.5)
            return "What is the capital of "+countryName+"?"+"\n"+capitalName;
        else
            return capitalName+" is the capital of? \n"+countryName;

    }
}
