package com.androidapp.nbcg.fragments.Usluge;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.androidapp.nbcg.MainActivity;
import com.androidapp.nbcg.R;
import com.androidapp.nbcg.api_urls.ApiUrls;
import com.androidapp.nbcg.helper.Helpers;


public class ZaIzdavace extends Fragment {

    private View mView;
    private Helpers helper = new Helpers();
    private int language = MainActivity.lang;

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
        mView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_za_izdavace, null);
        String zaIzdavaceStr= "";
        switch (language){
            case 0: zaIzdavaceStr = helper.mne(getResources().getString(R.string.str_izdavaci_title)); break;
            case 1: zaIzdavaceStr = helper.eng(getResources().getString(R.string.str_izdavaci_title)); break;
        }
        getActionBar().setTitle(zaIzdavaceStr);

        textPopulate();

        if(language == 1){
            imageButtonHandlerWeb(ApiUrls.ZA_IZDAVACE_FB_ENG, R.id.vb_btn_fb);
            imageButtonHandlerWeb(ApiUrls.ZA_IZDAVACE_TW_ENG, R.id.vb_btn_tw);
            imageButtonHandlerWeb(ApiUrls.ZA_IZDAVACE_LN_ENG, R.id.vb_btn_ln);
        }

        if(language == 0) {
            imageButtonHandlerWeb(ApiUrls.ZA_IZDAVACE_FB_MNE, R.id.vb_btn_fb);
            imageButtonHandlerWeb(ApiUrls.ZA_IZDAVACE_TW_MNE, R.id.vb_btn_tw);
            imageButtonHandlerWeb(ApiUrls.ZA_IZDAVACE_LN_MNE, R.id.vb_btn_ln);
        }

        buttonHandlerWeb(ApiUrls.ZA_IZADVACE_LINK1,R.id.link1);
        buttonHandlerWeb(ApiUrls.ZA_IZADVACE_LINK2,R.id.link2);
        buttonHandlerWeb(ApiUrls.ZA_IZADVACE_LINK3,R.id.link3);
        buttonHandlerWeb(ApiUrls.ZA_IZADVACE_LINK4,R.id.link4);
        buttonHandlerWeb(ApiUrls.ZA_IZADVACE_LINK5,R.id.link5);

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

    public ActionBar getActionBar() {
            return ((MainActivity) getActivity()).getSupportActionBar();
    }

    public void textPopulate(){
        Button link1 = (Button)mView.findViewById(R.id.link1);
        Button link2 = (Button)mView.findViewById(R.id.link2);

        if(language == 1) {
            link1.setVisibility(View.GONE);
            link2.setVisibility(View.GONE);
        }


        Button header = (Button)mView.findViewById(R.id.o_nama_title);
        String headerStr= "";
        switch (language){
            case 0: headerStr = helper.mne(getResources().getString(R.string.str_usluge_title)); break;
            case 1: headerStr = helper.eng(getResources().getString(R.string.str_usluge_title)); break;
        }
        header.setText(headerStr);


        TextView title = (TextView)mView.findViewById(R.id.str_za_izdavace_title);
        String titleStr= "";
        switch (language){
            case 0: titleStr = helper.mne(getResources().getString(R.string.str_izdavaci_title)); break;
            case 1: titleStr = helper.eng(getResources().getString(R.string.str_izdavaci_title)); break;
        }
        title.setText(titleStr);


        TextView body1 = (TextView)mView.findViewById(R.id.vb_body1);
        String bodyStr1= "";
        switch (language){
            case 0: bodyStr1 = helper.mne(getResources().getString(R.string.str_izdavaci_body1)); break;
            case 1: bodyStr1 = helper.eng(getResources().getString(R.string.str_izdavaci_body1)); break;
        }
        body1.setText(bodyStr1);

        TextView body2 = (TextView)mView.findViewById(R.id.vb_body2);
        String bodyStr2= "";
        switch (language){
            case 0: bodyStr2 = helper.mne(getResources().getString(R.string.str_izdavaci_body2)); break;
            case 1: bodyStr2 = helper.eng(getResources().getString(R.string.str_izdavaci_body2)); break;
        }
        body2.setText(bodyStr2);

        Button btn1 = (Button)mView.findViewById(R.id.link3);
        String btn1Str= "";
        switch (language){
            case 0: btn1Str = helper.mne(getResources().getString(R.string.str_izdavaci_btn1)); break;
            case 1: btn1Str = helper.eng(getResources().getString(R.string.str_izdavaci_btn1)); break;
        }
        btn1.setText(btn1Str);

        Button btn2 = (Button)mView.findViewById(R.id.link4);
        String btn2Str= "";
        switch (language){
            case 0: btn2Str = helper.mne(getResources().getString(R.string.str_izdavaci_btn2)); break;
            case 1: btn2Str = helper.eng(getResources().getString(R.string.str_izdavaci_btn2)); break;
        }
        btn2.setText(btn2Str);

        Button btn3 = (Button)mView.findViewById(R.id.link5);
        String btn3Str= "";
        switch (language){
            case 0: btn3Str = helper.mne(getResources().getString(R.string.str_izdavaci_btn3)); break;
            case 1: btn3Str = helper.eng(getResources().getString(R.string.str_izdavaci_btn3)); break;
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
