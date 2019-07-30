package com.androidapp.nbcg.fragments.Usluge;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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


public class ZaBibliotekare extends Fragment {

    private View mView;
    private Helpers helper = new Helpers();
    private int language = MainActivity.lang;

    ViewFlipper v_flipper;

    int[] images = {
            R.drawable.bibliotekari1,
            R.drawable.bibliotekari2,
            R.drawable.bibliotekari3,
            R.drawable.bibliotekari4,
            R.drawable.bibliotekari5,
            R.drawable.bibliotekari6,
            R.drawable.bibliotekari7
    };

    private OnFragmentInteractionListener mListener;

    public ZaBibliotekare() {
    }

    public static ZaBibliotekare newInstance(String param1, String param2) {
        ZaBibliotekare fragment = new ZaBibliotekare();
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
        mView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_za_bibliotekare, null);
        String zaBibliotekareStr= "";
        switch (language){
            case 0: zaBibliotekareStr = helper.mne(getResources().getString(R.string.str_bibliotekari_title)); break;
            case 1: zaBibliotekareStr = helper.eng(getResources().getString(R.string.str_bibliotekari_title)); break;
        }
        getActionBar().setTitle(zaBibliotekareStr);

        textPopulate();

        if(language == 1){
            imageButtonHandlerWeb(ApiUrls.ZA_BIBLIOTEKARE_FB_ENG, R.id.vb_btn_fb);
            imageButtonHandlerWeb(ApiUrls.ZA_BIBLIOTEKARE_TW_ENG, R.id.vb_btn_tw);
            imageButtonHandlerWeb(ApiUrls.ZA_BIBLIOTEKARE_LN_ENG, R.id.vb_btn_ln);
        }

        if(language == 0) {
            imageButtonHandlerWeb(ApiUrls.ZA_BIBLIOTEKARE_FB_MNE, R.id.vb_btn_fb);
            imageButtonHandlerWeb(ApiUrls.ZA_BIBLIOTEKARE_TW_MNE, R.id.vb_btn_tw);
            imageButtonHandlerWeb(ApiUrls.ZA_BIBLIOTEKARE_LN_MNE, R.id.vb_btn_ln);
        }

        buttonHandlerWeb(ApiUrls.ZA_BIBLIOTEKARE_LINK1, R.id.link1 );
        buttonHandlerWeb(ApiUrls.ZA_BIBLIOTEKARE_LINK2, R.id.link2 );
        buttonHandlerWeb(ApiUrls.ZA_BIBLIOTEKARE_LINK3, R.id.link3 );
        buttonHandlerWeb(ApiUrls.ZA_BIBLIOTEKARE_LINK4, R.id.link4 );
        buttonHandlerWeb(ApiUrls.ZA_BIBLIOTEKARE_LINK5, R.id.link5 );
        buttonHandlerWeb(ApiUrls.ZA_BIBLIOTEKARE_LINK6, R.id.link6 );
        buttonHandlerWeb(ApiUrls.ZA_BIBLIOTEKARE_LINK7, R.id.link7 );
        buttonHandlerWeb(ApiUrls.ZA_BIBLIOTEKARE_LINK8, R.id.link8 );
        buttonHandlerWeb(ApiUrls.ZA_BIBLIOTEKARE_LINK9, R.id.link9 );
        buttonHandlerWeb(ApiUrls.ZA_BIBLIOTEKARE_LINK10, R.id.link10 );
        buttonHandlerWeb(ApiUrls.ZA_BIBLIOTEKARE_LINK11, R.id.link11 );

