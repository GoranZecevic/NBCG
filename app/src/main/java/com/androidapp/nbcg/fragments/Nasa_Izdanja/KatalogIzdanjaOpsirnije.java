package com.androidapp.nbcg.fragments.Nasa_Izdanja;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidapp.nbcg.R;
import com.androidapp.nbcg.api_urls.ApiUrls;
import com.androidapp.nbcg.helper.Helpers;
import com.bumptech.glide.Glide;


public class KatalogIzdanjaOpsirnije extends Fragment {

    Helpers helper = new Helpers();

    private View thisFragment;

    private Button btnPoruci;

    private OnFragmentInteractionListener mListener;

    public KatalogIzdanjaOpsirnije() {
    }

    public static KatalogIzdanjaOpsirnije newInstance(String param1, String param2) {
        KatalogIzdanjaOpsirnije fragment = new KatalogIzdanjaOpsirnije();
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

        thisFragment = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_katalog_izdanja_opsirnije, null);

        if(bundle != null){
            String datumod = bundle.getString("datumod");
            String naslov  = bundle.getString("naslov");
            String opis = bundle.getString("opis");
            String tekst = bundle.getString("tekst");
            String link = bundle.getString("link");
            double cijena = bundle.getDouble("cijena");
            String tip_naslova = bundle.getString("tip_naslova");
            String fajl = bundle.getString("fajl");


            TextView datumOdTxt = (TextView)thisFragment.findViewById(R.id.opsirnije_datumod);
            datumOdTxt.setText(datumod);
            TextView naslovTxt = (TextView)thisFragment.findViewById(R.id.opsirnije_naslov);
            naslovTxt.setText(Html.fromHtml(naslov));
            TextView opisTxt = (TextView)thisFragment.findViewById(R.id.opsirnije_opis);
            opisTxt.setText(Html.fromHtml(opis));
            TextView tekstTxt = (TextView)thisFragment.findViewById(R.id.opsirnije_tekst);
            tekstTxt.setText(Html.fromHtml(tekst));
            TextView linkTxt = (TextView)thisFragment.findViewById(R.id.opsirnije_link);
            linkTxt.setText(Html.fromHtml(link));
            TextView cijenaTxt = (TextView)thisFragment.findViewById(R.id.opsirnije_cijena);
            cijenaTxt.setText(String.valueOf(cijena));
            TextView tipNaslovaTxt = (TextView)thisFragment.findViewById(R.id.opsirnije_tip_nslova);
            tipNaslovaTxt.setText(Html.fromHtml(String.valueOf(tip_naslova)));

            ImageView slika = (ImageView)thisFragment.findViewById(R.id.kat_izd_ops_img);
            Glide.with(thisFragment).load(ApiUrls.GET_PICTURES_FULL_SIZE+fajl).into(slika);
        }

        btnPoruci = (Button)thisFragment.findViewById(R.id.poruci_btn);

        btnPoruci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText imeTxtView = (EditText)thisFragment.findViewById(R.id.ime_input);
                String ime = imeTxtView.getText().toString();

                EditText prezimeTxtView = (EditText)thisFragment.findViewById(R.id.prezime_input);
                String prezime = prezimeTxtView.getText().toString();

                EditText telefonTxtView = (EditText)thisFragment.findViewById(R.id.telefon_input);
                String telefon = telefonTxtView.getText().toString();

                EditText adresaTxtView = (EditText)thisFragment.findViewById(R.id.adresa_input);
                String adresa = adresaTxtView.getText().toString();

                EditText emailTxtView = (EditText)thisFragment.findViewById(R.id.email_input);
                String email = emailTxtView.getText().toString();

                if(ime.equals("") || prezime.equals("") || telefon.equals("") || adresa.equals("") || email.equals("")){
                    helper.alert(thisFragment.getContext(), "Nedostaju podaci","Molimo Vas popunite sva polja. Hvala!" );
                }else if(email.indexOf('@') == -1){
                    helper.alert(thisFragment.getContext(), "Email nije validan","Uneseni email nije validan!" );
                }
                else{
                    closeKeyboard();
                    helper.alert(thisFragment.getContext(), "Uspesna porudzbina","Hvala Vam na porudzbini!" );

                    getFragmentManager().popBackStack();
                }
            }
        });


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

    //Close keyboard
    public void closeKeyboard(){
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}
