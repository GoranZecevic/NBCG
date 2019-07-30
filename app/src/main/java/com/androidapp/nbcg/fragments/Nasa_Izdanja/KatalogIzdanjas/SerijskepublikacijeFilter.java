package com.androidapp.nbcg.fragments.Nasa_Izdanja.KatalogIzdanjas;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.androidapp.nbcg.MainActivity;
import com.androidapp.nbcg.R;
import com.androidapp.nbcg.adapters.KatalogIzdanjaAdapter;
import com.androidapp.nbcg.api_urls.ApiUrls;
import com.androidapp.nbcg.fragments.Dogadjajis.KoncertFilter;
import com.androidapp.nbcg.fragments.Dogadjajis.PosjetaFilter;
import com.androidapp.nbcg.fragments.Dogadjajis.StruckiskupFilter;
import com.androidapp.nbcg.helper.Helpers;
import com.androidapp.nbcg.models.KatalogIzdanja;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class SerijskepublikacijeFilter extends Fragment {
    private Helpers helper = new Helpers();

    public int language = MainActivity.lang;

    private RecyclerView recycleView;
    private ArrayList<KatalogIzdanja> arrayList;
    private KatalogIzdanjaAdapter adapter;

    private RequestQueue requestQueue;
    private View mView;

    private String katalogIzdanja;

    private String filteri;
    private String fototipskaIzdanja;
    private String posebnaIzdanaj;
    private String bibliografija;
    private String bioBibliografija;
    private String serijskePublikacije;
    private String katalozi;
    private String prirucnici;
    private String ponistiFiltere;

    private String noConnectionTitle;
    private String noConnectionBody;

    private FloatingActionButton btnFilter;

    LottieAnimationView lottieAnimationView;

    private com.androidapp.nbcg.fragments.Nasa_Izdanja.KatalogIzdanja.OnFragmentInteractionListener mListener;

    public SerijskepublikacijeFilter() {
    }

    public static SerijskepublikacijeFilter newInstance(String param1, String param2) {
        SerijskepublikacijeFilter fragment = new SerijskepublikacijeFilter();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_serijskepublikacije_filter, null);
        textPopulate();

        lottieAnimationView = (LottieAnimationView) mView.findViewById(R.id.animation_view);
        lottieAnimationView.setImageAssetsFolder("images/");
        lottieAnimationView.setAnimation("data.json");
        lottieAnimationView.loop(true);
        lottieAnimationView.playAnimation();

        getActionBar().setTitle(katalogIzdanja);

        btnFilter = (FloatingActionButton)mView.findViewById(R.id.katalogFilter);

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Kliknuo");
                showAlertDialogButtonClicked(mView);
            }
        });

        arrayList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this.getContext());

        recycleView = (RecyclerView)mView.findViewById(R.id.recycler_view_katalog_izdanja);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycleView.setLayoutManager(layoutManager);
        PARSEDATA();

        return mView;
    }

    public void PARSEDATA() {
        final String URL = ApiUrls.GET_SERIJSKE_PUBLIKACIJE_FILTER ;


        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("server_response");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);

                                int id =  hit.getInt("ID");

                                String datumod = hit.getString("DATUMOD");
                                datumod = datumod.substring(0, datumod.indexOf(" "));
                                datumod = helper.dateConverter(datumod);

                                String naslov = hit.getString("NASLOV");
                                switch (language){
                                    case 0: naslov = helper.mne(naslov); break;
                                    case 1:
                                        String temp = helper.eng(naslov);
                                        if(!temp.equals("")) naslov = helper.eng(naslov);
                                        else naslov = helper.mne(naslov); break;

                                }

                                String opis = hit.getString("OPIS");
                                switch (language){
                                    case 0: opis =  helper.mne(opis); break;
                                    case 1:
                                        String temp = helper.eng(opis);
                                        if(!temp.equals("")) opis = helper.eng(opis);
                                        else opis = helper.mne(opis); break;
                                }

                                String tekst = hit.getString("TEKST");
                                switch (language){
                                    case 0: tekst = helper.mne(tekst); break;
                                    case 1:
                                        String temp = helper.eng(tekst);
                                        if(!temp.equals("")) tekst = helper.eng(tekst);
                                        else tekst = helper.mne(tekst); break;
                                }

                                String link = hit.getString("LINK");
                                switch (language){
                                    case 0: link = helper.mne(link); break;
                                    case 1:
                                        String temp = helper.eng(link);
                                        if(!temp.equals("")) link = helper.eng(link);
                                        else link = helper.mne(link); break;
                                }

                                double cijena = hit.getDouble("CIJENA");

                                String tipNaslova = hit.getString("TIPOVI_NASLOV");
                                switch (language){
                                    case 0: tipNaslova = helper.mne(tipNaslova); break;
                                    case 1: tipNaslova = helper.eng(tipNaslova); break;
                                }

                                String fajl = hit.getString("FAJL");
                                fajl = fajl;

                                arrayList.add(new com.androidapp.nbcg.models.KatalogIzdanja(id, datumod, naslov, opis, tekst, link, cijena, tipNaslova, fajl));

                            }
                            if(!arrayList.isEmpty()) lottieAnimationView.setVisibility(View.GONE);

                            adapter = new KatalogIzdanjaAdapter(mView , arrayList);

                            recycleView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        lottieAnimationView.setVisibility(View.GONE);
                        helper.alert(mView.getContext(), noConnectionTitle, noConnectionBody );
                    }
                });
        requestQueue.add(request);
    }

    public void showAlertDialogButtonClicked(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(mView.getContext());

        String[] filteri = {fototipskaIzdanja, posebnaIzdanaj, bibliografija, bioBibliografija, serijskePublikacije, katalozi, prirucnici, ponistiFiltere};
        builder.setItems(filteri, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        helper.openFragment(new FototipskaIzdanjaFilter(), getFragmentManager());
                        break;
                    case 1:
                        helper.openFragment(new PosebnaIzdanjaFilter(), getFragmentManager());
                        break;
                    case 2:
                        helper.openFragment(new BibliografijaFilter(), getFragmentManager());
                        break;
                    case 3:
                        helper.openFragment(new BiobibliografijaFilter(), getFragmentManager());
                        break;
                    case 4:
                        helper.openFragment(new SerijskepublikacijeFilter(), getFragmentManager());
                        break;
                    case 5:
                        helper.openFragment(new KataloziFilter(), getFragmentManager());
                        break;
                    case 6:
                        helper.openFragment(new PrirucniciFilter(), getFragmentManager());
                        break;
                    case 7:
                        helper.openFragment(new com.androidapp.nbcg.fragments.Nasa_Izdanja.KatalogIzdanja(), getFragmentManager());
                        break;
                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public ActionBar getActionBar() {
        return ((MainActivity) getActivity()).getSupportActionBar();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void textPopulate(){
        switch (language) {
            case 0:
                noConnectionTitle = "Nema interneta!";
                noConnectionBody = "Za pregled kataloga izdanja potrebna Vam je internet konekcija!";
                filteri = "Filteri";
                katalogIzdanja = "Ktalog Izdanja";
                fototipskaIzdanja = "Fototipska Izdanja";
                posebnaIzdanaj = "Posebna Izdanja";
                bibliografija = "Bibliografija";
                bioBibliografija = "Bio-bibliografija";
                serijskePublikacije = "Serijske publikacije";
                katalozi = "Katalozi";
                prirucnici = "Priručnici";
                ponistiFiltere = "Poništi filtere";
                break;
            case 1:
                noConnectionTitle = "No internet connection!";
                noConnectionBody = "You need internet connection to see our catalogues!";
                filteri = "Filters";
                katalogIzdanja = "Ktalog Izdanja";
                fototipskaIzdanja = "Catalogue of Publications";
                posebnaIzdanaj = "Special Edition";
                bibliografija = "Bibliography";
                bioBibliografija = "Bio - bibliography";
                serijskePublikacije = "Serials";
                katalozi = "Catalogues";
                prirucnici = "Handbooks";
                ponistiFiltere = "Reset filters";
                break;

        }
    }
}
