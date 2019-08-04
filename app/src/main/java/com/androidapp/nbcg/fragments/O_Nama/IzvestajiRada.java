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
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.androidapp.nbcg.CustomExpandableListAdapter;
import com.androidapp.nbcg.ExpandableListDataPump;
import com.androidapp.nbcg.MainActivity;
import com.androidapp.nbcg.R;
import com.androidapp.nbcg.api_urls.ApiUrls;
import com.androidapp.nbcg.helper.Helpers;
import com.androidapp.nbcg.models.Izvestaji;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IzvestajiRada extends Fragment {

    Helpers helper = new Helpers();
    private int language = MainActivity.lang;

    private View mView;

    String listaPraznaTitle;
    String listaPraznaBody;

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<Izvestaji>> expandableListDetail;

    private OnFragmentInteractionListener mListener;

    public IzvestajiRada() {
    }


    public static IzvestajiRada newInstance() {
        IzvestajiRada fragment = new IzvestajiRada();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_izvestaji_rada, null);

        textPopulate();

        if(language == 1){
            imageButtonHandlerWeb(ApiUrls.IZVESTAJI_FB_ENG, R.id.vb_btn_fb);
            imageButtonHandlerWeb(ApiUrls.IZVESTAJI_TW_ENG, R.id.vb_btn_tw);
            imageButtonHandlerWeb(ApiUrls.IZVESTAJI_LN_ENG, R.id.vb_btn_ln);
        }

        if(language == 0) {
            imageButtonHandlerWeb(ApiUrls.IZVESTAJI_FB_MNE, R.id.vb_btn_fb);
            imageButtonHandlerWeb(ApiUrls.IZVESTAJI_TW_MNE, R.id.vb_btn_tw);
            imageButtonHandlerWeb(ApiUrls.IZVESTAJI_LN_MNE, R.id.vb_btn_ln);
        }

        expandableListView = (ExpandableListView) mView.findViewById(R.id.expandableListView);
        expandableListDetail = ExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(mView.getContext(), expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {

                if(expandableListDetail.get("Izvestaji o radu") != null &&
                        expandableListDetail.get("Putni nalozi") != null &&
                        expandableListDetail.get("Analitičke kartice") != null &&
                        expandableListDetail.get("Programi rada") != null
                ){
                    int izvestaji = expandableListDetail.get("Izvestaji o radu").size();
                    int nalozi = expandableListDetail.get("Putni nalozi").size();
                    int analiticke = expandableListDetail.get("Analitičke kartice").size();
                    int programi = expandableListDetail.get("Programi rada").size();

                    if(izvestaji == 0 && groupPosition == 0){
                        helper.alert(mView.getContext(), listaPraznaTitle, listaPraznaBody );
                    }
                    if(nalozi == 0 && groupPosition == 1){
                        helper.alert(mView.getContext(), listaPraznaTitle, listaPraznaBody );
                    }
                    if(analiticke == 0 && groupPosition == 2){
                        helper.alert(mView.getContext(), listaPraznaTitle, listaPraznaBody );
                    }
                    if(programi == 0 && groupPosition == 3){
                        helper.alert(mView.getContext(), listaPraznaTitle, listaPraznaBody );
                    }
                }

                if(expandableListDetail.get("Reports") != null &&
                        expandableListDetail.get("Travel orders") != null &&
                        expandableListDetail.get("Analytical cards") != null &&
                        expandableListDetail.get("Programs") != null
                ){
                    int izvestaji = expandableListDetail.get("Reports").size();
                    int nalozi = expandableListDetail.get("Travel orders").size();
                    int analiticke = expandableListDetail.get("Analytical cards").size();
                    int programi = expandableListDetail.get("Programs").size();

                    if(programi == 0 && groupPosition == 0){
                        helper.alert(mView.getContext(), listaPraznaTitle, listaPraznaBody );
                    }
                    if(analiticke == 0 && groupPosition == 1){
                        helper.alert(mView.getContext(), listaPraznaTitle, listaPraznaBody );
                    }
                    if(izvestaji == 0 && groupPosition == 2){
                        helper.alert(mView.getContext(), listaPraznaTitle, listaPraznaBody );
                    }
                    if(nalozi == 0 && groupPosition == 3){
                        helper.alert(mView.getContext(), listaPraznaTitle, listaPraznaBody );
                    }
                }
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                String extension = expandableListDetail.get(
                         expandableListTitle.get(groupPosition)).get(
                         childPosition).getFajl();
                String url = ApiUrls.GET_DOCUMENTS+extension;

                helper.goToUrl(url, mView.getContext());

                return false;
            }
        });

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

    private void textPopulate(){
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
                titleStr = helper.mne(getResources().getString(R.string.str_izvestaji_title));
                break;
            case 1:
                titleStr = helper.eng(getResources().getString(R.string.str_izvestaji_title));
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

        switch (language){
            case 0:
                listaPraznaTitle = "Nema podataka!";
                listaPraznaBody = "Nema podataka, molimo Vas proverite interent konekciju.";
                break;
            case 1:
                listaPraznaTitle = "No data!";
                listaPraznaBody = "Data are missing, please check Your internet connection.";
                break;
        }
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
