package com.androidapp.nbcg.models;

public class KatalogIzdanja {

    private int id;
    private String datumod;
    private String naslov;
    private String opis;
    private String tekst;
    private String link;
    private double cijena;
    private String tipovi_naslova;

    public KatalogIzdanja(int id, String datumod, String naslov, String opis, String tekst, String link, double cijena, String tipovi_naslova) {
        this.id = id;
        this.datumod = datumod;
        this.naslov = naslov;
        this.opis = opis;
        this.tekst = tekst;
        this.link = link;
        this.cijena = cijena;
        this.tipovi_naslova = tipovi_naslova;
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

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public double getCijena() {
        return cijena;
    }

    public void setCijena(double cijena) {
        this.cijena = cijena;
    }

    public String getTipovi_naslova() {
        return tipovi_naslova;
    }

    public void setTipovi_naslova(String tipovi_naslova) {
        this.tipovi_naslova = tipovi_naslova;
    }
}
