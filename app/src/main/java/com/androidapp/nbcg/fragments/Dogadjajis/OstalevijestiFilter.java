package com.androidapp.nbcg.fragments.Dogadjajis;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
import com.androidapp.nbcg.fragments.Dogadjaji;
import com.androidapp.nbcg.helper.Helpers;
import com.androidapp.nbcg.models.Vijesti;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OstalevijestiFilter extends Fragment {
    private OstalevijestiFilter.OnFragmentInteractionListener mListener;

    Helpers helper = new Helpers();

    public int language = MainActivity.lang;

    private RecyclerView recycleView;
    private ArrayList<Vijesti> arrayList;
    private VijestiAdapter adapter;

    FloatingActionButton btnFilter;


    private RequestQueue requestQueue;

    private View mView;

    public OstalevijestiFilter() {
    }

    public static OstalevijestiFilter newInstance(String param1, String param2) {
        OstalevijestiFilter fragment = new OstalevijestiFilter();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.fragment_ostalevijesti_filter, container, false);

        btnFilter = (FloatingActionButton)mView.findViewById(R.id.dogadjajiFilter);

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialogButtonClicked(mView);
            }
        });

        arrayList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this.getContext());

        recycleView = (RecyclerView)mView.findViewById(R.id.recycler_view_dogadjaji);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycleView.setLayoutManager(layoutManager);
        PARSEDATA();

        return mView;

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

    public void PARSEDATA() {
        final String URL = ApiUrls.GET_OSTALEVIJESTI_FILTER ;


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

                            adapter = new VijestiAdapter(mView , arrayList);

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

    public void showAlertDialogButtonClicked(View view) {

        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(mView.getContext());
//        builder.setTitle("Filteri");

        // add a list
        String[] filteri = {"Vijest", "Najava", "Izlozba", "Promocija", "Posheta", "Strucni skup", "Koncert", "Virtuelna izlozba", "Ostale vijesti","Ponisti filtere"};
        builder.setItems(filteri, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        helper.openFragment(new VijestiFilter(), getFragmentManager());
                        break;
                    case 1:
                        helper.openFragment(new NajavaFilter(), getFragmentManager());
                        break;
                    case 2:
                        helper.openFragment(new IzlozbaFilter(), getFragmentManager());
                        break;
                    case 3:
                        helper.openFragment(new PromocijaFilter(), getFragmentManager());
                        break;
                    case 4:
                        helper.openFragment(new PosjetaFilter(), getFragmentManager());
                        break;
                    case 5:
                        helper.openFragment(new StruckiskupFilter(), getFragmentManager());
                        break;
                    case 6:
                        helper.openFragment(new KoncertFilter(), getFragmentManager());
                        break;
                    case 7:
                        helper.openFragment(new VirtuelnaizlozbaFilter(), getFragmentManager());
                        break;
                    case 8:
                        helper.openFragment(new OstalevijestiFilter(), getFragmentManager());
                        break;
                    case 9:
                        helper.openFragment(new Dogadjaji(), getFragmentManager());
                        break;
                }
            }
        });

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
