package com.androidapp.nbcg.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.androidapp.nbcg.ExpandableListDataPump;
import com.androidapp.nbcg.MainActivity;
import com.androidapp.nbcg.R;
import com.androidapp.nbcg.fragments.Usluge.ZaBibliotekare;
import com.androidapp.nbcg.fragments.Usluge.ZaIzdavace;
import com.androidapp.nbcg.fragments.Usluge.ZaKorisnike;


public class Pocetna extends Fragment {

    public int language = MainActivity.lang;

    private View thisFragment;
    ViewFlipper v_flipper;
    private static  boolean exist;

    int[] images = {
            R.drawable.slide1,
            R.drawable.slide2,
            R.drawable.slide3
    };

    private OnFragmentInteractionListener mListener;

    public Pocetna() {
    }

    public static Pocetna newInstance(String param1, String param2) {
        Pocetna fragment = new Pocetna();
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
        thisFragment = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_pocetna, null);

        System.out.println("Jezik je: "+ language);

        if(exist != true){
            ExpandableListDataPump expandableListDataPump = new ExpandableListDataPump(thisFragment.getContext());
            exist = true;
        }

        v_flipper = (ViewFlipper)thisFragment.findViewById(R.id.v_flipper);
        for(int i=0; i<images.length; i++){
            flip_image(images[i]);
        }

        buttonHandler(new ZaKorisnike(), R.id.za_korisnike_btn);
        buttonHandler(new ZaKorisnike(), R.id.za_korisnike_opsirnije_btn);

        buttonHandler(new ZaIzdavace(), R.id.za_izdavace_btn);
        buttonHandler(new ZaIzdavace(), R.id.za_izdavace_opsirnije_btn);

        buttonHandler(new ZaBibliotekare(), R.id.za_bibiliotekare_btn);
        buttonHandler(new ZaBibliotekare(), R.id.za_bibliotekare_opsirnije_btn);

        return thisFragment;
    }


    // region helpers
    private void newFragment(Fragment fragment){
        Fragment newFragment = fragment;

        FragmentManager manager = getFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, newFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void buttonHandler(final Fragment fragment, int buttonId){
        Button button = (Button) thisFragment.findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener()
        {

            Fragment proxyFragment = fragment;
            @Override
            public void onClick(View v)
            {
                newFragment(proxyFragment);
            }
        });
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
