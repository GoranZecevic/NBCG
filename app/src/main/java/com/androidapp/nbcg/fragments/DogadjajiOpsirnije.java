package com.androidapp.nbcg.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidapp.nbcg.MainActivity;
import com.androidapp.nbcg.R;
import com.androidapp.nbcg.api_urls.ApiUrls;
import com.androidapp.nbcg.helper.Helpers;
import com.bumptech.glide.Glide;

public class DogadjajiOpsirnije extends Fragment {

    private View thisFragment;

    int language = MainActivity.lang;

    private Helpers helper = new Helpers();

    private OnFragmentInteractionListener mListener;

    public DogadjajiOpsirnije() {
    }

    public static DogadjajiOpsirnije newInstance(String param1, String param2) {
        DogadjajiOpsirnije fragment = new DogadjajiOpsirnije();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();

        thisFragment = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_dogadjaji_opsirnije, null);

        if(bundle != null){
            final int id  = bundle.getInt("id");
            String naslov  = bundle.getString("naslov");
            String datumod = bundle.getString("datumod");
            String tip_novpsti = bundle.getString("tip_novpsti");
            String opis = bundle.getString("opis");
            String podnaslov = bundle.getString("description");
            String fajl = bundle.getString("fajl");
            final String link = bundle.getString("link");


            TextView naslovTxt = (TextView)thisFragment.findViewById(R.id.opsirnije_naslov);
            naslovTxt.setText(Html.fromHtml(naslov));
            TextView datumOdTxt = (TextView)thisFragment.findViewById(R.id.opsirnije_datumod);
            datumOdTxt.setText(Html.fromHtml(datumod));
            TextView tipNovostiTxt = (TextView)thisFragment.findViewById(R.id.opsirnije_tip_novpsti);
            tipNovostiTxt.setText(Html.fromHtml(tip_novpsti));
            ImageView image = (ImageView)thisFragment.findViewById(R.id.opsirnije_dogadjaji_img);
            Glide.with(thisFragment).load(ApiUrls.GET_PICTURES_FULL_SIZE + fajl).into(image);
            TextView podnaslovTxt = (TextView)thisFragment.findViewById(R.id.opsirnije_podnaslov);
            podnaslovTxt.setText(Html.fromHtml(podnaslov));

            TextView opisTxt = (TextView)thisFragment.findViewById(R.id.opsirnije_opis);
            TextView podjeliTxt = (TextView)thisFragment.findViewById(R.id.podjeli);
            switch (language) {
                case 0:
                    podjeliTxt.setText("PODJELI");
                    break;
                case 1:
                    podjeliTxt.setText("SHARE");
                    break;
            }


            String opisChanged = opis.toString().replace("\\r\\n", " ");
            opisTxt.setText(Html.fromHtml(opisChanged));

            ImageButton fb = (ImageButton)thisFragment.findViewById(R.id.vb_btn_fb);
            fb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(language == 0) helper.goToUrl(ApiUrls.SHARE_FB_MNE+link, thisFragment.getContext());
                    else if(language == 1) helper.goToUrl(ApiUrls.SHARE_FB_ENG+link, thisFragment.getContext());;
                }
            });

            ImageButton tw = (ImageButton)thisFragment.findViewById(R.id.vb_btn_tw);
            tw.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(language == 0) helper.goToUrl(ApiUrls.SHARE_TW_MNE+link, thisFragment.getContext());
                    else if(language == 1) helper.goToUrl(ApiUrls.SHARE_TW_ENG+link, thisFragment.getContext());;
                }
            });

            ImageButton ln = (ImageButton)thisFragment.findViewById(R.id.vb_btn_ln);
            ln.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(language == 0) helper.goToUrl(ApiUrls.SHARE_LN_MNE+link, thisFragment.getContext());
                    else if(language == 1) helper.goToUrl(ApiUrls.SHARE_LN_ENG+link, thisFragment.getContext());;
                }
            });

            Button galerijaBtn = (Button)thisFragment.findViewById(R.id.btnGalerija);
            switch (language) {
                case 0:
                    galerijaBtn.setText("GALERIJA");
                    break;
                case 1:
                    galerijaBtn.setText("GALLERY");
                    break;
            }
            galerijaBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fm = getFragmentManager();
                    Galerija galerija = new Galerija();

                    Bundle bundel = new Bundle();
                    bundel.putInt("id", id);
                    galerija.setArguments(bundel);

                    helper.openFragment(galerija, fm);
                }
            });
        }

        return thisFragment;
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
}