        v_flipper = (ViewFlipper)mView.findViewById(R.id.v_flipper_za_bibliotekare);
        for(int i=0; i<images.length; i++){
            flip_image(images[i]);
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

    public ActionBar getActionBar() {
        return ((MainActivity) getActivity()).getSupportActionBar();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void textPopulate(){

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
            case 0: titleStr = helper.mne(getResources().getString(R.string.str_bibliotekari_title)); break;
            case 1: titleStr = helper.eng(getResources().getString(R.string.str_bibliotekari_title)); break;
        }
        title.setText(titleStr);


        TextView body = (TextView)mView.findViewById(R.id.vb_body);
        String bodyStr= "";
        switch (language){
            case 0: bodyStr = helper.mne(getResources().getString(R.string.str_bibliotekari_body)); break;
            case 1: bodyStr = helper.eng(getResources().getString(R.string.str_bibliotekari_body)); break;
        }
        body.setText(bodyStr);


        Button btn1 = (Button)mView.findViewById(R.id.link1);
        String btn1Str= "";
        switch (language){
            case 0: btn1Str = helper.mne(getResources().getString(R.string.str_bibliotekari_link1)); break;
            case 1: btn1Str = helper.eng(getResources().getString(R.string.str_bibliotekari_link1)); break;
        }
        btn1.setText(btn1Str);

        Button btn2 = (Button)mView.findViewById(R.id.link2);
        String btn2Str= "";
        switch (language){
            case 0: btn2Str = helper.mne(getResources().getString(R.string.str_bibliotekari_link2)); break;
            case 1: btn2Str = helper.eng(getResources().getString(R.string.str_bibliotekari_link2)); break;
        }
        btn2.setText(btn2Str);

        Button btn3 = (Button)mView.findViewById(R.id.link3);
        String btn3Str= "";
        switch (language){
            case 0: btn3Str = helper.mne(getResources().getString(R.string.str_bibliotekari_link3)); break;
            case 1: btn3Str = helper.eng(getResources().getString(R.string.str_bibliotekari_link3)); break;
        }
        btn3.setText(btn3Str);

        Button btn4 = (Button)mView.findViewById(R.id.link4);
        String btn4Str= "";
        switch (language){
            case 0: btn4Str = helper.mne(getResources().getString(R.string.str_bibliotekari_link4)); break;
            case 1: btn4Str = helper.eng(getResources().getString(R.string.str_bibliotekari_link4)); break;
        }
        btn4.setText(btn4Str);

        Button btn5 = (Button)mView.findViewById(R.id.link5);
        String btn5Str= "";
        switch (language){
            case 0: btn5Str = helper.mne(getResources().getString(R.string.str_bibliotekari_link5)); break;
            case 1: btn5Str = helper.eng(getResources().getString(R.string.str_bibliotekari_link5)); break;
        }
        btn5.setText(btn5Str);

        Button btn6 = (Button)mView.findViewById(R.id.link6);
        String btn6Str= "";
        switch (language){
            case 0: btn6Str = helper.mne(getResources().getString(R.string.str_bibliotekari_link6)); break;
            case 1: btn6Str = helper.eng(getResources().getString(R.string.str_bibliotekari_link6)); break;
        }
        btn6.setText(btn6Str);

        Button btn7 = (Button)mView.findViewById(R.id.link7);
        String btn7Str= "";
        switch (language){
            case 0: btn7Str = helper.mne(getResources().getString(R.string.str_bibliotekari_link7)); break;
            case 1: btn7Str = helper.eng(getResources().getString(R.string.str_bibliotekari_link7)); break;
        }
        btn7.setText(btn7Str);


        Button btn8 = (Button)mView.findViewById(R.id.link8);
        String btn8Str= "";
        switch (language){
            case 0: btn8Str = helper.mne(getResources().getString(R.string.str_bibliotekari_link8)); break;
            case 1: btn8Str = helper.eng(getResources().getString(R.string.str_bibliotekari_link8)); break;
        }
        btn8.setText(btn8Str);

        Button btn9 = (Button)mView.findViewById(R.id.link9);
        String btn9Str= "";
        switch (language){
            case 0: btn9Str = helper.mne(getResources().getString(R.string.str_bibliotekari_link9)); break;
            case 1: btn9Str = helper.eng(getResources().getString(R.string.str_bibliotekari_link9)); break;
        }
        btn9.setText(btn9Str);

        Button btn10 = (Button)mView.findViewById(R.id.link10);
        String btn10Str= "";
        switch (language){
            case 0: btn10Str = helper.mne(getResources().getString(R.string.str_bibliotekari_link10)); break;
            case 1: btn10Str = helper.eng(getResources().getString(R.string.str_bibliotekari_link10)); break;
        }
        btn10.setText(btn10Str);

        Button btn11 = (Button)mView.findViewById(R.id.link11);
        String btn11Str= "";
        switch (language){
            case 0: btn11Str = helper.mne(getResources().getString(R.string.str_bibliotekari_link11)); break;
            case 1: btn11Str = helper.eng(getResources().getString(R.string.str_bibliotekari_link11)); break;
        }
        btn11.setText(btn11Str);

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
