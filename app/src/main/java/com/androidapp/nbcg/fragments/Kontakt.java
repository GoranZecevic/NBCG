package com.androidapp.nbcg.fragments;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidapp.nbcg.MainActivity;
import com.androidapp.nbcg.R;
import com.androidapp.nbcg.helper.Helpers;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Kontakt extends Fragment implements OnMapReadyCallback {

    private View mView;
    private Helpers helper = new Helpers();
    private int language = MainActivity.lang;


    private OnFragmentInteractionListener mListener;

    GoogleMap mGoogleMap;
    MapView mMapView;

    FloatingActionButton kontakBtn;

    public Kontakt() {
    }

    public static Kontakt newInstance() {
        Kontakt fragment = new Kontakt();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_kontakt, container, false);

        kontakBtn = (FloatingActionButton) mView.findViewById(R.id.emailBtn);

        kontakBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Klik radi! Yup");
                Fragment mail = new KontaktEmail();
                FragmentManager fm = getFragmentManager();
                helper.openFragment(mail, fm);

            }
        });

        textPopulate();

        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMapView = (MapView) mView.findViewById(R.id.map);
        if (mMapView != null) {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }
    }

    public BitmapDescriptor getMarkerIcon(String color) {
        float[] hsv = new float[3];
        Color.colorToHSV(Color.parseColor(color), hsv);
        return BitmapDescriptorFactory.defaultMarker(hsv[0]);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());

        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        googleMap.addMarker(new MarkerOptions().position(new LatLng(42.3949736, 18.91466761)).title("Nacionalna biblioteka").icon(getMarkerIcon("#c8a049")));

        CameraPosition nbcg = CameraPosition.builder().target(new LatLng(42.3949736, 18.91466761)).zoom(16).bearing(0).tilt(45).build();

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(nbcg));
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

        TextView title = (TextView) mView.findViewById(R.id.str_za_izdavace_title);
        String titleStr = "";
        switch (language) {
            case 0:
                titleStr = helper.mne(getResources().getString(R.string.str_kontakt_title));
                break;
            case 1:
                titleStr = helper.eng(getResources().getString(R.string.str_kontakt_title));
                break;
        }
        title.setText(titleStr);


        TextView body = (TextView) mView.findViewById(R.id.vb_body);
        String bodyStr = "";
        switch (language) {
            case 0:
                bodyStr = helper.mne(getResources().getString(R.string.str_kontakt_body));
                break;
            case 1:
                bodyStr = helper.eng(getResources().getString(R.string.str_kontakt_body));
                break;
        }
        body.setText(bodyStr);

    }


}