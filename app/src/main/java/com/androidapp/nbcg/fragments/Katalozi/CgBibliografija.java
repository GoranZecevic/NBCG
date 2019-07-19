package com.androidapp.nbcg.fragments.Katalozi;

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

public class CgBibliografija extends Fragment {

    private View mView;
    private Helpers helper = new Helpers();
    private int language = MainActivity.lang;

    private OnFragmentInteractionListener mListener;

    public CgBibliografija() {
    }

    public static CgBibliografija newInstance(String param1, String param2) {
        CgBibliografija fragment = new CgBibliografija();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_cg_bibliografija, container, false);

        textPopulate();

        if(language == 1){
            imageButtonHandlerWeb(ApiUrls.CG_BIBL_FB_ENG, R.id.vb_btn_fb);
            imageButtonHandlerWeb(ApiUrls.CG_BIBL_TW_ENG, R.id.vb_btn_tw);
            imageButtonHandlerWeb(ApiUrls.CG_BIBL_LN_ENG, R.id.vb_btn_ln);
        }

        if(language == 0) {
            imageButtonHandlerWeb(ApiUrls.CG_BIBL_FB_MNE, R.id.vb_btn_fb);
            imageButtonHandlerWeb(ApiUrls.CG_BIBL_TW_MNE, R.id.vb_btn_tw);
            imageButtonHandlerWeb(ApiUrls.CG_BIBL_LN_MNE, R.id.vb_btn_ln);
        }

        buttonHandlerWeb(ApiUrls.VBCG_CG_BIBLIOGRAFIJA, R.id.link);

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

    public void textPopulate(){

        Button header = (Button)mView.findViewById(R.id.o_nama_title);
        String headerStr= "";
        switch (language){
            case 0: headerStr = helper.mne(getResources().getString(R.string.str_katalozi_title)); break;
            case 1: headerStr = helper.eng(getResources().getString(R.string.str_katalozi_title)); break;
        }
        header.setText(headerStr);


        TextView title = (TextView)mView.findViewById(R.id.str_za_izdavace_title);
        String titleStr= "";
        switch (language){
            case 0: titleStr = helper.mne(getResources().getString(R.string.str_cg_bibl_title)); break;
            case 1: titleStr = helper.eng(getResources().getString(R.string.str_cg_bibl_title)); break;
        }
        title.setText(titleStr);


        TextView body1 = (TextView)mView.findViewById(R.id.vb_body1);
        String bodyStr1= "";
        switch (language){
            case 0: bodyStr1 = helper.mne(getResources().getString(R.string.str_cg_bibl_body1)); break;
            case 1: bodyStr1 = helper.eng(getResources().getString(R.string.str_cg_bibl_body1)); break;
        }
        body1.setText(bodyStr1);

        TextView body2 = (TextView)mView.findViewById(R.id.vb_body2);
        String bodyStr2= "";
        switch (language){
            case 0: bodyStr2 = helper.mne(getResources().getString(R.string.str_cg_bibl_body2)); break;
            case 1: bodyStr2 = helper.eng(getResources().getString(R.string.str_cg_bibl_body2)); break;
        }
        body2.setText(bodyStr2);



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
