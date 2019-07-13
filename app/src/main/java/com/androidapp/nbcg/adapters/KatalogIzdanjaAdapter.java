package com.androidapp.nbcg.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidapp.nbcg.MainActivity;
import com.androidapp.nbcg.R;
import com.androidapp.nbcg.api_urls.ApiUrls;
import com.androidapp.nbcg.fragments.Nasa_Izdanja.KatalogIzdanjaOpsirnije;
import com.androidapp.nbcg.helper.Helpers;
import com.androidapp.nbcg.models.KatalogIzdanja;
import com.bumptech.glide.Glide;

import java.util.List;

public class KatalogIzdanjaAdapter  extends RecyclerView.Adapter<KatalogIzdanjaAdapter.ViewHolder>  {

    Helpers helper = new Helpers();

    List<KatalogIzdanja> katalogIzdanjaLista;
    View kataloziIzdanjaFragment;
    private Context mContext;

    public KatalogIzdanjaAdapter(View kataloziIzdanjaFragment, List<KatalogIzdanja> katalogIzdanjaLista)
    {
        this.kataloziIzdanjaFragment =  kataloziIzdanjaFragment;
        this.katalogIzdanjaLista = katalogIzdanjaLista;
    }

    @Override
    public KatalogIzdanjaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater mInflater = LayoutInflater.from(kataloziIzdanjaFragment.getContext());
        view = mInflater.inflate(R.layout.card_layout_katalog_izdanja,parent,false);
        mContext = kataloziIzdanjaFragment.getContext();
        return new KatalogIzdanjaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(KatalogIzdanjaAdapter.ViewHolder holder, final int position) {
        final KatalogIzdanja katalogIzdanaj = katalogIzdanjaLista.get(position);

        final int newId = katalogIzdanaj.getId();
        final String DATUMOD = katalogIzdanaj.getDatumod();
        final String NASLOV = katalogIzdanaj.getNaslov();
        final String OPIS = katalogIzdanaj.getOpis();
        final String TEKST = katalogIzdanaj.getTekst();
        final String LINK = katalogIzdanaj.getLink();
        final double CIJENA = katalogIzdanaj.getCijena();
        final String TIP_NASLOVA = katalogIzdanaj.getTipovi_naslova();
        final String FAJL = katalogIzdanaj.getFajl();

        holder.katalogIzdanjaId.setText(String.valueOf(katalogIzdanaj.getId()));
        holder.datumod.setText(katalogIzdanaj.getDatumod());
        holder.naslov.setText(Html.fromHtml(katalogIzdanaj.getNaslov()));
        holder.opis.setText(Html.fromHtml(katalogIzdanaj.getOpis()));
        holder.tekst.setText(Html.fromHtml(katalogIzdanaj.getTekst()));
        holder.link.setText(Html.fromHtml(katalogIzdanaj.getLink()));
        holder.cijena.setText(String.valueOf(katalogIzdanaj.getCijena()));
        holder.tip_naslova.setText(Html.fromHtml(katalogIzdanaj.getTipovi_naslova()));

        Glide.with(mContext).load(ApiUrls.GET_PICTURES+FAJL).into(holder.slika);

        holder.opsirnijeBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("Opsirnije radi!" + newId);


//                helper.goToUrl ( "https://www.nb-cg.me/download.php?file=43", context);

                KatalogIzdanjaOpsirnije katalogIzdanjaOpsirnije = new KatalogIzdanjaOpsirnije();

                Bundle bundel = new Bundle();
                bundel.putString("datumod", DATUMOD);
                bundel.putString("naslov", NASLOV);
                bundel.putString("opis", OPIS);
                bundel.putString("tekst", TEKST);
                bundel.putString("link", LINK);
                bundel.putDouble("cijena", CIJENA);
                bundel.putString("tip_naslova", TIP_NASLOVA);
                bundel.putString("fajl", FAJL);

                katalogIzdanjaOpsirnije.setArguments(bundel);

                FragmentManager manager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, katalogIzdanjaOpsirnije);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

    }

    @Override
    public int getItemCount() {
        return katalogIzdanjaLista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView katalogIzdanjaId, datumod, naslov, opis, tekst, link, cijena, cijenaTitle, tip_naslova;
        ImageView slika;
        Button opsirnijeBtn;

        CardView cv;

        public ViewHolder(View itemView)
        {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.katalog_izdanja_card);
            katalogIzdanjaId = (TextView)itemView.findViewById(R.id.print_id_katalog_izdanaj);
            datumod = (TextView)itemView.findViewById(R.id.print_datumod_katalog_izdanaj);
            naslov = (TextView)itemView.findViewById(R.id.print_naslov_katalog_izdanaj);
            opis = (TextView)itemView.findViewById(R.id.print_opis_katalog_izdanaj);
            tekst = (TextView)itemView.findViewById(R.id.print_tekst_katalog_izdanaj);
            link = (TextView)itemView.findViewById(R.id.print_link_katalog_izdanaj);
            cijena = (TextView)itemView.findViewById(R.id.print_cijena_katalog_izdanaj);
            cijenaTitle = (TextView)itemView.findViewById(R.id.price);
            tip_naslova = (TextView)itemView.findViewById(R.id.print_tip_naslova_katalog_izdanaj);
            slika = (ImageView)itemView.findViewById(R.id.img_katalog);
            opsirnijeBtn = (Button)itemView.findViewById(R.id.katalog_izdanja_opsirnije_btn);

            String opsirnijeStr = "";
            String cijenaTitl = "";
            switch (MainActivity.lang) {
                case 0:
                    opsirnijeStr = "NARUČITE";
                    cijenaTitl = "Cijena:";
                    break;
                case 1:
                    opsirnijeStr = "ORDER IT";
                    cijenaTitl = "Price:";
                    break;
            }
            opsirnijeBtn.setText(opsirnijeStr);
            cijenaTitle.setText(cijenaTitl);
        }

    }
}
