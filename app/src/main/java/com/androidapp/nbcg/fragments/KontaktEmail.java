package com.androidapp.nbcg.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.androidapp.nbcg.MainActivity;
import com.androidapp.nbcg.R;
import com.androidapp.nbcg.helper.GMailSender;
import com.androidapp.nbcg.helper.Helpers;


public class KontaktEmail extends Fragment {

    View mView;
    Button btnPosalji;

    String dataMissingTitle;
    String dataMissingBody;
    String emailNotValidTitle;
    String emailNotValidBody;
    String questionSendTitle;
    String questionSendBody;


    private OnFragmentInteractionListener mListener;

    Helpers helper = new Helpers();

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

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        textPopulate();

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
                    helper.alert(mView.getContext(), dataMissingTitle, dataMissingBody );
                }else if(email.indexOf('@') == -1){
                    helper.alert(mView.getContext(), emailNotValidTitle, emailNotValidBody );
                }
                else{
                    closeKeyboard();
                    helper.alertInfo(mView.getContext(), questionSendTitle, questionSendBody );

                    String subject = " Pitanje kontakt forma android aplikacija";
                    String message = "Ime: " + ime + " Prezime: " + prezime + " Email: " + email + " Telefon: " + telefon + " Pitanje: " + pitanje;

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
        TextView title = (TextView)mView.findViewById(R.id.posalji_txt);
        String titleStr= "";
        switch (MainActivity.lang){
            case 0: titleStr = helper.mne(getResources().getString(R.string.str_email_title)); break;
            case 1: titleStr = helper.eng(getResources().getString(R.string.str_email_title)); break;
        }
        title.setText(titleStr);

        EditText name = (EditText) mView.findViewById(R.id.kontakt_ime_input);
        String nameStr= "";
        switch (MainActivity.lang){
            case 0: nameStr = helper.mne(getResources().getString(R.string.str_name_title)); break;
            case 1: nameStr = helper.eng(getResources().getString(R.string.str_name_title)); break;
        }
        name.setHint(nameStr);

        EditText surname = (EditText) mView.findViewById(R.id.kontakt_prezime_input);
        String surnameStr= "";
        switch (MainActivity.lang){
            case 0: surnameStr = helper.mne(getResources().getString(R.string.str_surname_title)); break;
            case 1: surnameStr = helper.eng(getResources().getString(R.string.str_surname_title)); break;
        }
        surname.setHint(surnameStr);

        EditText email = (EditText) mView.findViewById(R.id.kontakt_email_input);
        String emailStr= "";
        switch (MainActivity.lang){
            case 0: emailStr = helper.mne(getResources().getString(R.string.str_emails_title)); break;
            case 1: emailStr = helper.eng(getResources().getString(R.string.str_emails_title)); break;
        }
        email.setHint(emailStr);

        EditText phone = (EditText) mView.findViewById(R.id.kontakt_telefon_input);
        String phoneStr= "";
        switch (MainActivity.lang){
            case 0: phoneStr = helper.mne(getResources().getString(R.string.str_phone_title)); break;
            case 1: phoneStr = helper.eng(getResources().getString(R.string.str_phone_title)); break;
        }
        phone.setHint(phoneStr);

        EditText question = (EditText) mView.findViewById(R.id.pitanje);
        String questionStr= "";
        switch (MainActivity.lang){
            case 0: questionStr = helper.mne(getResources().getString(R.string.str_question)); break;
            case 1: questionStr = helper.eng(getResources().getString(R.string.str_question)); break;
        }
        question.setHint(questionStr);

        Button btn = (Button)mView.findViewById(R.id.pitanje_btn);
        String sendStr= "";
        switch (MainActivity.lang){
            case 0: sendStr = helper.mne(getResources().getString(R.string.str_question_title)); break;
            case 1: sendStr = helper.eng(getResources().getString(R.string.str_question_title)); break;
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
            case 0: questionSendTitle = helper.mne(getResources().getString(R.string.str_pitanje_prosledjeno_title)); break;
            case 1: questionSendTitle = helper.eng(getResources().getString(R.string.str_pitanje_prosledjeno_title)); break;
        }

        switch (MainActivity.lang){
            case 0: questionSendBody = helper.mne(getResources().getString(R.string.str_pitanje_prosledjeno_body)); break;
            case 1: questionSendBody = helper.eng(getResources().getString(R.string.str_pitanje_prosledjeno_body)); break;
        }
    }

}

