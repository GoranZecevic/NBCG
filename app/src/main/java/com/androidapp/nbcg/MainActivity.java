package com.androidapp.nbcg;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    //menu main titles
    private String str_pocetna;
    private String str_o_nama;
    private String str_katalozi;
    private String str_usluge;
    private String str_kolekcije;
    private String str_dogadjaji;
    private String str_nasa_izdanja;
    private String str_kontakt;

    //menu sub titles
    //o nama
    private String str_uvodna;
    private String str_vizmis;
    private String str_istorija;
    private String str_objekti;
    private String str_organizacija;
    private String str_zv_rad;
    private String str_saradnja;
    private String str_donatori;
    private String str_jav_nab;

    //katalozi
    private String str_virt;
    private String str_ecg;
    private String str_enbcg;
    private String str_cobis;
    private String str_cgbibl;
    private String str_tekbibl;

    //usluge
    private String str_korisnici;
    private String str_izdavaci;
    private String str_bibliotekari;

    //kolekcije
    private String str_osnovni;
    private String str_montenegrina;
    private String str_star_retk;
    private String str_rukopisi;
    private String str_kartog;
    private String str_muzikalije;
    private String str_lik_graf;
    private String str_ser_publ;
    private String str_legati;
    private String str_dig_bibl;

    //nasa izdanja
    private String str_izd_del;
    private String str_katalog;
    private String str_epub;


    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    List<MenuModel> headerList = new ArrayList<>();
    HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>();

    public MainActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Menu titles
        str_pocetna = getString(R.string.title_pocetna);
        str_o_nama = getString(R.string.title_o_nama);
        str_katalozi = getString(R.string.title_katalozi);
        str_usluge = getString(R.string.title_usluge);
        str_kolekcije = getString(R.string.title_kolekcije);
        str_dogadjaji = getString(R.string.title_dogadjaji);
        str_nasa_izdanja = getString(R.string.title_nasa_izdanja);
        str_kontakt = getString(R.string.title_kontakt);

        //Menu sub titles
        // o nama
        str_uvodna = getString(R.string.subt_uvodna);
        str_vizmis = getString(R.string.subt_vizmis);
        str_istorija = getString(R.string.subt_istorija);
        str_objekti = getString(R.string.subt_objekti);
        str_organizacija = getString(R.string.subt_organizacija);
        str_zv_rad = getString(R.string.subt_izv_rad);
        str_saradnja = getString(R.string.subt_saradnja);
        str_donatori = getString(R.string.subt_donatori);
        str_jav_nab = getString(R.string.subt_jav_nab);

        //katalozi
        str_virt = getString(R.string.subt_virt);
        str_ecg = getString(R.string.subt_ecg);
        str_enbcg = getString(R.string.subt_enbcg);
        str_cobis = getString(R.string.subt_cobis);
        str_cgbibl = getString(R.string.subt_cgbibl);
        str_tekbibl = getString(R.string.subt_tekbibl);

        //usluge
        str_korisnici  = getString(R.string.subt_korisnici);
        str_izdavaci  = getString(R.string.subt_izdavaci);
        str_bibliotekari  = getString(R.string.subt_bibliotekari);

        //kolekcije
        str_osnovni  = getString(R.string.subt_osnovni);
        str_montenegrina = getString(R.string.subt_montenegrina);
        str_star_retk = getString(R.string.subt_star_retk);
        str_rukopisi = getString(R.string.subt_rukopisi);
        str_kartog = getString(R.string.subt_kartog);
        str_muzikalije = getString(R.string.subt_muzikalije);
        str_lik_graf = getString(R.string.subt_lik_graf);
        str_ser_publ = getString(R.string.subt_ser_publ);
        str_legati = getString(R.string.subt_legati);
        str_dig_bibl = getString(R.string.subt_dig_bibl);

        //nasa izdanja
        str_izd_del = getString(R.string.subt_izd_del);
        str_katalog = getString(R.string.subt_katalog);
        str_epub = getString(R.string.subt_epub);


        //Mail button action listener
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        expandableListView = findViewById(R.id.expandableListView);
        prepareMenuData();
        populateExpandableList();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else
        if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

