package com.androidapp.nbcg.fragments.Nasa_Izdanja;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidapp.nbcg.MainActivity;
import com.androidapp.nbcg.R;
import com.androidapp.nbcg.api_urls.ApiUrls;
import com.androidapp.nbcg.helper.GMailSender;
import com.androidapp.nbcg.helper.Helpers;
import com.bumptech.glide.Glide;


public class KatalogIzdanjaOpsirnije extends Fragment {

    Helpers helper = new Helpers();

    Button btnPosalji;

    String dataMissingTitle;
    String dataMissingBody;
    String emailNotValidTitle;
    String emailNotValidBody;
    String questionSendTitle;
    String questionSendBody;

    private View mView;

    private Button btnPoruci;

    String link;

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

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        mView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_katalog_izdanja_opsirnije, null);
        textPopulate();

        if(bundle != null){
            String datumod = bundle.getString("datumod");
            String naslov  = bundle.getString("naslov");
            String opis = bundle.getString("opis");
            String tekst = bundle.getString("tekst");
            link = bundle.getString("link");
            double cijena = bundle.getDouble("cijena");
            String tip_naslova = bundle.getString("tip_naslova");
            String fajl = bundle.getString("fajl");


            TextView datumOdTxt = (TextView)mView.findViewById(R.id.opsirnije_datumod);
            datumOdTxt.setText(datumod);
            TextView naslovTxt = (TextView)mView.findViewById(R.id.opsirnije_naslov);
            naslovTxt.setText(Html.fromHtml(naslov));
            TextView opisTxt = (TextView)mView.findViewById(R.id.opsirnije_opis);
            opisTxt.setText(Html.fromHtml(opis));
            TextView tekstTxt = (TextView)mView.findViewById(R.id.opsirnije_tekst);
            tekstTxt.setText(Html.fromHtml(tekst));
            TextView linkTxt = (TextView)mView.findViewById(R.id.opsirnije_link);
            linkTxt.setText(Html.fromHtml(link));
            TextView cijenaTxt = (TextView)mView.findViewById(R.id.opsirnije_cijena);
            cijenaTxt.setText(String.valueOf(cijena));
            TextView tipNaslovaTxt = (TextView)mView.findViewById(R.id.opsirnije_tip_nslova);
            tipNaslovaTxt.setText(Html.fromHtml(String.valueOf(tip_naslova)));

            ImageView slika = (ImageView)mView.findViewById(R.id.kat_izd_ops_img);
            Glide.with(mView).load(ApiUrls.GET_PICTURES_FULL_SIZE+fajl).into(slika);
        }

        btnPoruci = (Button)mView.findViewById(R.id.poruci_btn);

        btnPoruci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText imeTxtView = (EditText)mView.findViewById(R.id.ime_input);
                String ime = imeTxtView.getText().toString();

                EditText prezimeTxtView = (EditText)mView.findViewById(R.id.prezime_input);
                String prezime = prezimeTxtView.getText().toString();

                EditText telefonTxtView = (EditText)mView.findViewById(R.id.telefon_input);
                String telefon = telefonTxtView.getText().toString();

                EditText adresaTxtView = (EditText)mView.findViewById(R.id.adresa_input);
                String adresa = adresaTxtView.getText().toString();

                EditText emailTxtView = (EditText)mView.findViewById(R.id.email_input);
                String email = emailTxtView.getText().toString();

                if(ime.equals("") || prezime.equals("") || telefon.equals("") || adresa.equals("") || email.equals("")){
                    helper.alert(mView.getContext(), dataMissingTitle, dataMissingBody );
                }else if(email.indexOf('@') == -1){
                    helper.alert(mView.getContext(), emailNotValidTitle, emailNotValidBody );
                }
                else{
                    closeKeyboard();
                    helper.alert(mView.getContext(), questionSendTitle, questionSendBody );
//                    helper.alert(mView.getContext(), questionSendTitle, link );

                    String subject = " Porudžbina iz kataloga izdanja sa android aplikacije";
                    String message = "Ime: " + ime + " Prezime: " + prezime + " Telefon: + " + telefon + " Adresa: "
                            + adresa + " Email: " + email + " Porudžbina: " + link ;

                    try {
                        GMailSender sender = new GMailSender("djurdjecrnojevicmne@gmail.com",
                                "Djurdjecrnojevicmne123$");
                        sender.sendMail(subject, message,
                                "ne_odgovaraj@email.minmedia.me", "info@nb-cg.me");
                    } catch (Exception e) {
                        Log.e("SendMail", e.getMessage(), e);
                    }

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

    private void textPopulate(){
        TextView title = (TextView)mView.findViewById(R.id.poruci_txt);
        String titleStr= "";
        switch (MainActivity.lang){
            case 0: titleStr = helper.mne(getResources().getString(R.string.str_poruci_title)); break;
            case 1: titleStr = helper.eng(getResources().getString(R.string.str_poruci_title)); break;
        }
        title.setText(titleStr);

        EditText name = (EditText) mView.findViewById(R.id.ime_input);
        String nameStr= "";
        switch (MainActivity.lang){
            case 0: nameStr = helper.mne(getResources().getString(R.string.str_name_title)); break;
            case 1: nameStr = helper.eng(getResources().getString(R.string.str_name_title)); break;
        }
        name.setHint(nameStr);

        EditText surname = (EditText) mView.findViewById(R.id.prezime_input);
        String surnameStr= "";
        switch (MainActivity.lang){
            case 0: surnameStr = helper.mne(getResources().getString(R.string.str_surname_title)); break;
            case 1: surnameStr = helper.eng(getResources().getString(R.string.str_surname_title)); break;
        }
        surname.setHint(surnameStr);

        EditText email = (EditText) mView.findViewById(R.id.email_input);
        String emailStr= "";
        switch (MainActivity.lang){
            case 0: emailStr = helper.mne(getResources().getString(R.string.str_emails_title)); break;
            case 1: emailStr = helper.eng(getResources().getString(R.string.str_emails_title)); break;
        }
        email.setHint(emailStr);

        EditText phone = (EditText) mView.findViewById(R.id.telefon_input);
        String phoneStr= "";
        switch (MainActivity.lang){
            case 0: phoneStr = helper.mne(getResources().getString(R.string.str_phone_title)); break;
            case 1: phoneStr = helper.eng(getResources().getString(R.string.str_phone_title)); break;
        }
        phone.setHint(phoneStr);

        EditText address = (EditText) mView.findViewById(R.id.adresa_input);
        String addressStr= "";
        switch (MainActivity.lang){
            case 0: addressStr = helper.mne(getResources().getString(R.string.str_address_title)); break;
            case 1: addressStr = helper.eng(getResources().getString(R.string.str_address_title)); break;
        }
        address.setHint(addressStr);

        Button btn = (Button)mView.findViewById(R.id.poruci_btn);
        String sendStr= "";
        switch (MainActivity.lang){
            case 0: sendStr = helper.mne(getResources().getString(R.string.str_poruci_title)); break;
            case 1: sendStr = helper.eng(getResources().getString(R.string.str_poruci_title)); break;
        }
        btn.setText(sendStr);

        switch (MainActivity.lang){
            case 0: dataMissingTitle = helper.mne(getResources().getString(R.string.str_missing_title)); break;
            case 1: dataMissingTitle = helper.eng(getResources().getString(R.string.str_missing_title)); break;
        }

        switch (MainActivity.lang){
            case 0: dataMissingBody = helper.mne(getResources().getString(R.string.str_missing_body)); break;
            case 1: dataMissingBody = helper.eng(getResources().getString(R.string.str_missing_body)); break;
        }

        switch (MainActivity.lang){
            case 0: emailNotValidTitle = helper.mne(getResources().getString(R.string.str_emaivalid_title)); break;
            case 1: emailNotValidTitle = helper.eng(getResources().getString(R.string.str_emaivalid_title)); break;
        }

        switch (MainActivity.lang){
            case 0: emailNotValidBody = helper.mne(getResources().getString(R.string.str_emaivalid_body)); break;
            case 1: emailNotValidBody = helper.eng(getResources().getString(R.string.str_emaivalid_body)); break;
        }

        switch (MainActivity.lang){
            case 0: questionSendTitle = helper.mne(getResources().getString(R.string.str_poruceno_title)); break;
            case 1: questionSendTitle = helper.eng(getResources().getString(R.string.str_poruceno_title)); break;
        }

        switch (MainActivity.lang){
            case 0: questionSendBody = helper.mne(getResources().getString(R.string.str_poruceno_body)); break;
            case 1: questionSendBody = helper.eng(getResources().getString(R.string.str_poruceno_body)); break;
        }
    }

}
