package com.androidapp.nbcg.fragments.Kolekcije;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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


public class LikovnoGraficka extends Fragment {


    private View mView;
    private Helpers helper = new Helpers();
    private int language = MainActivity.lang;

    ViewFlipper v_flipper;

    int[] images = {
            R.drawable.likovnograficka1,
            R.drawable.likovnograficka2,
            R.drawable.likovnograficka3,
            R.drawable.likovnograficka4,
            R.drawable.likovnograficka5,
            R.drawable.likovnograficka6
    };

    private OnFragmentInteractionListener mListener;

    public LikovnoGraficka() {
    }

    public static LikovnoGraficka newInstance(String param1, String param2) {
        LikovnoGraficka fragment = new LikovnoGraficka();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_likovno_graficka, null);

        v_flipper = (ViewFlipper)mView.findViewById(R.id.v_flipper_istorija);
        for(int i=0; i<images.length; i++){
            flip_image(images[i]);
        }
        textPopulate();

        if(language == 1){
            imageButtonHandlerWeb(ApiUrls.LIKOVNO_GRAFICKA_FB_ENG, R.id.vb_btn_fb);
            imageButtonHandlerWeb(ApiUrls.LIKOVNO_GRAFICKA_TW_ENG, R.id.vb_btn_tw);
            imageButtonHandlerWeb(ApiUrls.LIKOVNO_GRAFICKA_LN_ENG, R.id.vb_btn_ln);
        }

        if(language == 0) {
            imageButtonHandlerWeb(ApiUrls.LIKOVNO_GRAFICKA_FB_MNE, R.id.vb_btn_fb);
            imageButtonHandlerWeb(ApiUrls.LIKOVNO_GRAFICKA_TW_MNE, R.id.vb_btn_tw);
            imageButtonHandlerWeb(ApiUrls.LIKOVNO_GRAFICKA_LN_MNE, R.id.vb_btn_ln);
        }


        return mView;
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
            case 0: titleStr = helper.mne(getResources().getString(R.string.str_likovno_graficka_zbirka_title)); break;
            case 1: titleStr = helper.eng(getResources().getString(R.string.str_likovno_graficka_zbirka_title)); break;
        }
        title.setText(titleStr);


        TextView body = (TextView)mView.findViewById(R.id.vb_body);
        String bodyStr= "";
        switch (language){
            case 0: bodyStr = helper.mne(getResources().getString(R.string.str_likovno_graficka_zbirka_body)); break;
            case 1: bodyStr = helper.eng(getResources().getString(R.string.str_likovno_graficka_zbirka_body)); break;
        }
        body.setText(bodyStr);



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
