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
import com.androidapp.nbcg.MainActivity;
import com.androidapp.nbcg.R;
import com.androidapp.nbcg.adapters.VijestiAdapter;
import com.androidapp.nbcg.api_urls.ApiUrls;
import com.androidapp.nbcg.helper.Helpers;
import com.androidapp.nbcg.models.Vijesti;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Dogadjaji extends Fragment {

    private OnFragmentInteractionListener mListener;

    Helpers helper = new Helpers();

    public int language = MainActivity.lang;

    private RecyclerView recycleView;
    private ArrayList<Vijesti> arrayList;
    private VijestiAdapter adapter;


    private RequestQueue requestQueue;
    private View thisFragment;




    public Dogadjaji() {
    }


    public static Dogadjaji newInstance(String param1, String param2) {
        Dogadjaji fragment = new Dogadjaji();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
                                    case 0: opis = helper.mne(opis); break;
                                    case 1:
                                        String temp = helper.eng(opis);
                                        if(!temp.equals("")) opis = helper.eng(opis);
                                        else opis = helper.mne(opis); break;
                                }
//                                System.out.println("Opis: "+ opis);


                                String description = hit.getString("DESCRIPTION");
                                switch (language){
                                    case 0: description = helper.mne(description); break;
                                    case 1:
                                        String temp = helper.eng(description);
                                        if(!temp.equals("")) description = helper.eng(description);
                                        else description = helper.mne(description); break;
                                }
//                                System.out.println("Description: "+ description);

                                String tip_novosti = hit.getString("TIP_NOVOSTI");
                                switch (language){
                                    case 0: tip_novosti = helper.mne(tip_novosti); break;
                                    case 1: tip_novosti = helper.eng(tip_novosti); break;
                                }

                                String link = hit.getString("LINK");
                                switch (language){
                                    case 0: link = helper.mne(link); break;
                                    case 1: link = helper.eng(link); break;
                                }
//                                System.out.println("LInk: "+ link);

                                String fajl = hit.getString("FAJL");
//                                System.out.println("Fajl: "+ fajl);

//                                System.out.println(" ");

                                arrayList.add(new Vijesti(id, datumod, naslov, opis, description, tip_novosti, fajl, link));

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
