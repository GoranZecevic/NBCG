package com.androidapp.nbcg.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.androidapp.nbcg.R;
import com.androidapp.nbcg.helper.Helpers;


public class KontaktEmail extends Fragment {

    private OnFragmentInteractionListener mListener;

    Helpers helper = new Helpers();

    View mView;
    Button btnPosalji;

    public KontaktEmail() {
    }

    public static KontaktEmail newInstance(String param1, String param2) {
        KontaktEmail fragment = new KontaktEmail();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_kontakt_email, container, false);

        btnPosalji = (Button)mView.findViewById(R.id.pitanje_btn);

        btnPosalji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText imeTxtView = (EditText)mView.findViewById(R.id.kontakt_ime_input);
                String ime = imeTxtView.getText().toString();

                EditText prezimeTxtView = (EditText)mView.findViewById(R.id.kontakt_prezime_input);
                String prezime = prezimeTxtView.getText().toString();

                EditText emailTxtView = (EditText)mView.findViewById(R.id.kontakt_email_input);
                String email = emailTxtView.getText().toString();

                EditText telefonTxtView = (EditText)mView.findViewById(R.id.kontakt_telefon_input);
                String telefon = telefonTxtView.getText().toString();

                EditText pitanjeTxtView = (EditText)mView.findViewById(R.id.pitanje);
                String pitanje = pitanjeTxtView.getText().toString();

                if(ime.equals("") || prezime.equals("") || email.equals("") || telefon.equals("") || pitanje.equals("")) {
                    helper.alert(mView.getContext(), "Nedostaju podaci","Molimo Vas popunite sva polja. Hvala!" );
                }else if(email.indexOf('@') == -1){
                    helper.alert(mView.getContext(), "Email nije validan","Uneseni email nije validan!" );
                }
                else{
                    closeKeyboard();
                    helper.alert(mView.getContext(), "Pitanje prosledjeno","Hvala Vam na interesovanju!" );

                    //TO DO: proslediti podatke na neki call

                    getFragmentManager().popBackStack();
                }
            }
        });


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

    //Close keyboard
    public void closeKeyboard(){
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
