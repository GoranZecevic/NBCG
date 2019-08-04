package com.androidapp.nbcg.fragments;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.androidapp.nbcg.ExpandableListDataPump;
import com.androidapp.nbcg.MainActivity;
import com.androidapp.nbcg.R;
import com.androidapp.nbcg.api_urls.ApiUrls;
import com.androidapp.nbcg.fragments.Nasa_Izdanja.KatalogIzdanja;
import com.androidapp.nbcg.fragments.Usluge.ZaBibliotekare;
import com.androidapp.nbcg.fragments.Usluge.ZaIzdavace;
import com.androidapp.nbcg.fragments.Usluge.ZaKorisnike;
import com.androidapp.nbcg.helper.Helpers;


public class Pocetna extends Fragment {

    public int language = MainActivity.lang;
    Helpers helper = new Helpers();

    private View mView;
    ViewFlipper v_flipper;
    private static  boolean exist;

    Button btnKatalogKolekcije;

    int[] images = {
            R.drawable.hall1,
            R.drawable.hall2,
            R.drawable.hall3
    };

    private OnFragmentInteractionListener mListener;

    public Pocetna() {
    }

    public static Pocetna newInstance(String param1, String param2) {
        Pocetna fragment = new Pocetna();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void flip_image(int i){
        ImageView view  = new ImageView((Context) getHost());
        view.setBackgroundResource(i);
        v_flipper.addView(view);
        v_flipper.setFlipInterval(4000);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation((Context) getHost(), android.R.anim.slide_in_left);
        v_flipper.setOutAnimation((Context) getHost(), android.R.anim.slide_out_right);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_pocetna, null);
        getActionBar().setTitle("Pocetna");

        textPopulate();
        handleAnimation();

        System.out.println("Jezik je: "+ language);

        if(exist != true){
            ExpandableListDataPump expandableListDataPump = new ExpandableListDataPump(mView.getContext());
            exist = true;
        }

        v_flipper = (ViewFlipper)mView.findViewById(R.id.v_flipper);
        for(int i=0; i<images.length; i++){
            flip_image(images[i]);
        }

        buttonHandler(new ZaKorisnike(), R.id.za_korisnike_btn);
        buttonHandler(new ZaKorisnike(), R.id.za_korisnike_opsirnije_btn);

        buttonHandler(new ZaIzdavace(), R.id.za_izdavace_btn);
        buttonHandler(new ZaIzdavace(), R.id.za_izdavace_opsirnije_btn);

        buttonHandler(new ZaBibliotekare(), R.id.za_bibiliotekare_btn);
        buttonHandler(new ZaBibliotekare(), R.id.za_bibliotekare_opsirnije_btn);

        buttonHandler(new Dogadjaji(), R.id.dogadjaji_btn);
        buttonHandler(new Dogadjaji(), R.id.dogadjaji_opsirnije_btn);

        buttonHandlerWeb(ApiUrls.E_KATALOG_NBCG, R.id.enbcg_btn);

        buttonHandlerWeb(ApiUrls.DIG_KOLEKCIJA_PPNJ, R.id.ppn_dig_btn);

        buttonHandlerWeb(ApiUrls.E_KATALOG_CG, R.id.ekatalog_btn);

        buttonHandlerWeb(ApiUrls.DIG_BIBL_CG, R.id.dig_bibl_btn);

        buttonHandler(new KatalogIzdanja(), R.id.katal_izd_btn);

        buttonHandlerWeb(ApiUrls.CG_BIBLIOGRAFIJA, R.id.cg_bibliog_btn);

        buttonHandlerWeb(ApiUrls.EUROPEANA, R.id.europeana_btn);

        imageButtonHandlerWeb(ApiUrls.YOUTUBE, R.id.nbcg_yt);

        imageButtonHandlerWeb(ApiUrls.FB, R.id.nbcg_fb);

        return mView;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public ActionBar getActionBar() {
        return ((MainActivity) getActivity()).getSupportActionBar();
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

        Button pocetnaBtn =(Button) mView.findViewById(R.id.o_nama_title);
        String pocetnaBtnStr= "";
        switch (language){
            case 0: pocetnaBtnStr = helper.mne(getResources().getString(R.string.str_pocetna)); break;
            case 1: pocetnaBtnStr = helper.eng(getResources().getString(R.string.str_pocetna)); break;
        }
        pocetnaBtn.setText(pocetnaBtnStr);

        String pocetnaStr= "";
        switch (language){
            case 0: pocetnaStr = helper.mne(getResources().getString(R.string.str_pocetna)); break;
            case 1: pocetnaStr = helper.eng(getResources().getString(R.string.str_pocetna)); break;
        }
        getActionBar().setTitle(pocetnaStr);

        TextView title = (TextView) mView.findViewById(R.id.str_home_title_inner);
        String titleStr= "";
        switch (language){
            case 0: titleStr = helper.mne(getResources().getString(R.string.str_home_title)); break;
            case 1: titleStr = helper.eng(getResources().getString(R.string.str_home_title)); break;
        }
        title.setText(titleStr);

        TextView home_body = (TextView) mView.findViewById(R.id.str_home_body);
        String homeBodyStr= "";
        switch (language){
            case 0: homeBodyStr = helper.mne(getResources().getString(R.string.str_home_body)); break;
            case 1: homeBodyStr = helper.eng(getResources().getString(R.string.str_home_body)); break;
        }
        home_body.setText(homeBodyStr);

        Button zaKorisnikeBtn =(Button) mView.findViewById(R.id.za_korisnike_btn);
        String zaKorisnikeStr= "";
        switch (language){
            case 0: zaKorisnikeStr = helper.mne(getResources().getString(R.string.str_za_korisnike_title)); break;
            case 1: zaKorisnikeStr = helper.eng(getResources().getString(R.string.str_za_korisnike_title)); break;
        }
        zaKorisnikeBtn.setText(zaKorisnikeStr);

        TextView zaKorisnike = (TextView) mView.findViewById(R.id.za_korisnike_txt);
        String zaKorisnikeTxtStr= "";
        switch (language){
            case 0: zaKorisnikeTxtStr = helper.mne(getResources().getString(R.string.str_za_korisnike_txt)); break;
            case 1: zaKorisnikeTxtStr = helper.eng(getResources().getString(R.string.str_za_korisnike_txt)); break;
        }
        zaKorisnike.setText(zaKorisnikeTxtStr);

        Button opsitnijeZaKorisnkike = (Button)mView.findViewById(R.id.za_korisnike_opsirnije_btn);
        Button opsitnijeZaIzdavace = (Button)mView.findViewById(R.id.za_izdavace_opsirnije_btn);
        Button opsitnijeZaBibliotekare = (Button)mView.findViewById(R.id.za_bibliotekare_opsirnije_btn);
        Button opsitnijeDogadjaji = (Button)mView.findViewById(R.id.dogadjaji_opsirnije_btn);
        String opsirnijeStr= "";
        switch (language){
            case 0: opsirnijeStr = helper.mne(getResources().getString(R.string.str__opsirnije)); break;
            case 1: opsirnijeStr = helper.eng(getResources().getString(R.string.str__opsirnije)); break;
        }
        opsitnijeZaKorisnkike.setText(opsirnijeStr);
        opsitnijeZaIzdavace.setText(opsirnijeStr);
        opsitnijeZaBibliotekare.setText(opsirnijeStr);
        opsitnijeDogadjaji.setText(opsirnijeStr);

        Button zaIzdavaceBtn =(Button) mView.findViewById(R.id.za_izdavace_btn);
        String zaIzdavaceStr= "";
        switch (language){
            case 0: zaIzdavaceStr = helper.mne(getResources().getString(R.string.str_za_izdavace_title)); break;
            case 1: zaIzdavaceStr = helper.eng(getResources().getString(R.string.str_za_izdavace_title)); break;
        }
        zaIzdavaceBtn.setText(zaIzdavaceStr);

        TextView zaIzdavace = (TextView) mView.findViewById(R.id.za_izavace_txt);
        String zaIzdavaceTxtStr= "";
        switch (language){
            case 0: zaIzdavaceTxtStr = helper.mne(getResources().getString(R.string.str_za_izdavace_txt)); break;
            case 1: zaIzdavaceTxtStr = helper.eng(getResources().getString(R.string.str_za_izdavace_txt)); break;
        }
        zaIzdavace.setText(zaIzdavaceTxtStr);

        Button zaBibliotekareBtn =(Button) mView.findViewById(R.id.za_bibiliotekare_btn);
        String zaBibliotekareStr= "";
        switch (language){
            case 0: zaBibliotekareStr = helper.mne(getResources().getString(R.string.str_za_bibliotekare_title)); break;
            case 1: zaBibliotekareStr = helper.eng(getResources().getString(R.string.str_za_bibliotekare_title)); break;
        }
        zaBibliotekareBtn.setText(zaBibliotekareStr);

        TextView zaBibliotekare = (TextView) mView.findViewById(R.id.za_bibliotekare_txt);
        String zaBibliotekareTxtStr= "";
        switch (language){
            case 0: zaBibliotekareTxtStr = helper.mne(getResources().getString(R.string.str_za_bibliotekare_txt)); break;
            case 1: zaBibliotekareTxtStr = helper.eng(getResources().getString(R.string.str_za_bibliotekare_txt)); break;
        }
        zaBibliotekare.setText(zaBibliotekareTxtStr);

        Button dogadjajiBtn =(Button) mView.findViewById(R.id.dogadjaji_btn);
        String dogadjajiStr= "";
        switch (language){
            case 0: dogadjajiStr = helper.mne(getResources().getString(R.string.str_dogadjaji_title)); break;
            case 1: dogadjajiStr = helper.eng(getResources().getString(R.string.str_dogadjaji_title)); break;
        }
        dogadjajiBtn.setText(dogadjajiStr);

        TextView dogadjajiOpsirnije = (TextView) mView.findViewById(R.id.dogadjaji_txt);
        String dogadjajiOpsirnijeStr= "";
        switch (language){
            case 0: dogadjajiOpsirnijeStr = helper.mne(getResources().getString(R.string.str_dogadjaji_opsirnije)); break;
            case 1: dogadjajiOpsirnijeStr = helper.eng(getResources().getString(R.string.str_dogadjaji_opsirnije)); break;
        }
        dogadjajiOpsirnije.setText(dogadjajiOpsirnijeStr);

        TextView katalogKolekcije = (TextView) mView.findViewById(R.id.katalozi_kolekcije);
        String katalogKolekcijeStr= "";
        switch (language){
            case 0: katalogKolekcijeStr = helper.mne(getResources().getString(R.string.str_katalozi_kolekcije_title)); break;
            case 1: katalogKolekcijeStr = helper.eng(getResources().getString(R.string.str_katalozi_kolekcije_title)); break;
        }
        katalogKolekcije.setText(katalogKolekcijeStr);

        Button ekatalogNbcg =(Button) mView.findViewById(R.id.enbcg_btn);
        String ekatalogNbcgStr= "";
        switch (language){
            case 0: ekatalogNbcgStr = helper.mne(getResources().getString(R.string.str_ekatlognbcg_title)); break;
            case 1: ekatalogNbcgStr = helper.eng(getResources().getString(R.string.str_ekatlognbcg_title)); break;
        }
        ekatalogNbcg.setText(ekatalogNbcgStr);

        Button dkPpnjNbcg =(Button) mView.findViewById(R.id.ppn_dig_btn);
        String dkPpnjNbcgStr= "";
        switch (language){
            case 0: dkPpnjNbcgStr = helper.mne(getResources().getString(R.string.str_dk_pdpnj_title)); break;
            case 1: dkPpnjNbcgStr = helper.eng(getResources().getString(R.string.str_dk_pdpnj_title)); break;
        }
        dkPpnjNbcg.setText(dkPpnjNbcgStr);

        Button ekcg =(Button) mView.findViewById(R.id.ekatalog_btn);
        String ekcgStr= "";
        switch (language){
            case 0: ekcgStr = helper.mne(getResources().getString(R.string.str_ekcg_title)); break;
            case 1: ekcgStr = helper.eng(getResources().getString(R.string.str_ekcg_title)); break;
        }
        ekcg.setText(ekcgStr);

        Button dbcg =(Button) mView.findViewById(R.id.dig_bibl_btn);
        String dbcgStr= "";
        switch (language){
            case 0: dbcgStr = helper.mne(getResources().getString(R.string.str_dbcg_title)); break;
            case 1: dbcgStr = helper.eng(getResources().getString(R.string.str_dbcg_title)); break;
        }
        dbcg.setText(dbcgStr);

        Button katalogIzdanaj =(Button) mView.findViewById(R.id.katal_izd_btn);
        String katalogIzdanajStr= "";
        switch (language){
            case 0: katalogIzdanajStr = helper.mne(getResources().getString(R.string.str_katalog_izdanja_title)); break;
            case 1: katalogIzdanajStr = helper.eng(getResources().getString(R.string.str_katalog_izdanja_title)); break;
        }
        katalogIzdanaj.setText(katalogIzdanajStr);

        Button cgBibliografija =(Button) mView.findViewById(R.id.cg_bibliog_btn);
        String cgBibliografijajStr= "";
        switch (language){
            case 0: cgBibliografijajStr = helper.mne(getResources().getString(R.string.str_cg_bibliografija_title)); break;
            case 1: cgBibliografijajStr = helper.eng(getResources().getString(R.string.str_cg_bibliografija_title)); break;
        }
        cgBibliografija.setText(cgBibliografijajStr);

        Button europeana =(Button) mView.findViewById(R.id.europeana_btn);
        String europeanaStr= "";
        switch (language){
            case 0: europeanaStr = helper.mne(getResources().getString(R.string.str_europeana_title)); break;
            case 1: europeanaStr = helper.eng(getResources().getString(R.string.str_europeana_title)); break;
        }
        europeana.setText(europeanaStr);

        TextView posetiteNas =(TextView) mView.findViewById(R.id.podjeli);
        String posetiteNasStr= "";
        switch (language){
            case 0: posetiteNasStr = helper.mne(getResources().getString(R.string.str_posetine_nas)); break;
            case 1: posetiteNasStr = helper.eng(getResources().getString(R.string.str_posetine_nas)); break;
        }
        posetiteNas.setText(posetiteNasStr);
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

    public void handleAnimation(){
        ScrollView title = (ScrollView) mView.findViewById(R.id.home_scroll);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(title, View.ALPHA, 0.0f, 1.0f);
        alpha.setDuration(400);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(alpha);
        animatorSet.start();
    }
}
