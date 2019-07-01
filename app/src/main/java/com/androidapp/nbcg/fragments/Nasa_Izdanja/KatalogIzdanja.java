package com.androidapp.nbcg.fragments.Nasa_Izdanja;

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
import com.androidapp.nbcg.MainActivity;
import com.androidapp.nbcg.R;
import com.androidapp.nbcg.adapters.KatalogIzdanjaAdapter;
import com.androidapp.nbcg.api_urls.ApiUrls;
import com.androidapp.nbcg.helper.Helpers;


import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class KatalogIzdanja extends Fragment {

    private Helpers helper = new Helpers();

    public int language = MainActivity.lang;

    private RecyclerView recycleView;
    private ArrayList<com.androidapp.nbcg.models.KatalogIzdanja> arrayList;
    private KatalogIzdanjaAdapter adapter;


    private RequestQueue requestQueue;
    private View thisFragment;


    private OnFragmentInteractionListener mListener;

    public KatalogIzdanja() {
    }

    public static KatalogIzdanja newInstance(String param1, String param2) {
        KatalogIzdanja fragment = new KatalogIzdanja();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        thisFragment = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_katalog_izdanja, null);

        arrayList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this.getContext());

        recycleView = (RecyclerView)thisFragment.findViewById(R.id.recycler_view_katalog_izdanja);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycleView.setLayoutManager(layoutManager);
        PARSEDATA();

        return thisFragment;
    }

    public void PARSEDATA() {
        final String URL = ApiUrls.GET_KATALOG_IZDANJA ;


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
                                datumod = datumod.substring(0, datumod.indexOf(" "));
                                datumod = helper.dateConverter(datumod);
//                                System.out.println("Datum: "+ datumod);

                                String naslov = hit.getString("NASLOV");
                                switch (language){
                                    case 0: naslov = helper.mne(naslov); break;
                                    case 1:
                                        String temp = helper.eng(naslov);
                                        if(!temp.equals("")) naslov = helper.eng(naslov);
                                        else naslov = helper.mne(naslov); break;

                                }
//                                System.out.println("Naslov: "+ naslov);

                                String opis = hit.getString("OPIS");
                                switch (language){
                                    case 0: opis =  helper.mne(opis); break;
                                    case 1:
                                        String temp = helper.eng(opis);
                                        if(!temp.equals("")) opis = helper.eng(opis);
                                        else opis = helper.mne(opis); break;
                                }
//                                System.out.println("Opis: "+ opis);


                                String tekst = hit.getString("TEKST");
                                switch (language){
                                    case 0: tekst = helper.mne(tekst); break;
                                    case 1:
                                        String temp = helper.eng(tekst);
                                        if(!temp.equals("")) tekst = helper.eng(tekst);
                                        else tekst = helper.mne(tekst); break;
                                }
//                                System.out.println("Tekst: "+ tekst);

                                String link = hit.getString("LINK");
                                switch (language){
                                    case 0: link = helper.mne(link); break;
                                    case 1:
                                        String temp = helper.eng(link);
                                        if(!temp.equals("")) link = helper.eng(link);
                                        else link = helper.mne(link); break;
                                }
//                                System.out.println("Link: "+ link);


                                double cijena = hit.getDouble("CIJENA");
//                                System.out.println("Cijena: "+ cijena);


                                String tipNaslova = hit.getString("TIPOVI_NASLOV");
                                switch (language){
                                    case 0: tipNaslova = helper.mne(tipNaslova); break;
                                    case 1: tipNaslova = helper.eng(tipNaslova); break;
                                }
//                                System.out.println("Tip naslova: "+ tipNaslova);

                                String fajl = hit.getString("FAJL");
                                fajl = fajl;
//                                System.out.println("Fajl: "+ fajl);

//                                System.out.println(" ");

                                arrayList.add(new com.androidapp.nbcg.models.KatalogIzdanja(id, datumod, naslov, opis, tekst, link, cijena, tipNaslova, fajl));

                            }

                            adapter = new KatalogIzdanjaAdapter(thisFragment , arrayList);

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
