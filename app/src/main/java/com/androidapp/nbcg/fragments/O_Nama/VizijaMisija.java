package com.androidapp.nbcg.fragments.O_Nama;

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
import android.widget.TextView;

import com.androidapp.nbcg.MainActivity;
import com.androidapp.nbcg.R;
import com.androidapp.nbcg.api_urls.ApiUrls;
import com.androidapp.nbcg.helper.Helpers;

public class VizijaMisija extends Fragment {

    public int language = MainActivity.lang;
    Helpers helper = new Helpers();

    private View mView;

    private OnFragmentInteractionListener mListener;

    public VizijaMisija() {
    }

    public static VizijaMisija newInstance(String param1, String param2) {
        VizijaMisija fragment = new VizijaMisija();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_vizija_misija, null);
        textPopulate();

        if(language == 1){
            imageButtonHandlerWeb(ApiUrls.VIZIJA_MISIJA_FB_ENG, R.id.vb_btn_fb);
            imageButtonHandlerWeb(ApiUrls.VIZIJA_MISIJA_TW_ENG, R.id.vb_btn_tw);
            imageButtonHandlerWeb(ApiUrls.VIZIJA_MISIJA_LN_ENG, R.id.vb_btn_ln);
        }

        if(language == 0) {
            imageButtonHandlerWeb(ApiUrls.VIZIJA_MISIJA_FB_MNE, R.id.vb_btn_fb);
            imageButtonHandlerWeb(ApiUrls.VIZIJA_MISIJA_TW_MNE, R.id.vb_btn_tw);
            imageButtonHandlerWeb(ApiUrls.VIZIJA_MISIJA_LN_MNE, R.id.vb_btn_ln);
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
            case 0: titleStr = helper.mne(getResources().getString(R.string.str_vizija_misija_title)); break;
            case 1: titleStr = helper.eng(getResources().getString(R.string.str_vizija_misija_title)); break;
        }
        title.setText(titleStr);

        TextView bold = (TextView)mView.findViewById(R.id.vb_bold);
        String boldStr= "";
        switch (language){
            case 0: boldStr = helper.mne(getResources().getString(R.string.str_vizija_misija_bold)); break;
            case 1: boldStr = helper.eng(getResources().getString(R.string.str_vizija_misija_bold)); break;
        }
        bold.setText(boldStr);

        TextView body = (TextView)mView.findViewById(R.id.vb_body);
        String bodyStr= "";
        switch (language){
            case 0: bodyStr = helper.mne(getResources().getString(R.string.str_vizija_misija_body)); break;
            case 1: bodyStr = helper.eng(getResources().getString(R.string.str_vizija_misija_body)); break;
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
