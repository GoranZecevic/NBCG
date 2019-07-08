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

import com.androidapp.nbcg.MainActivity;
import com.androidapp.nbcg.R;
import com.androidapp.nbcg.api_urls.ApiUrls;
import com.androidapp.nbcg.helper.Helpers;


public class Organizacija extends Fragment {

    private View mView;
    private Helpers helper = new Helpers();
    private int language = MainActivity.lang;

    private OnFragmentInteractionListener mListener;

    public Organizacija() {
    }

    public static Organizacija newInstance(String param1, String param2) {
        Organizacija fragment = new Organizacija();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_organizacija, container, false);

        textPopulate();

        buttonHandlerWeb(ApiUrls.DOKUMENT_1, R.id.button1);
        buttonHandlerWeb(ApiUrls.DOKUMENT_2, R.id.button2);
        buttonHandlerWeb(ApiUrls.DOKUMENT_3, R.id.button3);
        buttonHandlerWeb(ApiUrls.DOKUMENT_4, R.id.button4);
        buttonHandlerWeb(ApiUrls.DOKUMENT_5, R.id.button5);
        buttonHandlerWeb(ApiUrls.DOKUMENT_6, R.id.button6);
        buttonHandlerWeb(ApiUrls.DOKUMENT_7, R.id.button7);
        buttonHandlerWeb(ApiUrls.DOKUMENT_8, R.id.button8);
        buttonHandlerWeb(ApiUrls.DOKUMENT_9, R.id.button9);
        buttonHandlerWeb(ApiUrls.DOKUMENT_10, R.id.button10);
        buttonHandlerWeb(ApiUrls.DOKUMENT_11, R.id.button11);

        if(language == 1){
            imageButtonHandlerWeb(ApiUrls.ORGANIZACIJA_FB_ENG, R.id.vb_btn_fb);
            imageButtonHandlerWeb(ApiUrls.ORGANIZACIJA_TW_ENG, R.id.vb_btn_tw);
            imageButtonHandlerWeb(ApiUrls.ORGANIZACIJA_LN_ENG, R.id.vb_btn_ln);
        }

        if(language == 0) {
            imageButtonHandlerWeb(ApiUrls.OBJEKTI_FB_MNE, R.id.vb_btn_fb);
            imageButtonHandlerWeb(ApiUrls.OBJEKTI_TW_MNE, R.id.vb_btn_tw);
            imageButtonHandlerWeb(ApiUrls.OBJEKTI_LN_MNE, R.id.vb_btn_ln);
        }


        return mView;
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

    public void textPopulate() {

        Button header = (Button) mView.findViewById(R.id.o_nama_title);
        String headerStr = "";
        switch (language) {
            case 0:
                headerStr = helper.mne(getResources().getString(R.string.str_onama_title));
                break;
            case 1:
                headerStr = helper.eng(getResources().getString(R.string.str_onama_title));
                break;
        }
        header.setText(headerStr);


        TextView title = (TextView) mView.findViewById(R.id.str_za_izdavace_title);
        String titleStr = "";
        switch (language) {
            case 0:
                titleStr = helper.mne(getResources().getString(R.string.str_organ_title));
                break;
            case 1:
                titleStr = helper.eng(getResources().getString(R.string.str_organ_title));
                break;
        }
        title.setText(titleStr);


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
