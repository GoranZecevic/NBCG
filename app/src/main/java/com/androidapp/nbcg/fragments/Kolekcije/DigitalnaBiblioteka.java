package com.androidapp.nbcg.fragments.Kolekcije;

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

public class DigitalnaBiblioteka extends Fragment {

    private View mView;
    private Helpers helper = new Helpers();
    private int language = MainActivity.lang;


    private OnFragmentInteractionListener mListener;

    public DigitalnaBiblioteka() {
    }

    public static DigitalnaBiblioteka newInstance(String param1, String param2) {
        DigitalnaBiblioteka fragment = new DigitalnaBiblioteka();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_digitalna_biblioteka, container, false);

        textPopulate();

        if(language == 1){
            imageButtonHandlerWeb(ApiUrls.DIG_LIB_FB_ENG, R.id.vb_btn_fb);
            imageButtonHandlerWeb(ApiUrls.DIG_LIB_TW_ENG, R.id.vb_btn_tw);
            imageButtonHandlerWeb(ApiUrls.DIG_LIB_LN_ENG, R.id.vb_btn_ln);
        }

        if(language == 0) {
            imageButtonHandlerWeb(ApiUrls.DIG_LIB_FB_MNE, R.id.vb_btn_fb);
            imageButtonHandlerWeb(ApiUrls.DIG_LIB_TW_MNE, R.id.vb_btn_tw);
            imageButtonHandlerWeb(ApiUrls.DIG_LIB_LN_MNE, R.id.vb_btn_ln);
        }

        buttonHandlerWeb(ApiUrls.DIGLIB_LINK1, R.id.link);
        buttonHandlerWeb(ApiUrls.DIGLIB_LINK2, R.id.link9);
        buttonHandlerWeb(ApiUrls.DIGLIB_LINK3, R.id.link10);

        buttonHandlerWeb(ApiUrls.KNIGE, R.id.link1);
        buttonHandlerWeb(ApiUrls.NOVINE, R.id.link2);
        buttonHandlerWeb(ApiUrls.RUKOPISI, R.id.link3);
        buttonHandlerWeb(ApiUrls.KARTE, R.id.link4);
        buttonHandlerWeb(ApiUrls.PLAKATI, R.id.link5);
        buttonHandlerWeb(ApiUrls.FOTOGRAFIJE, R.id.link6);
        buttonHandlerWeb(ApiUrls.MUZICKA, R.id.link7);
        buttonHandlerWeb(ApiUrls.TEMATSKE, R.id.link8);

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
            case 0: headerStr = helper.mne(getResources().getString(R.string.str_kolekcije_title)); break;
            case 1: headerStr = helper.eng(getResources().getString(R.string.str_kolekcije_title)); break;
        }
        header.setText(headerStr);


        TextView title = (TextView)mView.findViewById(R.id.str_za_izdavace_title);
        String titleStr= "";
        switch (language){
            case 0: titleStr = helper.mne(getResources().getString(R.string.str_dig_bibl_title)); break;
            case 1: titleStr = helper.eng(getResources().getString(R.string.str_dig_bibl_title)); break;
        }
        title.setText(titleStr);


        TextView body1 = (TextView)mView.findViewById(R.id.vb_body1);
        String bodyStr1= "";
        switch (language){
            case 0: bodyStr1 = helper.mne(getResources().getString(R.string.str_dig_bibl_body1)); break;
            case 1: bodyStr1 = helper.eng(getResources().getString(R.string.str_dig_bibl_body1)); break;
        }
        body1.setText(bodyStr1);

        TextView body2 = (TextView)mView.findViewById(R.id.vb_body2);
        String bodyStr2= "";
        switch (language){
            case 0: bodyStr2 = helper.mne(getResources().getString(R.string.str_dig_bibl_body2)); break;
            case 1: bodyStr2 = helper.eng(getResources().getString(R.string.str_dig_bibl_body2)); break;
        }
        body2.setText(bodyStr2);

        TextView body3 = (TextView)mView.findViewById(R.id.vb_body3);
        String bodyStr3= "";
        switch (language){
            case 0: bodyStr3 = helper.mne(getResources().getString(R.string.str_dig_bibl_body3)); break;
            case 1: bodyStr3 = helper.eng(getResources().getString(R.string.str_dig_bibl_body3)); break;
        }
        body3.setText(bodyStr3);

        TextView body4 = (TextView)mView.findViewById(R.id.vb_body4);
        String bodyStr4= "";
        switch (language){
            case 0: bodyStr4 = helper.mne(getResources().getString(R.string.str_dig_bibl_body4)); break;
            case 1: bodyStr4 = helper.eng(getResources().getString(R.string.str_dig_bibl_body4)); break;
        }
        body4.setText(bodyStr4);

        Button link1 = (Button)mView.findViewById(R.id.link1);
        String str1= "";
        switch (language){
            case 0: str1 = helper.mne(getResources().getString(R.string.str_knjige)); break;
            case 1: str1 = helper.eng(getResources().getString(R.string.str_knjige)); break;
        }
        link1.setText(str1);

        Button link2 = (Button)mView.findViewById(R.id.link2);
        String str2= "";
        switch (language){
            case 0: str2 = helper.mne(getResources().getString(R.string.str_novine)); break;
            case 1: str2 = helper.eng(getResources().getString(R.string.str_novine)); break;
        }
        link2.setText(str2);

        Button link3 = (Button)mView.findViewById(R.id.link3);
        String str3= "";
        switch (language){
            case 0: str3 = helper.mne(getResources().getString(R.string.str_rukopisi)); break;
            case 1: str3 = helper.eng(getResources().getString(R.string.str_rukopisi)); break;
        }
        link3.setText(str3);

        Button link4 = (Button)mView.findViewById(R.id.link4);
        String str4= "";
        switch (language){
            case 0: str4 = helper.mne(getResources().getString(R.string.str_karte)); break;
            case 1: str4 = helper.eng(getResources().getString(R.string.str_karte)); break;
        }
        link4.setText(str4);

        Button link5 = (Button)mView.findViewById(R.id.link5);
        String str5= "";
        switch (language){
            case 0: str5 = helper.mne(getResources().getString(R.string.str_plakati)); break;
            case 1: str5 = helper.eng(getResources().getString(R.string.str_plakati)); break;
        }
        link5.setText(str5);

        Button link6 = (Button)mView.findViewById(R.id.link6);
        String str6= "";
        switch (language){
            case 0: str6 = helper.mne(getResources().getString(R.string.str_fotografije)); break;
            case 1: str6 = helper.eng(getResources().getString(R.string.str_fotografije)); break;
        }
        link6.setText(str6);

        Button link7 = (Button)mView.findViewById(R.id.link7);
        String str7= "";
        switch (language){
            case 0: str7 = helper.mne(getResources().getString(R.string.str_muzicka)); break;
            case 1: str7 = helper.eng(getResources().getString(R.string.str_muzicka)); break;
        }
        link7.setText(str7);

        Button link8 = (Button)mView.findViewById(R.id.link8);
        String str8= "";
        switch (language){
            case 0: str8 = helper.mne(getResources().getString(R.string.str_tematske)); break;
            case 1: str8 = helper.eng(getResources().getString(R.string.str_tematske)); break;
        }
        link8.setText(str8);



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
