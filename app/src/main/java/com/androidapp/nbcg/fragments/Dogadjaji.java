package com.androidapp.nbcg.fragments;

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
import com.androidapp.nbcg.adapters.VijestiAdapter;
import com.androidapp.nbcg.api_urls.ApiUrls;
import com.androidapp.nbcg.models.Vijesti;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Dogadjaji extends Fragment {


    private String language;

    private RecyclerView recycleView;
    private ArrayList<Vijesti> arrayList;
    private VijestiAdapter adapter;


    private RequestQueue requestQueue;
    private View thisFragment;



    private OnFragmentInteractionListener mListener;

    public Dogadjaji() {
    }


    public static Dogadjaji newInstance(String param1, String param2) {
        Dogadjaji fragment = new Dogadjaji();

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
        thisFragment = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_dogadjaji, null);

        arrayList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this.getContext());

        recycleView = (RecyclerView)thisFragment.findViewById(R.id.recycler_view_dogadjaji);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycleView.setLayoutManager(layoutManager);
        PARSEDATA();

        return thisFragment;
    }

    public void PARSEDATA() {
        final String URL = ApiUrls.GET_VIJESTI ;


        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("server_response");
                            System.out.println("Response: "+ response);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);

                                int id =  hit.getInt("ID");
//                                System.out.println("Id: "+ id);

                                String datumod = hit.getString("DATUMOD");
                                datumod = datumod.substring(0, datumod.indexOf(" "));
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


                                String description = hit.getString("DESCRIPTION");
                                switch (language){
                                    case "mne": description = StringUtils.substringBetween(description, "[0]", "[/0]"); break;
                                    case "en": description = StringUtils.substringBetween(description, "[1]", "[/1]"); break;
                                }
//                                System.out.println("Description: "+ description);

                                String tip_novosti = hit.getString("TIP_NOVOSTI");
                                switch (language){
                                    case "mne": tip_novosti = StringUtils.substringBetween(tip_novosti, "[0]", "[/0]"); break;
                                    case "en": tip_novosti = StringUtils.substringBetween(tip_novosti, "[1]", "[/1]"); break;
                                }
//                                System.out.println("Tip_novosti: "+ tip_novosti);


//                                System.out.println(" ");

                                arrayList.add(new Vijesti(id, datumod, naslov, opis, description, tip_novosti));

                            }

                            adapter = new VijestiAdapter(thisFragment , arrayList);

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
        void onFragmentInteraction(Uri uri);
    }
}
