package com.androidapp.nbcg.fragments.Nasa_Izdanja;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.androidapp.nbcg.adapters.E_PublikacijeAdapter;
import com.androidapp.nbcg.api_urls.ApiUrls;
import com.androidapp.nbcg.helper.Helpers;
import com.androidapp.nbcg.models.KatalogIzdanja;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class E_Publikacije extends Fragment {

    public int language = MainActivity.lang;

    Helpers helper = new Helpers();

    private RecyclerView recycleView;
    private ArrayList<KatalogIzdanja> arrayList;
    private E_PublikacijeAdapter adapter;

    private String noConnectionTitle;
    private String noConnectionBody;

    private RequestQueue requestQueue;
    private View mView;


    LottieAnimationView lottieAnimationView;

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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_e__publikacije, null);
        handleAnimation();

        lottieAnimationView = (LottieAnimationView) mView.findViewById(R.id.animation_view);
        lottieAnimationView.setImageAssetsFolder("images/");
        lottieAnimationView.setAnimation("data.json");
        lottieAnimationView.loop(true);
        lottieAnimationView.playAnimation();

        arrayList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this.getContext());

        recycleView = (RecyclerView)mView.findViewById(R.id.recycler_view_epublikacij);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycleView.setLayoutManager(layoutManager);
        PARSEDATA();

        return mView;
    }

    public void PARSEDATA() {
        final String URL = ApiUrls.GET_EPUBLIKACIJE ;


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
                                datumod = helper.dateConverter(datumod);

                                String naslov = hit.getString("NASLOV");
                                switch (language){
                                    case 0: naslov = StringUtils.substringBetween(naslov, "[0]", "[/0]"); break;
                                    case 1: naslov = StringUtils.substringBetween(naslov, "[1]", "[/1]"); break;
                                }

                                String opis = hit.getString("OPIS");
                                switch (language){
                                    case 0: opis = StringUtils.substringBetween(opis, "[0]", "[/0]"); break;
                                    case 1: opis = StringUtils.substringBetween(opis, "[1]", "[/1]"); break;
                                }

                                String tekst = hit.getString("TEKST");
                                switch (language){
                                    case 0: tekst = StringUtils.substringBetween(tekst, "[0]", "[/0]"); break;
                                    case 1: tekst = StringUtils.substringBetween(tekst, "[1]", "[/1]"); break;
                                }

                                String link = hit.getString("LINK");
                                switch (language){
                                    case 0: link = StringUtils.substringBetween(link, "[0]", "[/0]"); break;
                                    case 1: link = StringUtils.substringBetween(link, "[1]", "[/1]"); break;
                                }


                                double cijena = hit.getDouble("CIJENA");

                                String tipNaslova = hit.getString("TIPOVI_NASLOV");
                                switch (language){
                                    case 0: tipNaslova = StringUtils.substringBetween(tipNaslova, "[0]", "[/0]"); break;
                                    case 1: tipNaslova = StringUtils.substringBetween(tipNaslova, "[1]", "[/1]"); break;
                                }

                                String fajl = hit.getString("FAJL");
                                fajl = ApiUrls.GET_PICTURES + fajl;

                                arrayList.add(new com.androidapp.nbcg.models.KatalogIzdanja(id, datumod, naslov, opis, tekst, link, cijena, tipNaslova, fajl));

                            }
                            if(!arrayList.isEmpty()) lottieAnimationView.setVisibility(View.GONE);

                            adapter = new E_PublikacijeAdapter(mView , arrayList);

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
                        switch (language) {
                            case 0:
                                noConnectionTitle = "Nema interneta!";
                                noConnectionBody = "Kako bi preuzeli E-Publikacije potrebna Vam je internet konekcija!";
                                break;
                            case 1:
                                noConnectionTitle = "No internet connection!";
                                noConnectionBody = "You need internet connection to download Electronic Publications!";
                                break;

                        }
                        helper.alert(mView.getContext(), noConnectionTitle, noConnectionBody );
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

    public void handleAnimation(){
        LottieAnimationView title = (LottieAnimationView) mView.findViewById(R.id.animation_view);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(title, View.ALPHA, 0.0f, 1.0f);
        alpha.setDuration(400);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(alpha);
        animatorSet.start();
    }
}
