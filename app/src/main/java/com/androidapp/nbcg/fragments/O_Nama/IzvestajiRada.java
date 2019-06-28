package com.androidapp.nbcg.fragments.O_Nama;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.androidapp.nbcg.CustomExpandableListAdapter;
import com.androidapp.nbcg.ExpandableListDataPump;
import com.androidapp.nbcg.R;
import com.androidapp.nbcg.api_urls.ApiUrls;
import com.androidapp.nbcg.helper.Helpers;
import com.androidapp.nbcg.models.Izvestaji;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IzvestajiRada extends Fragment {

    Helpers helper = new Helpers();

    private View thisFragment;

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
        thisFragment = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_izvestaji_rada, null);

        expandableListView = (ExpandableListView) thisFragment.findViewById(R.id.expandableListView);
        expandableListDetail = ExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(thisFragment.getContext(), expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(thisFragment.getContext(),
//                        expandableListTitle.get(groupPosition) + " List Expanded.",
//                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(thisFragment.getContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                System.out.println("Izlaz: "+expandableListDetail.get(
                        expandableListTitle.get(groupPosition)).get(
                        childPosition).getFajl());
                 String extension = expandableListDetail.get(
                         expandableListTitle.get(groupPosition)).get(
                         childPosition).getFajl();
                String url = ApiUrls.GET_DOCUMENTS+extension;

                helper.goToUrl(url, thisFragment.getContext());

                return false;
            }
        });


        return thisFragment;
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



}
