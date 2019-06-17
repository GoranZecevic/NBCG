package com.androidapp.nbcg;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import com.androidapp.nbcg.fragments.Dogadjaji;
import com.androidapp.nbcg.fragments.Katalozi.COBISS;
import com.androidapp.nbcg.fragments.Katalozi.CgBibliografija;
import com.androidapp.nbcg.fragments.Katalozi.ECG;
import com.androidapp.nbcg.fragments.Katalozi.ENBCG;
import com.androidapp.nbcg.fragments.Katalozi.TekucaBibliografija;
import com.androidapp.nbcg.fragments.Katalozi.VirtuelnaBiblioteka;
import com.androidapp.nbcg.fragments.Kolekcije.DigitalnaBiblioteka;
import com.androidapp.nbcg.fragments.Kolekcije.KartografskaZbirka;
import com.androidapp.nbcg.fragments.Kolekcije.Legati;
import com.androidapp.nbcg.fragments.Kolekcije.LikovnoGraficka;
import com.androidapp.nbcg.fragments.Kolekcije.Montenegrina;
import com.androidapp.nbcg.fragments.Kolekcije.MuzikalijeAudiovizuelna;
import com.androidapp.nbcg.fragments.Kolekcije.OsnovniFond;
import com.androidapp.nbcg.fragments.Kolekcije.RukopisiArhivalije;
import com.androidapp.nbcg.fragments.Kolekcije.SerijskePublikacije;
import com.androidapp.nbcg.fragments.Kolekcije.StareRijetkeKnjige;
import com.androidapp.nbcg.fragments.Kontakt;
import com.androidapp.nbcg.fragments.Nasa_Izdanja.E_Publikacije;
import com.androidapp.nbcg.fragments.Nasa_Izdanja.IzdavackaDjelatnost;
import com.androidapp.nbcg.fragments.Nasa_Izdanja.KatalogIzdanja;
import com.androidapp.nbcg.fragments.O_Nama.DonatoriPrijatelji;
import com.androidapp.nbcg.fragments.O_Nama.Istorija;
import com.androidapp.nbcg.fragments.O_Nama.IzvestajiRada;
import com.androidapp.nbcg.fragments.O_Nama.JavneNabavke;
import com.androidapp.nbcg.fragments.O_Nama.Objekti;
import com.androidapp.nbcg.fragments.O_Nama.Organizacija;
import com.androidapp.nbcg.fragments.O_Nama.Saradnja;
import com.androidapp.nbcg.fragments.Pocetna;
import com.androidapp.nbcg.fragments.O_Nama.UvodnaRijec;
import com.androidapp.nbcg.fragments.O_Nama.VizijaMisija;
import com.androidapp.nbcg.fragments.Usluge.ZaBibliotekare;
import com.androidapp.nbcg.fragments.Usluge.ZaIzdavace;
import com.androidapp.nbcg.fragments.Usluge.ZaKorisnike;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    //region menu main titles
    private String str_pocetna;
    private String str_o_nama;
    private String str_katalozi;
    private String str_usluge;
    private String str_kolekcije;
    private String str_dogadjaji;
    private String str_nasa_izdanja;
    private String str_kontakt;
    //endregion menu main titles

    //menu sub titles
    //region o nama
    private String str_uvodna;
    private String str_vizmis;
    private String str_istorija;
    private String str_objekti;
    private String str_organizacija;
    private String str_zv_rad;
    private String str_saradnja;
    private String str_donatori;
    private String str_jav_nab;
    //endregion o nama

    //region katalozi
    private String str_virt;
    private String str_ecg;
    private String str_enbcg;
    private String str_cobis;
    private String str_cgbibl;
    private String str_tekbibl;
    //endregion katalozi

    //region usluge
    private String str_korisnici;
    private String str_izdavaci;
    private String str_bibliotekari;
    //endregion usluge

    //region kolekcije
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
    //endregion kolekcije

    //region nasa izdanja
    private String str_izd_del;
    private String str_katalog;
    private String str_epub;
    //endregion nasa izdanja


    View view_Group;

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

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Pocetna()).commit();
        }

        //region Menu titles
        str_pocetna = getString(R.string.title_pocetna);
        str_o_nama = getString(R.string.title_o_nama);
        str_katalozi = getString(R.string.title_katalozi);
        str_usluge = getString(R.string.title_usluge);
        str_kolekcije = getString(R.string.title_kolekcije);
        str_dogadjaji = getString(R.string.title_dogadjaji);
        str_nasa_izdanja = getString(R.string.title_nasa_izdanja);
        str_kontakt = getString(R.string.title_kontakt);
        //endregion Menu titles

        //Menu sub titles
        //region o nama
        str_uvodna = getString(R.string.subt_uvodna);
        str_vizmis = getString(R.string.subt_vizmis);
        str_istorija = getString(R.string.subt_istorija);
        str_objekti = getString(R.string.subt_objekti);
        str_organizacija = getString(R.string.subt_organizacija);
        str_zv_rad = getString(R.string.subt_izv_rad);
        str_saradnja = getString(R.string.subt_saradnja);
        str_donatori = getString(R.string.subt_donatori);
        str_jav_nab = getString(R.string.subt_jav_nab);
        //endregion o nama

        //region katalozi
        str_virt = getString(R.string.subt_virt);
        str_ecg = getString(R.string.subt_ecg);
        str_enbcg = getString(R.string.subt_enbcg);
        str_cobis = getString(R.string.subt_cobis);
        str_cgbibl = getString(R.string.subt_cgbibl);
        str_tekbibl = getString(R.string.subt_tekbibl);
        //endregion katalozi

        //region usluge
        str_korisnici  = getString(R.string.subt_korisnici);
        str_izdavaci  = getString(R.string.subt_izdavaci);
        str_bibliotekari  = getString(R.string.subt_bibliotekari);
        //endregion usluge

        //region kolekcije
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
        //endregion kolekcije

        //region nasa izdanja
        str_izd_del = getString(R.string.subt_izd_del);
        str_katalog = getString(R.string.subt_katalog);
        str_epub = getString(R.string.subt_epub);
        //endregion nasa izdanja


