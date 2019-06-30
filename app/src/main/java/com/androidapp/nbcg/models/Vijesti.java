package com.androidapp.nbcg.models;

import java.util.Date;

public class Vijesti {

    private int id;
    private String datumod;
    private String naslov;
    private String opis;
    private String description;
    private String tip_novosti;
    private String link;
    private String fajl;




    public Vijesti(int id, String datumod, String naslov, String opis, String description, String tip_novosti, String fajl, String link ) {
        this.id = id;
        this.datumod = datumod;
        this.naslov = naslov;
        this.opis = opis;
        this.description = description;
        this.tip_novosti = tip_novosti;
        this.link = link;
        this.fajl = fajl;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDatumod() {
        return datumod;
    }

    public void setDatumod(String datumod) {
        this.datumod = datumod;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTip_novosti() {
        return tip_novosti;
    }

    public void setTip_novosti(String tip_novosti) {
        this.tip_novosti = tip_novosti;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getFajl() {
        return fajl;
    }

    public void setFajl(String fajl) {
        this.fajl = fajl;
    }
}
