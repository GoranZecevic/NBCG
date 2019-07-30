package com.androidapp.nbcg.fragments.O_Nama;

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
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.androidapp.nbcg.MainActivity;
import com.androidapp.nbcg.R;
import com.androidapp.nbcg.adapters.VijestiAdapter;
import com.androidapp.nbcg.api_urls.ApiUrls;
import com.androidapp.nbcg.helper.Helpers;
import com.androidapp.nbcg.models.Vijesti;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class JavneNabavke extends Fragment {

    private Helpers helper = new Helpers();
    private int language = MainActivity.lang;

    private View mView;

    private OnFragmentInteractionListener mListener;

    public JavneNabavke() {

    }

    public static JavneNabavke newInstance(String param1, String param2) {
        JavneNabavke fragment = new JavneNabavke();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.fragment_javne_nabavke, container, false);

        textPopulate();

        buttonHandler(new JavneNabavkeLista(), R.id.lista);

        buttonHandlerWeb(ApiUrls.JAVNE_NABAVKE_OBRAZA1, R.id.dokument1);
        buttonHandlerWeb(ApiUrls.JAVNE_NABAVKE_OBRAZA2, R.id.dokument2);
        buttonHandlerWeb(ApiUrls.JAVNE_NABAVKE_OBRAZA3, R.id.dokument3);

        if(language == 1){
            imageButtonHandlerWeb(ApiUrls.JAVNE_NABAVKE_FB_ENG, R.id.vb_btn_fb);
            imageButtonHandlerWeb(ApiUrls.JAVNE_NABAVKE_TW_ENG, R.id.vb_btn_tw);
            imageButtonHandlerWeb(ApiUrls.JAVNE_NABAVKE_LN_ENG, R.id.vb_btn_ln);
        }

        if(language == 0) {
            imageButtonHandlerWeb(ApiUrls.JAVNE_NABAVKE_FB_MNE, R.id.vb_btn_fb);
            imageButtonHandlerWeb(ApiUrls.JAVNE_NABAVKE_TW_MNE, R.id.vb_btn_tw);
            imageButtonHandlerWeb(ApiUrls.JAVNE_NABAVKE_LN_MNE, R.id.vb_btn_ln);
        }

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

        Button header = (Button)mView.findViewById(R.id.o_nama_title);
        String headerStr= "";
        switch (language){
            case 0: headerStr = helper.mne(getResources().getString(R.string.str_onama_title)); break;
            case 1: headerStr = helper.eng(getResources().getString(R.string.str_onama_title)); break;
        }
        header.setText(headerStr);


        TextView title = (TextView)mView.findViewById(R.id.str_za_izdavace_title);
        String titleStr= "";
        switch (language){
            case 0: titleStr = helper.mne(getResources().getString(R.string.str_nabavke_title)); break;
            case 1: titleStr = helper.eng(getResources().getString(R.string.str_nabavke_title)); break;
        }
        title.setText(titleStr);

        TextView javneNabavkeBtn = (TextView)mView.findViewById(R.id.lista);
        String javneNabavkeBtnStr= "";
        switch (language){
            case 0: javneNabavkeBtnStr = helper.mne(getResources().getString(R.string.str_nabavke_title)); break;
            case 1: javneNabavkeBtnStr = helper.eng(getResources().getString(R.string.str_nabavke_title)); break;
        }
        javneNabavkeBtn.setText(javneNabavkeBtnStr);


        Button btn1 = (Button)mView.findViewById(R.id.dokument1);
        String btn1Str= "";
        switch (language){
            case 0: btn1Str = helper.mne(getResources().getString(R.string.str_nabavke_dok1)); break;
            case 1: btn1Str = helper.eng(getResources().getString(R.string.str_nabavke_dok1)); break;
        }
        btn1.setText(btn1Str);

        Button btn2 = (Button)mView.findViewById(R.id.dokument2);
        String btn2Str= "";
        switch (language){
            case 0: btn2Str = helper.mne(getResources().getString(R.string.str_nabavke_dok2)); break;
            case 1: btn2Str = helper.eng(getResources().getString(R.string.str_nabavke_dok2)); break;
        }
        btn2.setText(btn2Str);

        Button btn3 = (Button)mView.findViewById(R.id.dokument3);
        String btn3Str= "";
        switch (language){
            case 0: btn3Str = helper.mne(getResources().getString(R.string.str_nabavke_dok3)); break;
            case 1: btn3Str = helper.eng(getResources().getString(R.string.str_nabavke_dok3)); break;
        }
        btn3.setText(btn3Str);


        TextView podjeli = (TextView)mView.findViewById(R.id.podjeli);
        String podjeliStr= "";
        switch (language){
            case 0: podjeliStr = helper.mne(getResources().getString(R.string.str_podelite)); break;
            case 1: podjeliStr = helper.eng(getResources().getString(R.string.str_podelite)); break;
        }
        podjeli.setText(podjeliStr);


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

    private void buttonHandler(final Fragment fragment, final int buttonId){
        Button button = (Button) mView.findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener()
        {
            Fragment proxyFragment = fragment;
            int id = buttonId;
            @Override
            public void onClick(View v)
            {
                newFragment(proxyFragment);
            }
        });
    }

    private void buttonHandlerWeb(final String url, final int buttonId){
        Button button = (Button) mView.findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener()
        {
            int id = buttonId;
            Helpers help = new Helpers();
            Context ctx = mView.getContext();
            @Override
            public void onClick(View v)
            {
                help.goToUrl(url, ctx);
            }
        });
    }

    private void imageButtonHandlerWeb(final String url, final int buttonId){
        ImageButton button = (ImageButton) mView.findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener()
        {
            int id = buttonId;
            Helpers help = new Helpers();
            Context ctx = mView.getContext();
            @Override
            public void onClick(View v)
            {
                help.goToUrl(url, ctx);
            }
        });
    }
}
