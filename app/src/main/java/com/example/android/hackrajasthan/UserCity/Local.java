package com.example.android.hackrajasthan.UserCity;




public class Local {

    String Name;
    String About;
    String ImageUrl;

    public Local(String Name,String About,String ImageUrl)
    {
        this.Name=Name;
        this.About=About;
        this.ImageUrl=ImageUrl;

    }
   /** public Local(String Name,String About)
    {
        this.Name=Name;
        this.About=About;

    }**/

    public String getName()
    {
        return Name;
    }

    public String getAbout()
    {
        return About;
    }

    public String getImageUrl()
    {
        return ImageUrl;
    }
}