//        //Mail button action listener
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });



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
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
////        if (id == R.id.nav_camera) {
////            // Handle the camera action
////        } else
//        if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
////        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void prepareMenuData() {

        //region Pocetna
        MenuModel menuModel = new MenuModel( str_pocetna, true, false, R.drawable.ic_house);
        headerList.add(menuModel);

        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }
        //endregion Pocetna

        //region O nama
        menuModel = new MenuModel(str_o_nama+"  >", true, true, R.drawable.ic_team);
        headerList.add(menuModel);

        List<MenuModel> childModelsList = new ArrayList<>();
        MenuModel childModel = new MenuModel(str_uvodna, false, false, 0);
        childModelsList.add(childModel);
        childModel = new MenuModel(str_vizmis, false, false, 0);
        childModelsList.add(childModel);
        childModel = new MenuModel(str_istorija, false, false, 0);
        childModelsList.add(childModel);
        childModel = new MenuModel(str_objekti, false, false, 0);
        childModelsList.add(childModel);
        childModel = new MenuModel(str_organizacija, false, false, 0);
        childModelsList.add(childModel);
        childModel = new MenuModel(str_zv_rad, false, false, 0);
        childModelsList.add(childModel);
        childModel = new MenuModel(str_saradnja, false, false, 0);
        childModelsList.add(childModel);
        childModel = new MenuModel(str_donatori, false, false, 0);
        childModelsList.add(childModel);
        childModel = new MenuModel(str_jav_nab, false, false, 0);
        childModelsList.add(childModel);


        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }
        //endregion O nama

        //region Katalozi
        menuModel = new MenuModel(str_katalozi +"  >", true, true, R.drawable.ic_catalog);
        headerList.add(menuModel);

        childModelsList = new ArrayList<>();
        childModel = new MenuModel(str_virt, false, false, 0);
        childModelsList.add(childModel);
        childModel = new MenuModel(str_ecg, false, false, 0);
        childModelsList.add(childModel);
        childModel = new MenuModel(str_enbcg, false, false, 0);
        childModelsList.add(childModel);
        childModel = new MenuModel(str_cobis, false, false, 0);
        childModelsList.add(childModel);
        childModel = new MenuModel(str_cgbibl, false, false, 0);
        childModelsList.add(childModel);
        childModel = new MenuModel(str_tekbibl, false, false, 0);
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }
        //endregion Katalozi

        //region Usluge
        menuModel = new MenuModel(str_usluge + "  >", true, true, R.drawable.ic_customer);
        headerList.add(menuModel);

        childModelsList = new ArrayList<>();
        childModel = new MenuModel(str_korisnici, false, false, 0);
        childModelsList.add(childModel);
        childModel = new MenuModel(str_izdavaci, false, false, 0);
        childModelsList.add(childModel);
        childModel = new MenuModel(str_bibliotekari, false, false, 0);
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }
        //endregion Usluge

        //region Kolekcije
        menuModel = new MenuModel(str_kolekcije + "  >", true, true, R.drawable.ic_books_stack_of_three);
        headerList.add(menuModel);

        childModelsList = new ArrayList<>();
        childModel = new MenuModel(str_osnovni, false, false, 0);
        childModelsList.add(childModel);
        childModel = new MenuModel(str_montenegrina, false, false, 0);
        childModelsList.add(childModel);
        childModel = new MenuModel(str_star_retk, false, false, 0);
        childModelsList.add(childModel);
        childModel = new MenuModel(str_rukopisi, false, false, 0);
        childModelsList.add(childModel);
        childModel = new MenuModel(str_kartog, false, false, 0);
        childModelsList.add(childModel);
        childModel = new MenuModel(str_muzikalije, false, false, 0);
        childModelsList.add(childModel);
        childModel = new MenuModel(str_lik_graf, false, false, 0);
        childModelsList.add(childModel);
        childModel = new MenuModel(str_ser_publ, false, false, 0);
        childModelsList.add(childModel);
        childModel = new MenuModel(str_legati, false, false, 0);
        childModelsList.add(childModel);
        childModel = new MenuModel(str_dig_bibl, false, false, 0);
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }
        //endregion Kolekcije

        //region Dogadjaji
        menuModel = new MenuModel(str_dogadjaji, true, false, R.drawable.ic_calendar);
        headerList.add(menuModel);

        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }
        //endregion Dogadjaji

        //region Nasa izdanja
        menuModel = new MenuModel(str_nasa_izdanja + "  >", true, true, R.drawable.ic_library);
        headerList.add(menuModel);

        childModelsList = new ArrayList<>();
        childModel = new MenuModel(str_izd_del, false, false, 0);
        childModelsList.add(childModel);
        childModel = new MenuModel(str_katalog, false, false, 0);
        childModelsList.add(childModel);
        childModel = new MenuModel(str_epub, false, false, 0);
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }
        //endregion Nasa izdanja

        //region Kontakt
        menuModel = new MenuModel(str_kontakt, true, false, R.drawable.ic_contact);
        headerList.add(menuModel);

        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }
        //endregion Kontakt
    }

    private void populateExpandableList() {

        expandableListAdapter = new ExpandableListAdapter(this, headerList, childList);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(final ExpandableListView parent, View v, int groupPosition, long id) {

                if (headerList.get(groupPosition).isGroup) {
                    if (!headerList.get(groupPosition).hasChildren) {
//                        WebView webView = findViewById(R.id.webView);
//                        webView.loadUrl(headerList.get(groupPosition).url);
                        onBackPressed();

                        switch (groupPosition) {
                            //region Pocetna
                            case 0:
                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                        new Pocetna()).commit();
                                groupPosition = -1;
                                getSupportActionBar().setTitle(str_pocetna); break;
                            // endregion Pocetna
                            //region Dogadjaji
                            case 5:
                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                        new Dogadjaji()).commit();
                                groupPosition = -1;
                                getSupportActionBar().setTitle(str_dogadjaji); break;
                            //endregion Dogadjaji
                            //region Kontakt
                            case 7:
                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                        new Kontakt()).commit();
                                groupPosition = -1;
                                getSupportActionBar().setTitle(str_kontakt); break;
                            //endregion Kontakt
                        }
                    }
                }

                return false;
            }
        });


        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                v.setSelected(true);
                if (view_Group != null) {
                    view_Group.setBackgroundColor(Color.parseColor("#ffffff"));
                }
                view_Group = v;
                view_Group.setBackgroundColor(Color.parseColor("#F21E1E"));

                if (childList.get(headerList.get(groupPosition)) != null) {
//                    MenuModel model = childList.get(headerList.get(groupPosition)).get(childPosition);

//                    if (model.url.length() > 0) {
//                        WebView webView = findViewById(R.id.webView);
//                        webView.loadUrl(model.url);
//                        onBackPressed();

                        switch (groupPosition){
                            //region O nama
                            case 1:
                                switch (childPosition){
                                    case 0:
                                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                                new UvodnaRijec()).commit();
                                        onBackPressed();
                                        getSupportActionBar().setTitle(str_uvodna);
                                        childPosition = -1 ;  groupPosition = -1; break;
                                    case 1:
                                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                                new VizijaMisija()).commit();
                                        onBackPressed();
                                        getSupportActionBar().setTitle(str_vizmis);
                                        childPosition = -1 ;  groupPosition = -1; break;
                                    case 2:
                                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                                new Istorija()).commit();
                                        onBackPressed();
                                        getSupportActionBar().setTitle(str_istorija);
                                        childPosition = -1 ;  groupPosition = -1; break;
                                    case 3:
                                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                                new Objekti()).commit();
                                        onBackPressed();
                                        getSupportActionBar().setTitle(str_objekti);
                                        childPosition = -1 ;  groupPosition = -1; break;
                                    case 4:
                                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                                new Organizacija()).commit();
                                        onBackPressed();
                                        getSupportActionBar().setTitle(str_organizacija);
                                        childPosition = -1 ;  groupPosition = -1; break;
                                    case 5:
                                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                                new IzvestajiRada()).commit();
                                        onBackPressed();
                                        getSupportActionBar().setTitle(str_zv_rad);
                                        childPosition = -1 ;  groupPosition = -1; break;
                                    case 6:
                                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                                new Saradnja()).commit();
                                        onBackPressed();
                                        getSupportActionBar().setTitle(str_saradnja);
                                        childPosition = -1 ;  groupPosition = -1; break;
                                    case 7:
                                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                                new DonatoriPrijatelji()).commit();
                                        onBackPressed();
                                        getSupportActionBar().setTitle(str_donatori);
                                        childPosition = -1 ;  groupPosition = -1; break;
                                    case 8:
                                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                                new JavneNabavke()).commit();
                                        onBackPressed();
                                        getSupportActionBar().setTitle(str_jav_nab);
                                        childPosition = -1 ;  groupPosition = -1; break;
                                }
                                //endregion O nama
                            //region  Katalozi
                            case 2:
                                switch (childPosition){
                                    case 0:
                                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                                new VirtuelnaBiblioteka()).commit();
                                        onBackPressed();
                                        getSupportActionBar().setTitle(str_virt);
                                        childPosition = -1 ;  groupPosition = -1; break;
                                    case 1:
                                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                                new ECG()).commit();
                                        onBackPressed();
                                        getSupportActionBar().setTitle(str_ecg);
                                        childPosition = -1 ;  groupPosition = -1; break;
                                    case 2:
                                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                                new ENBCG()).commit();
                                        onBackPressed();
                                        getSupportActionBar().setTitle(str_enbcg);
                                        childPosition = -1 ;  groupPosition = -1; break;
                                    case 3:
                                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                                new COBISS()).commit();
                                        onBackPressed();
                                        getSupportActionBar().setTitle(str_cobis);
                                        childPosition = -1 ;  groupPosition = -1; break;
                                    case 4:
                                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                                new CgBibliografija()).commit();
                                        onBackPressed();
                                        getSupportActionBar().setTitle(str_cgbibl);
                                        childPosition = -1 ;  groupPosition = -1; break;
                                    case 5:
                                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                                new TekucaBibliografija()).commit();
                                        onBackPressed();
                                        getSupportActionBar().setTitle(str_tekbibl);
                                        childPosition = -1 ;  groupPosition = -1; break;

                                }
                                //endregion  Katalozi
                            //region Usluge
                            case 3:
                                switch (childPosition){
                                    case 0:
                                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                                new ZaKorisnike()).commit();
                                        onBackPressed();
                                        getSupportActionBar().setTitle(str_korisnici);
                                        childPosition = -1 ;  groupPosition = -1; break;
                                    case 1:
                                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                                new ZaIzdavace()).commit();
                                        onBackPressed();
                                        getSupportActionBar().setTitle(str_izdavaci);
                                        childPosition = -1 ;  groupPosition = -1; break;
                                    case 2:
                                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                                new ZaBibliotekare()).commit();
                                        onBackPressed();
                                        getSupportActionBar().setTitle(str_bibliotekari);
                                        childPosition = -1 ;  groupPosition = -1; break;

                                }
                                //endregion Usluge
                            //region Kolekcije
                            case 4:
                                switch (childPosition){
                                    case 0:
                                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                                new OsnovniFond()).commit();
                                        onBackPressed();
                                        getSupportActionBar().setTitle(str_osnovni);
                                        childPosition = -1 ;  groupPosition = -1; break;
                                    case 1:
                                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                                new Montenegrina()).commit();
                                        onBackPressed();
                                        getSupportActionBar().setTitle(str_montenegrina);
                                        childPosition = -1 ;  groupPosition = -1; break;
                                    case 2:
                                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                                new StareRijetkeKnjige()).commit();
                                        onBackPressed();
                                        getSupportActionBar().setTitle(str_star_retk);
                                        childPosition = -1 ;  groupPosition = -1; break;
                                    case 3:
                                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                                new RukopisiArhivalije()).commit();
                                        onBackPressed();
                                        getSupportActionBar().setTitle(str_rukopisi);
                                        childPosition = -1 ;  groupPosition = -1; break;
                                    case 4:
                                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                                new KartografskaZbirka()).commit();
                                        onBackPressed();
                                        getSupportActionBar().setTitle(str_kartog);
                                        childPosition = -1 ;  groupPosition = -1; break;
                                    case 5:
                                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                                new MuzikalijeAudiovizuelna()).commit();
                                        onBackPressed();
                                        getSupportActionBar().setTitle(str_muzikalije);
                                        childPosition = -1 ;  groupPosition = -1; break;
                                    case 6:
                                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                                new LikovnoGraficka()).commit();
                                        onBackPressed();
                                        getSupportActionBar().setTitle(str_lik_graf);
                                        childPosition = -1 ;  groupPosition = -1; break;
                                    case 7:
                                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                                new SerijskePublikacije()).commit();
                                        onBackPressed();
                                        getSupportActionBar().setTitle(str_ser_publ);
                                        childPosition = -1 ;  groupPosition = -1; break;
                                    case 8:
                                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                                new Legati()).commit();
                                        onBackPressed();
                                        getSupportActionBar().setTitle(str_legati);
                                        childPosition = -1 ;  groupPosition = -1; break;
                                    case 9:
                                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                                new DigitalnaBiblioteka()).commit();
                                        onBackPressed();
                                        getSupportActionBar().setTitle(str_dig_bibl);
                                        childPosition = -1 ;  groupPosition = -1; break;
                                }
                                //endregion Kolekcije
                            //region Nasa izdanja
                            case 6:
                                switch (childPosition){
                                    case 0:
                                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                                new IzdavackaDjelatnost()).commit();
                                        onBackPressed();
                                        getSupportActionBar().setTitle(str_izd_del);
                                        childPosition = -1 ;  groupPosition = -1; break;
                                    case 1:
                                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                                new KatalogIzdanja()).commit();
                                        onBackPressed();
                                        getSupportActionBar().setTitle(str_katalog);
                                        childPosition = -1 ;  groupPosition = -1; break;
                                    case 2:
                                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                                new E_Publikacije()).commit();
                                        onBackPressed();
                                        getSupportActionBar().setTitle(str_epub);
                                        childPosition = -1 ;  groupPosition = -1; break;
                                }
                                //endregion Nasa izdanja
                        }
                    }
//                }

                return false;
            }
        });

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousItem = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if(groupPosition != previousItem )
                    expandableListView.collapseGroup(previousItem );
                previousItem = groupPosition;
            }


        });

    }
}
