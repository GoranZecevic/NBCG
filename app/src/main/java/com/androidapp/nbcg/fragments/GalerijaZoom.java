package com.androidapp.nbcg.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidapp.nbcg.R;
import com.androidapp.nbcg.api_urls.ApiUrls;
import com.bumptech.glide.Glide;


public class GalerijaZoom extends Fragment {

    private OnFragmentInteractionListener mListener;

    private View mView;
    private String naslov;
    private String fajl;

    public GalerijaZoom() {
    }

    public static GalerijaZoom newInstance(String param1, String param2) {
        GalerijaZoom fragment = new GalerijaZoom();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.fragment_galerija_zoom, container, false);

        Bundle bundle = getArguments();


        if(bundle != null) {
            naslov = bundle.getString("naslov");
            fajl = bundle.getString("fajl");
        }

        ImageView slika = (ImageView)mView.findViewById(R.id.frame);
        TextView naslovPrint = (TextView)mView.findViewById(R.id.frame_text);

        naslovPrint.setText(naslov);

        Glide.with(mView.getContext()).load(ApiUrls.GET_PICTURES_FULL_SIZE + fajl).into(slika);

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
}
