package com.example.android.hackrajasthan.LocalInformation;


import java.util.jar.Attributes;

public class LocalDetails {

    private String AvailableForDifferentlyAbled;
    private String EmailId;
    private String Gender;
    private String Interests;
    private String Language;
    private String PhoneNumber;
    private String Rate;
    private String itinerary;
    private String Name;

    public LocalDetails(String Name,String AvailableForDifferentlyAbled,String EmailId,String Gender,String Interests,String Language,String PhoneNumber,String Rate,String itinerary)
    {
        this.AvailableForDifferentlyAbled=AvailableForDifferentlyAbled;
        this.EmailId=EmailId;
        this.Gender=Gender;
        this.Interests=Interests;
        this.Language=Language;
        this.PhoneNumber=PhoneNumber;
        this.Rate=Rate;
        this.itinerary=itinerary;
        this.Name=Name;
    }

    public String getEmailId() {
        return EmailId;
    }

    public String getGender() {
        return Gender;
    }

    public String getInterests() {
        return Interests;
    }

    public String getLanguage() {
        return Language;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getRate() {
        return Rate;
    }

    public String getItinerary() {
        return itinerary;
    }

    public String getName() {
        return Name;
    }

    public String getAvailableForDifferentlyAbled() {
        return AvailableForDifferentlyAbled;
    }
}
