package com.example.android.hackrajasthan.CityPart;


public class City {

    public String cityName;
    public String cityAboutMe;
    public String imageUrl;

    public City()
    {

    }
    public String getCityName()
    {
        return cityName;
    }
    public City(String cityName,String cityAboutMe,String imageUrl)
    {
        this.cityName=cityName;
        this.cityAboutMe=cityAboutMe;
        this.imageUrl=imageUrl;
    }
    public String getCityAboutMe()
    {
        return cityAboutMe;
    }
    public String getImageUrl()
    {
        return imageUrl;
    }
}
