package com.androidapp.nbcg.fragments;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.androidapp.nbcg.R;
import com.androidapp.nbcg.api_urls.ApiUrls;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;


public class GalerijaZoom extends Fragment {

    private OnFragmentInteractionListener mListener;

    private View mView;
    private String naslov;
    private String fajl;

    LottieAnimationView lottieAnimationView;

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

        lottieAnimationView = (LottieAnimationView) mView.findViewById(R.id.animation_view);
        lottieAnimationView.setImageAssetsFolder("images/");
        lottieAnimationView.setAnimation("data.json");
        lottieAnimationView.loop(true);
        lottieAnimationView.playAnimation();

        Bundle bundle = getArguments();


        if(bundle != null) {
            naslov = bundle.getString("naslov");
            fajl = bundle.getString("fajl");
        }

        ImageView slika = (ImageView)mView.findViewById(R.id.frame);
        TextView naslovPrint = (TextView)mView.findViewById(R.id.frame_text);

        naslovPrint.setText(naslov);

        Glide.with(mView.getContext()).load(ApiUrls.GET_PICTURES_FULL_SIZE + fajl)
                .listener(new RequestListener<Drawable>() {
                              @Override
                              public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {

                                  lottieAnimationView.setVisibility(View.GONE);
                                  return false;
                              }

                              @Override
                              public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                                  lottieAnimationView.setVisibility(View.GONE);
                                  return false;
                              }
                          }
                ).into(slika);

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
