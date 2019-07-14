package com.androidapp.nbcg.models;

public class JavneNabavke {
    int id;
    String naslov;
    String fajl;

    public JavneNabavke(int id, String naslov, String fajl) {
        this.id = id;
        this.naslov = naslov;
        this.fajl = fajl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
