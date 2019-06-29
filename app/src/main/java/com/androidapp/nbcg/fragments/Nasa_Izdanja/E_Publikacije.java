package com.androidapp.nbcg.fragments.Nasa_Izdanja;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.androidapp.nbcg.R;
import com.androidapp.nbcg.adapters.E_PublikacijeAdapter;
import com.androidapp.nbcg.adapters.KatalogIzdanjaAdapter;
import com.androidapp.nbcg.api_urls.ApiUrls;
import com.androidapp.nbcg.models.KatalogIzdanja;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class E_Publikacije extends Fragment {

    private String language;

    private RecyclerView recycleView;
    private ArrayList<KatalogIzdanja> arrayList;
    private E_PublikacijeAdapter adapter;


    private RequestQueue requestQueue;
    private View thisFragment;

    private OnFragmentInteractionListener mListener;

    public E_Publikacije() {
    }


    public static E_Publikacije newInstance(String param1, String param2) {
        E_Publikacije fragment = new E_Publikacije();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        language = "mne";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        thisFragment = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_e__publikacije, null);

        arrayList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this.getContext());

        recycleView = (RecyclerView)thisFragment.findViewById(R.id.recycler_view_epublikacij);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycleView.setLayoutManager(layoutManager);
        PARSEDATA();

        return thisFragment;
    }

    public void PARSEDATA() {
        final String URL = ApiUrls.GET_EPUBLIKACIJE ;


        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("server_response");
//                            System.out.println("Response: "+ response);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);

                                int id =  hit.getInt("ID");
//                                System.out.println("Id: "+ id);

                                String datumod = hit.getString("DATUMOD");
//                                System.out.println("Datum: "+ datumod);

                                String naslov = hit.getString("NASLOV");
                                switch (language){
                                    case "mne": naslov = StringUtils.substringBetween(naslov, "[0]", "[/0]"); break;
                                    case "en": naslov = StringUtils.substringBetween(naslov, "[1]", "[/1]"); break;
                                }
//                                System.out.println("Naslov: "+ naslov);

                                String opis = hit.getString("OPIS");
                                switch (language){
                                    case "mne": opis = StringUtils.substringBetween(opis, "[0]", "[/0]"); break;
                                    case "en": opis = StringUtils.substringBetween(opis, "[1]", "[/1]"); break;
                                }
//                                System.out.println("Opis: "+ opis);


                                String tekst = hit.getString("TEKST");
                                switch (language){
                                    case "mne": tekst = StringUtils.substringBetween(tekst, "[0]", "[/0]"); break;
                                    case "en": tekst = StringUtils.substringBetween(tekst, "[1]", "[/1]"); break;
                                }
//                                System.out.println("Tekst: "+ tekst);

                                String link = hit.getString("LINK");
                                switch (language){
                                    case "mne": link = StringUtils.substringBetween(link, "[0]", "[/0]"); break;
                                    case "en": link = StringUtils.substringBetween(link, "[1]", "[/1]"); break;
                                }
//                                System.out.println("Link: "+ link);


                                double cijena = hit.getDouble("CIJENA");
//                                System.out.println("Cijena: "+ cijena);


                                String tipNaslova = hit.getString("TIPOVI_NASLOV");
                                switch (language){
                                    case "mne": tipNaslova = StringUtils.substringBetween(tipNaslova, "[0]", "[/0]"); break;
                                    case "en": tipNaslova = StringUtils.substringBetween(tipNaslova, "[1]", "[/1]"); break;
                                }
//                                System.out.println("Tip naslova: "+ tipNaslova);

                                String fajl = hit.getString("FAJL");
                                fajl = ApiUrls.GET_PICTURES + fajl;
                                System.out.println("Fajl: "+ fajl);

//                                System.out.println(" ");

                                arrayList.add(new com.androidapp.nbcg.models.KatalogIzdanja(id, datumod, naslov, opis, tekst, link, cijena, tipNaslova, fajl));

                            }

                            adapter = new E_PublikacijeAdapter(thisFragment , arrayList);

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
                    }
                });
        requestQueue.add(request);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
}
