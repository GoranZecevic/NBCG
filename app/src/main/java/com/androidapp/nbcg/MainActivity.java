package com.androidapp.nbcg;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.menu.MenuBuilder;
import android.util.Size;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

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
import com.androidapp.nbcg.helper.Helpers;
import com.androidapp.nbcg.models.MenuModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static int lang = 0;

    private Helpers helper = new Helpers();

    private String izlaz;

    private String yes;
    private String no;

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

    DrawerLayout drawer;

    Toolbar toolbar;

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
        textPopulate();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Pocetna()).commit();
        }

        navigation();

    }

    private void navigation(){
        expandableListView = findViewById(R.id.expandableListView);
        prepareMenuData();
        populateExpandableList();

        drawer = findViewById(R.id.drawer_layout);
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
            int count = getSupportFragmentManager().getBackStackEntryCount();

            if (count == 0) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.app_name);
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setMessage(izlaz)
                        .setCancelable(false)
                        .setPositiveButton(yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        })
                        .setNegativeButton(no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
                Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
                nbutton.setBackgroundColor(Color.parseColor("#c8a049"));
                nbutton.setTextColor(Color.parseColor("#333333"));
                Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
                pbutton.setBackgroundColor(Color.parseColor("#333333"));
                pbutton.setTextColor(Color.parseColor("#c8a049"));
            } else {
                getSupportFragmentManager().popBackStack();
            }
        }


    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        if(menu instanceof MenuBuilder){
            MenuBuilder m = (MenuBuilder) menu;
            m.setOptionalIconsVisible(true);
        }

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.crnogorski) {
            lang = 0;

            getSupportActionBar().setTitle(str_pocetna);
            navigation();

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Pocetna()).commit();
            return true;

        }
        else if(id == R.id.english) {
            lang = 1;

            getSupportActionBar().setTitle(str_pocetna);
            navigation();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Pocetna()).commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        return true;
    }

    private void prepareMenuData() {
        textPopulate();

        if(headerList.size() != 0) headerList.clear();
        if(childList.size() != 0) childList.clear();

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

                if (childList.get(headerList.get(groupPosition)) != null) {


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

    public void textPopulate(){
        switch (lang){
            case 0:
                izlaz = helper.mne(getResources().getString(R.string.str_izlaz));

                yes = helper.mne(getResources().getString(R.string.str_da));
                no = helper.mne(getResources().getString(R.string.str_ne));

                str_pocetna = helper.mne(getResources().getString(R.string.title_pocetna));
                str_o_nama = helper.mne(getResources().getString(R.string.title_o_nama));
                str_katalozi = helper.mne(getResources().getString(R.string.title_katalozi));
                str_usluge = helper.mne(getResources().getString(R.string.title_usluge));
                str_kolekcije = helper.mne(getResources().getString(R.string.title_kolekcije));
                str_dogadjaji = helper.mne(getResources().getString(R.string.title_dogadjaji));
                str_nasa_izdanja = helper.mne(getResources().getString(R.string.title_nasa_izdanja));
                str_kontakt = helper.mne(getResources().getString(R.string.title_kontakt));

                str_uvodna = helper.mne(getResources().getString(R.string.str_uvodna_rijec_title));;
                str_vizmis = helper.mne(getResources().getString(R.string.str_vizija_misija_title));
                str_istorija = helper.mne(getResources().getString(R.string.str_istorija_title));
                str_objekti = helper.mne(getResources().getString(R.string.str_objekti_title));
                str_organizacija = helper.mne(getResources().getString(R.string.str_organ_title));
                str_zv_rad = helper.mne(getResources().getString(R.string.str_izvestaji_title));
                str_saradnja = helper.mne(getResources().getString(R.string.str_saradnja_title));
                str_donatori = helper.mne(getResources().getString(R.string.str_donatori_title));
                str_jav_nab = helper.mne(getResources().getString(R.string.str_nabavke_title));

                str_virt = helper.mne(getResources().getString(R.string.str_vb_title));
                str_ecg = helper.mne(getResources().getString(R.string.str_ecg_title));
                str_enbcg = helper.mne(getResources().getString(R.string.str_enbcg_title));
                str_cobis = helper.mne(getResources().getString(R.string.str_cobiss_title));
                str_cgbibl = helper.mne(getResources().getString(R.string.str_cg_bibl_title));
                str_tekbibl = helper.mne(getResources().getString(R.string.str_tek_bibl_title));

                str_korisnici  =  helper.mne(getResources().getString(R.string.str_korisnici_title));
                str_izdavaci  =  helper.mne(getResources().getString(R.string.str_izdavaci_title));
                str_bibliotekari  =  helper.mne(getResources().getString(R.string.str_bibliotekari_title));

                str_osnovni  = helper.mne(getResources().getString(R.string.str_osnovni_fond_title));
                str_montenegrina = helper.mne(getResources().getString(R.string.str_montenegrina_title));
                str_star_retk = helper.mne(getResources().getString(R.string.str_stare_rijetke_title));
                str_rukopisi = helper.mne(getResources().getString(R.string.str_rukopisi_arhivalije_title));
                str_kartog = helper.mne(getResources().getString(R.string.str_kartografska_zbirka_title));
                str_muzikalije = helper.mne(getResources().getString(R.string.str_muzikalije_title));
                str_lik_graf = helper.mne(getResources().getString(R.string.str_likovno_graficka_zbirka_title));
                str_ser_publ = helper.mne(getResources().getString(R.string.str_serijske_publikacije_title));
                str_legati = helper.mne(getResources().getString(R.string.str_legati_title));
                str_dig_bibl = helper.mne(getResources().getString(R.string.str_dig_bibl_title));

                str_izd_del = helper.mne(getResources().getString(R.string.str_djelatnost_title));
                str_katalog = helper.mne(getResources().getString(R.string.str_kat_izdanja_title));
                str_epub = helper.mne(getResources().getString(R.string.str_e_publikacije_title));
            break;
            case 1:

                izlaz = helper.eng(getResources().getString(R.string.str_izlaz));

                yes = helper.eng(getResources().getString(R.string.str_da));
                no = helper.eng(getResources().getString(R.string.str_ne));

                str_pocetna = helper.eng(getResources().getString(R.string.title_pocetna));
                str_o_nama = helper.eng(getResources().getString(R.string.title_o_nama));
                str_katalozi = helper.eng(getResources().getString(R.string.title_katalozi));
                str_usluge = helper.eng(getResources().getString(R.string.title_usluge));
                str_kolekcije = helper.eng(getResources().getString(R.string.title_kolekcije));
                str_dogadjaji = helper.eng(getResources().getString(R.string.title_dogadjaji));
                str_nasa_izdanja = helper.eng(getResources().getString(R.string.title_nasa_izdanja));
                str_kontakt = helper.eng(getResources().getString(R.string.title_kontakt));

                str_uvodna = helper.eng(getResources().getString(R.string.str_uvodna_rijec_title));;
                str_vizmis = helper.eng(getResources().getString(R.string.str_vizija_misija_title));
                str_istorija = helper.eng(getResources().getString(R.string.str_istorija_title));
                str_objekti = helper.eng(getResources().getString(R.string.str_objekti_title));
                str_organizacija = helper.eng(getResources().getString(R.string.str_organ_title));
                str_zv_rad = helper.eng(getResources().getString(R.string.str_izvestaji_title));
                str_saradnja = helper.eng(getResources().getString(R.string.str_saradnja_title));
                str_donatori = helper.eng(getResources().getString(R.string.str_donatori_title));
                str_jav_nab = helper.eng(getResources().getString(R.string.str_nabavke_title));

                str_virt = helper.eng(getResources().getString(R.string.str_vb_title));
                str_ecg = helper.eng(getResources().getString(R.string.str_ecg_title));
                str_enbcg = helper.eng(getResources().getString(R.string.str_enbcg_title));
                str_cobis = helper.eng(getResources().getString(R.string.str_cobiss_title));
                str_cgbibl = helper.eng(getResources().getString(R.string.str_cg_bibl_title));
                str_tekbibl = helper.eng(getResources().getString(R.string.str_tek_bibl_title));

                str_korisnici  =  helper.eng(getResources().getString(R.string.str_korisnici_title));
                str_izdavaci  =  helper.eng(getResources().getString(R.string.str_izdavaci_title));
                str_bibliotekari  =  helper.eng(getResources().getString(R.string.str_bibliotekari_title));

                str_osnovni  = helper.eng(getResources().getString(R.string.str_osnovni_fond_title));
                str_montenegrina = helper.eng(getResources().getString(R.string.str_montenegrina_title));
                str_star_retk = helper.eng(getResources().getString(R.string.str_stare_rijetke_title));
                str_rukopisi = helper.eng(getResources().getString(R.string.str_rukopisi_arhivalije_title));
                str_kartog = helper.eng(getResources().getString(R.string.str_kartografska_zbirka_title));
                str_muzikalije = helper.eng(getResources().getString(R.string.str_muzikalije_title));
                str_lik_graf = helper.eng(getResources().getString(R.string.str_likovno_graficka_zbirka_title));
                str_ser_publ = helper.eng(getResources().getString(R.string.str_serijske_publikacije_title));
                str_legati = helper.eng(getResources().getString(R.string.str_legati_title));
                str_dig_bibl = helper.eng(getResources().getString(R.string.str_dig_bibl_title));

                str_izd_del = helper.eng(getResources().getString(R.string.str_djelatnost_title));
                str_katalog = helper.eng(getResources().getString(R.string.str_kat_izdanja_title));
                str_epub = helper.eng(getResources().getString(R.string.str_e_publikacije_title));
            break;
        }
    }
}
