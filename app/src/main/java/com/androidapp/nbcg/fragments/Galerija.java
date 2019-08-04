package com.androidapp.nbcg.fragments;

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
import com.androidapp.nbcg.adapters.GalerijaAdapter;
import com.androidapp.nbcg.api_urls.ApiUrls;
import com.androidapp.nbcg.helper.Helpers;
import com.androidapp.nbcg.models.Slike;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Galerija extends Fragment {

    private OnFragmentInteractionListener mListener;

    private int language;

    Helpers helper = new Helpers();

    private RecyclerView recycleView;
    private ArrayList<Slike> arrayList;
    private GalerijaAdapter galerijaAdapter;

    private int id;
    public View mView;
    private RequestQueue requestQueue;


    public Galerija() {
    }

    public static Galerija newInstance(String param1, String param2) {
        Galerija fragment = new Galerija();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        language = MainActivity.lang;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_galerija, null);
        arrayList = new ArrayList<>();


        requestQueue = Volley.newRequestQueue(this.getContext());
        Bundle bundle = getArguments();

        if(bundle != null) {
              id = bundle.getInt("id");
        }

        recycleView = (RecyclerView)mView.findViewById(R.id.recycler_view_galerija);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycleView.setLayoutManager(layoutManager);

        PARSEDATA();

        return  mView;
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
        final String URL = ApiUrls.GET_SLIKE;

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL+"?id="+id, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("server_response");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);

                                String naslov = hit.getString("NASLOV");
                                naslov = helper.mne(naslov);

                                String fajl = hit.getString("FAJL");

                                arrayList.add(new Slike(naslov, fajl));
                            }
                            galerijaAdapter = new GalerijaAdapter(mView , arrayList);

                            recycleView.setAdapter(galerijaAdapter);

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
}