//        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void prepareMenuData() {

        //Pocetna
        MenuModel menuModel = new MenuModel( str_pocetna, true, false, "https://www.journaldev.com/9333/android-webview-example-tutorial"); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }

        //O nama
        menuModel = new MenuModel(str_o_nama+"  >", true, true, ""); //Menu of Java Tutorials
        headerList.add(menuModel);

        List<MenuModel> childModelsList = new ArrayList<>();
        MenuModel childModel = new MenuModel(str_uvodna, false, false, "https://www.journaldev.com/7153/core-java-tutorial");
        childModelsList.add(childModel);
        childModel = new MenuModel(str_vizmis, false, false, "https://www.journaldev.com/19187/java-fileinputstream");
        childModelsList.add(childModel);
        childModel = new MenuModel(str_istorija, false, false, "https://www.journaldev.com/19187/java-fileinputstream");
        childModelsList.add(childModel);
        childModel = new MenuModel(str_objekti, false, false, "https://www.journaldev.com/19187/java-fileinputstream");
        childModelsList.add(childModel);
        childModel = new MenuModel(str_organizacija, false, false, "https://www.journaldev.com/19187/java-fileinputstream");
        childModelsList.add(childModel);
        childModel = new MenuModel(str_zv_rad, false, false, "https://www.journaldev.com/19187/java-fileinputstream");
        childModelsList.add(childModel);
        childModel = new MenuModel(str_saradnja, false, false, "https://www.journaldev.com/19187/java-fileinputstream");
        childModelsList.add(childModel);
        childModel = new MenuModel(str_donatori, false, false, "https://www.journaldev.com/19187/java-fileinputstream");
        childModelsList.add(childModel);
        childModel = new MenuModel(str_jav_nab, false, false, "https://www.journaldev.com/19187/java-fileinputstream");
        childModelsList.add(childModel);


        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }

        //Katalozi
        menuModel = new MenuModel(str_katalozi +"  >", true, true, ""); //Menu of Java Tutorials
        headerList.add(menuModel);

        childModelsList = new ArrayList<>();
        childModel = new MenuModel(str_virt, false, false, "https://www.journaldev.com/7153/core-java-tutorial");
        childModelsList.add(childModel);
        childModel = new MenuModel(str_ecg, false, false, "https://www.journaldev.com/19187/java-fileinputstream");
        childModelsList.add(childModel);
        childModel = new MenuModel(str_enbcg, false, false, "https://www.journaldev.com/19187/java-fileinputstream");
        childModelsList.add(childModel);
        childModel = new MenuModel(str_cobis, false, false, "https://www.journaldev.com/19187/java-fileinputstream");
        childModelsList.add(childModel);
        childModel = new MenuModel(str_cgbibl, false, false, "https://www.journaldev.com/19187/java-fileinputstream");
        childModelsList.add(childModel);
        childModel = new MenuModel(str_tekbibl, false, false, "https://www.journaldev.com/19187/java-fileinputstream");
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }

        //Usluge
        menuModel = new MenuModel(str_usluge + "  >", true, true, ""); //Menu of Java Tutorials
        headerList.add(menuModel);

        childModelsList = new ArrayList<>();
        childModel = new MenuModel(str_korisnici, false, false, "https://www.journaldev.com/7153/core-java-tutorial");
        childModelsList.add(childModel);
        childModel = new MenuModel(str_izdavaci, false, false, "https://www.journaldev.com/7153/core-java-tutorial");
        childModelsList.add(childModel);
        childModel = new MenuModel(str_bibliotekari, false, false, "https://www.journaldev.com/7153/core-java-tutorial");
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }

        //Kolekcije
        menuModel = new MenuModel(str_kolekcije + "  >", true, true, ""); //Menu of Java Tutorials
        headerList.add(menuModel);

        childModelsList = new ArrayList<>();
        childModel = new MenuModel(str_osnovni, false, false, "https://www.journaldev.com/7153/core-java-tutorial");
        childModelsList.add(childModel);
        childModel = new MenuModel(str_montenegrina, false, false, "https://www.journaldev.com/7153/core-java-tutorial");
        childModelsList.add(childModel);
        childModel = new MenuModel(str_star_retk, false, false, "https://www.journaldev.com/7153/core-java-tutorial");
        childModelsList.add(childModel);
        childModel = new MenuModel(str_rukopisi, false, false, "https://www.journaldev.com/7153/core-java-tutorial");
        childModelsList.add(childModel);
        childModel = new MenuModel(str_kartog, false, false, "https://www.journaldev.com/7153/core-java-tutorial");
        childModelsList.add(childModel);
        childModel = new MenuModel(str_muzikalije, false, false, "https://www.journaldev.com/7153/core-java-tutorial");
        childModelsList.add(childModel);
        childModel = new MenuModel(str_lik_graf, false, false, "https://www.journaldev.com/7153/core-java-tutorial");
        childModelsList.add(childModel);
        childModel = new MenuModel(str_ser_publ, false, false, "https://www.journaldev.com/7153/core-java-tutorial");
        childModelsList.add(childModel);
        childModel = new MenuModel(str_legati, false, false, "https://www.journaldev.com/7153/core-java-tutorial");
        childModelsList.add(childModel);
        childModel = new MenuModel(str_dig_bibl, false, false, "https://www.journaldev.com/7153/core-java-tutorial");
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }

        //Dogadjaji
        menuModel = new MenuModel(str_dogadjaji, true, false, "https://www.journaldev.com/9333/android-webview-example-tutorial"); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }

        //Nasa izdanja
        menuModel = new MenuModel(str_nasa_izdanja + "  >", true, true, ""); //Menu of Java Tutorials
        headerList.add(menuModel);

        childModelsList = new ArrayList<>();
        childModel = new MenuModel(str_izd_del, false, false, "https://www.journaldev.com/7153/core-java-tutorial");
        childModelsList.add(childModel);
        childModel = new MenuModel(str_katalog, false, false, "https://www.journaldev.com/7153/core-java-tutorial");
        childModelsList.add(childModel);
        childModel = new MenuModel(str_epub, false, false, "https://www.journaldev.com/7153/core-java-tutorial");
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }

        //Kontakt
        menuModel = new MenuModel(str_kontakt, true, false, "https://www.journaldev.com/9333/android-webview-example-tutorial"); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }
    }

    private void populateExpandableList() {

        expandableListAdapter = new ExpandableListAdapter(this, headerList, childList);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                if (headerList.get(groupPosition).isGroup) {
                    if (!headerList.get(groupPosition).hasChildren) {
                        WebView webView = findViewById(R.id.webView);
                        webView.loadUrl(headerList.get(groupPosition).url);
                        onBackPressed();
                    }
                }

                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                if (childList.get(headerList.get(groupPosition)) != null) {
                    MenuModel model = childList.get(headerList.get(groupPosition)).get(childPosition);
                    if (model.url.length() > 0) {
                        WebView webView = findViewById(R.id.webView);
                        webView.loadUrl(model.url);
                        onBackPressed();
                    }
                }

                return false;
            }
        });
    }
}
