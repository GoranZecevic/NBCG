package com.androidapp.nbcg.fragments.Dogadjajis;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class StruckiskupFilter extends Fragment {
    private StruckiskupFilter.OnFragmentInteractionListener mListener;

    Helpers helper = new Helpers();

    private String dogadjaji;

    private String filteri;

    private String vijest;
    private String najava;
    private String izlozba;
    private String promocija;
    private String posjeta;
    private String strucniSkup;
    private String koncert;
    private String virtuelnaIzlozba;
    private String ostaleVijesti;
    private String ponistiFiltere;

    private String noConnectionTitle;
    private String noConnectionBody;

    public int language = MainActivity.lang;

    private RecyclerView recycleView;
    private ArrayList<Vijesti> arrayList;
    private VijestiAdapter adapter;

    FloatingActionButton btnFilter;


    private RequestQueue requestQueue;

    private View mView;

    public StruckiskupFilter() {
    }

    public static StruckiskupFilter newInstance(String param1, String param2) {
        StruckiskupFilter fragment = new StruckiskupFilter();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.fragment_struckiskup_filter, container, false);
        textPopulate();

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
        final String URL = ApiUrls.GET_STRUCNISKUP_FILTER ;


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
                                    case 0: opis = helper.mne(opis); break;
                                    case 1:
                                        String temp = helper.eng(opis);
                                        if(!temp.equals("")) opis = helper.eng(opis);
                                        else opis = helper.mne(opis); break;
                                }

                                String description = hit.getString("DESCRIPTION");
                                switch (language){
                                    case 0: description = helper.mne(description); break;
                                    case 1:
                                        String temp = helper.eng(description);
                                        if(!temp.equals("")) description = helper.eng(description);
                                        else description = helper.mne(description); break;
                                }

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

                                String fajl = hit.getString("FAJL");

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
                        helper.alert(mView.getContext(), noConnectionTitle, noConnectionBody );
                    }
                });
        requestQueue.add(request);
    }

    public void showAlertDialogButtonClicked(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mView.getContext());
        builder.setTitle(filteri);

        String[] filteri = {vijest, najava, izlozba, promocija, posjeta, strucniSkup, koncert, virtuelnaIzlozba, ostaleVijesti, ponistiFiltere};
        builder.setItems(filteri, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        helper.openFragment(new VijestiFilter(), getActivity().getSupportFragmentManager());
                        break;
                    case 1:
                        helper.openFragment(new NajavaFilter(), getActivity().getSupportFragmentManager());
                        break;
                    case 2:
                        helper.openFragment(new IzlozbaFilter(), getActivity().getSupportFragmentManager());
                        break;
                    case 3:
                        helper.openFragment(new PromocijaFilter(), getActivity().getSupportFragmentManager());
                        break;
                    case 4:
                        helper.openFragment(new PosjetaFilter(), getActivity().getSupportFragmentManager());
                        break;
                    case 5:
                        helper.openFragment(new StruckiskupFilter(), getActivity().getSupportFragmentManager());
                        break;
                    case 6:
                        helper.openFragment(new KoncertFilter(), getActivity().getSupportFragmentManager());
                        break;
                    case 7:
                        helper.openFragment(new VirtuelnaizlozbaFilter(), getActivity().getSupportFragmentManager());
                        break;
                    case 8:
                        helper.openFragment(new OstalevijestiFilter(), getActivity().getSupportFragmentManager());
                        break;
                    case 9:
                        helper.openFragment(new Dogadjaji(), getActivity().getSupportFragmentManager());
                        break;
                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void textPopulate(){
        switch (language) {
            case 0:
                noConnectionTitle = "Nema interneta!";
                noConnectionBody = "Za pregled događaja potrebna Vam je internet konekcija!";
                dogadjaji = "Događaji";
                filteri = "Filteri";
                vijest = "Vijest";
                najava = "Najava";
                izlozba = "Izložba";
                promocija = "Promocija";
                posjeta = "Posjeta";
                strucniSkup = "Stručni skup";
                koncert = "Koncert";
                virtuelnaIzlozba = "Virtuelna izložba";
                ostaleVijesti = "Ostale vijesti";
                ponistiFiltere = "Poništi filtere";
                break;
            case 1:
                noConnectionTitle = "No internet connection!";
                noConnectionBody = "You need internet connection to see events!";
                dogadjaji = "Events";
                filteri = "Filters";
                vijest = "News";
                najava = "Announcement";
                izlozba = "Exhibition";
                promocija = "Promotion";
                posjeta = "Visit";
                strucniSkup = "Expert meeting";
                koncert = "Concert";
                virtuelnaIzlozba = "Virtual exhibition";
                ostaleVijesti = "Other news";
                ponistiFiltere = "Reset filters";
                break;
        }
    }
}
