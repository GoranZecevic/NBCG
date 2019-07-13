package com.androidapp.nbcg.adapters;

import android.content.Context;
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
import com.androidapp.nbcg.models.KatalogIzdanja;
import com.androidapp.nbcg.helper.Helpers;
import com.bumptech.glide.Glide;

import java.util.List;

public class E_PublikacijeAdapter extends RecyclerView.Adapter<E_PublikacijeAdapter.ViewHolder>  {

        Helpers helper = new Helpers();

        List<KatalogIzdanja> katalogIzdanjaLista;
        View kataloziIzdanjaFragment;
        private Context mContext;

        public E_PublikacijeAdapter(View kataloziIzdanjaFragment, List<KatalogIzdanja> katalogIzdanjaLista)
        {
            this.kataloziIzdanjaFragment =  kataloziIzdanjaFragment;
            this.katalogIzdanjaLista = katalogIzdanjaLista;
        }

        @Override
        public E_PublikacijeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view ;
                LayoutInflater mInflater = LayoutInflater.from(kataloziIzdanjaFragment.getContext());
                view = mInflater.inflate(R.layout.card_layout_katalog_izdanja,parent,false);
                mContext = kataloziIzdanjaFragment.getContext();
                return new E_PublikacijeAdapter.ViewHolder(view);
                }

        @Override
        public void onBindViewHolder(E_PublikacijeAdapter.ViewHolder holder, final int position) {
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

        Glide.with(mContext).load(FAJL).into(holder.slika);

        holder.preuzetiBtn.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
                System.out.println("Opsirnije radi!" + newId);

                helper.goToUrl (ApiUrls.FILE_DOWNLOAD + newId, mContext);

                }
            });
        }

        @Override
        public int getItemCount() {
                return katalogIzdanjaLista.size();
                }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView katalogIzdanjaId, datumod, naslov, opis, tekst, link, cijena, cijenaTitle, euro, tip_naslova;
        ImageView slika;
        Button preuzetiBtn;

        CardView cv;

        public ViewHolder(View itemView)
        {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.katalog_izdanja_card);
            katalogIzdanjaId = (TextView)itemView.findViewById(R.id.print_id_katalog_izdanaj);
            datumod = (TextView)itemView.findViewById(R.id.print_datumod_katalog_izdanaj);
            naslov = (TextView)itemView.findViewById(R.id.print_naslov_katalog_izdanaj);
            opis = (TextView)itemView.findViewById(R.id.print_opis_katalog_izdanaj);
            opis.setVisibility(View.VISIBLE);
            tekst = (TextView)itemView.findViewById(R.id.print_tekst_katalog_izdanaj);
            link = (TextView)itemView.findViewById(R.id.print_link_katalog_izdanaj);

            cijena = (TextView)itemView.findViewById(R.id.print_cijena_katalog_izdanaj);
            cijena.setVisibility(View.GONE);

            cijenaTitle = (TextView)itemView.findViewById(R.id.price);
            cijenaTitle.setVisibility(View.GONE);

            euro = (TextView)itemView.findViewById(R.id.e);
            euro.setVisibility(View.GONE);

            tip_naslova = (TextView)itemView.findViewById(R.id.print_tip_naslova_katalog_izdanaj);
            slika = (ImageView)itemView.findViewById(R.id.img_katalog);
            preuzetiBtn = (Button)itemView.findViewById(R.id.katalog_izdanja_opsirnije_btn);

            String preuzeti = "";
            switch (MainActivity.lang) {
                case 0:
                    preuzeti = "PREUZETI";
                    break;
                case 1:
                    preuzeti = "DOWNLOAD";
                    break;
            }
            preuzetiBtn.setText(preuzeti);

        }

    }
}