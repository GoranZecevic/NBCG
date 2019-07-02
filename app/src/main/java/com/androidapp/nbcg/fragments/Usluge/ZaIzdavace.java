package com.androidapp.nbcg.fragments.Usluge;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.androidapp.nbcg.MainActivity;
import com.androidapp.nbcg.R;
import com.androidapp.nbcg.helper.Helpers;


public class ZaIzdavace extends Fragment {

    Helpers helper = new Helpers();

    private View thisFragment;
    ViewFlipper v_flipper;

    int[] images = {
            R.drawable.slide1,
            R.drawable.slide2,
            R.drawable.slide3
    };

    private OnFragmentInteractionListener mListener;

    public ZaIzdavace() {
    }

    public static ZaIzdavace newInstance(String param1, String param2) {
        ZaIzdavace fragment = new ZaIzdavace();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void flip_image(int i){
        ImageView view  = new ImageView((Context) getHost());
        view.setBackgroundResource(i);
        v_flipper.addView(view);
        v_flipper.setFlipInterval(4000);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation((Context) getHost(), android.R.anim.slide_in_left);
        v_flipper.setOutAnimation((Context) getHost(), android.R.anim.slide_out_right);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        thisFragment = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_za_izdavace, null);
        getActionBar().setTitle("Za izdavace");

        return thisFragment;
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

    public ActionBar getActionBar() {
            return ((MainActivity) getActivity()).getSupportActionBar();
    }
}
