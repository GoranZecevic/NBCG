package com.androidapp.nbcg.fragments.O_Nama;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.androidapp.nbcg.MainActivity;
import com.androidapp.nbcg.R;
import com.androidapp.nbcg.helper.Helpers;


public class UvodnaRijec extends Fragment {

    public int language = MainActivity.lang;
    Helpers helper = new Helpers();

    private View mView;

    private OnFragmentInteractionListener mListener;

    public UvodnaRijec() {
    }


    public static UvodnaRijec newInstance(String param1, String param2) {
        UvodnaRijec fragment = new UvodnaRijec();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_uvodna_rijec, null);
        textPopulate();

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

    public void textPopulate(){

        TextView title = (TextView)mView.findViewById(R.id.str_za_izdavace_title);
        String titleStr= "";
        switch (language){
            case 0: titleStr = helper.mne(getResources().getString(R.string.str_uvodna_rijec_title)); break;
            case 1: titleStr = helper.eng(getResources().getString(R.string.str_uvodna_rijec_title)); break;
        }
        title.setText(titleStr);

        TextView body = (TextView)mView.findViewById(R.id.vb_body);
        String bodyStr= "";
        switch (language){
            case 0: bodyStr = helper.mne(getResources().getString(R.string.str_uvodna_rijec_body)); break;
            case 1: bodyStr = helper.eng(getResources().getString(R.string.str_uvodna_rijec_body)); break;
        }
        body.setText(bodyStr);

        Button header = (Button)mView.findViewById(R.id.o_nama_title);
        String headerStr= "";
        switch (language){
            case 0: headerStr = helper.mne(getResources().getString(R.string.str_onama_title)); break;
            case 1: headerStr = helper.eng(getResources().getString(R.string.str_onama_title)); break;
        }
        header.setText(headerStr);
    }
}
