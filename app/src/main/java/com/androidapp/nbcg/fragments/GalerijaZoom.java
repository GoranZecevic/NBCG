package com.androidapp.nbcg.fragments;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidapp.nbcg.MainActivity;
import com.androidapp.nbcg.R;
import com.androidapp.nbcg.api_urls.ApiUrls;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.Timer;


public class GalerijaZoom extends Fragment {

    private OnFragmentInteractionListener mListener;

    private View mView;
    private String naslov;
    private String fajl;
    ImageView slika;

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

        slika = (ImageView)mView.findViewById(R.id.frame);
        TextView naslovPrint = (TextView)mView.findViewById(R.id.frame_text);



        naslovPrint.setText(naslov);

        Glide.with(mView.getContext()).load(ApiUrls.GET_PICTURES_FULL_SIZE + fajl)
                .listener(new RequestListener<Drawable>() {
                              @Override
                              public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                  return false;
                              }

                              @Override
                              public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                  handleAnimation();
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
        handleFade();
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    long animatorDuration = 200;
    public void handleAnimation(){
        ObjectAnimator animY = ObjectAnimator.ofFloat(slika, "x", -400f, 0f);
        animY.setDuration(animatorDuration);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(slika, View.ALPHA, 0.0f, 1.0f);
        alpha.setDuration(800);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animY, alpha);
        animatorSet.start();
    }

    public void handleFade(){
        ObjectAnimator animY = ObjectAnimator.ofFloat(slika, "x", 0f, 1200f);
        animY.setDuration(animatorDuration);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(slika, View.ALPHA, 1.0f, 0.0f);
        alpha.setDuration(800);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animY, alpha);
        animatorSet.start();
    }


}
