package com.androidapp.nbcg.models;

public class Slike {
    private String naslov;
    private String fajl;


    public Slike(String naslov, String fajl) {
        this.naslov = naslov;
        this.fajl = fajl;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getFajl() {
        return fajl;
    }

    public void setFajl(String fajl) {
        this.fajl = fajl;
    }
}
