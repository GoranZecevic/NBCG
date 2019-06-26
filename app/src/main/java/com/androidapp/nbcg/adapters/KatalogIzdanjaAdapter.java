package com.androidapp.nbcg.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.androidapp.nbcg.fragments.DogadjajiOpsirnije;
import com.androidapp.nbcg.R;
import com.androidapp.nbcg.models.KatalogIzdanja;

import java.util.List;

public class KatalogIzdanjaAdapter  extends RecyclerView.Adapter<KatalogIzdanjaAdapter.ViewHolder>  {

    List<KatalogIzdanja> katalogIzdanjaLista;
    View kataloziIzdanjaFragment;
    private Context context;

    public KatalogIzdanjaAdapter(View kataloziIzdanjaFragment, List<KatalogIzdanja> katalogIzdanjaLista)
    {
        this.kataloziIzdanjaFragment =  kataloziIzdanjaFragment;
        this.katalogIzdanjaLista = katalogIzdanjaLista;
    }

    @Override
    public KatalogIzdanjaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater mInflater = LayoutInflater.from(kataloziIzdanjaFragment.getContext());
        view = mInflater.inflate(R.layout.card_layout_dogadjaji,parent,false);
        context = kataloziIzdanjaFragment.getContext();
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

        holder.katalogIzdanjaId.setText(String.valueOf(katalogIzdanaj.getId()));
        holder.datumod.setText(katalogIzdanaj.getDatumod());
        holder.naslov.setText(katalogIzdanaj.getNaslov());
        holder.opis.setText(katalogIzdanaj.getOpis());
        holder.tekst.setText(katalogIzdanaj.getTekst());
        holder.link.setText(katalogIzdanaj.getLink());
        holder.cijena.setText(String.valueOf(katalogIzdanaj.getCijena()));
        holder.tip_naslova.setText(katalogIzdanaj.getTipovi_naslova());

        holder.opsirnijeBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("Opsirnije radi!" + newId);

//                Kata dogadjajiOpsirnije = new DogadjajiOpsirnije();

                Bundle bundel = new Bundle();
                bundel.putString("naslov", NASLOV);
                bundel.putString("datumod", DATUMOD);
//                bundel.putString("tip_novpsti", TIP_NOVOSTI);
                bundel.putString("opis", OPIS);

//                dogadjajiOpsirnije.setArguments(bundel);

                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
//                fragmentTransaction.replace(R.id.fragment_container, dogadjajiOpsirnije);
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
        TextView katalogIzdanjaId, datumod, naslov, opis, tekst, link, cijena, tip_naslova;
        Button opsirnijeBtn;

        CardView cv;

        public ViewHolder(View itemView)
        {
            super(itemView);
//            cv = (CardView)itemView.findViewById(R.id.katalog_izdanja_card);
//            katalogIzdanjaId = (TextView)itemView.findViewById(R.id.print_id_katalog_izdanaj);
//            datumod = (TextView)itemView.findViewById(R.id.print_datumod_katalog_izdanaj);
//            naslov = (TextView)itemView.findViewById(R.id.print_naslov_katalog_izdanaj);
//            opis = (TextView)itemView.findViewById(R.id.print_opis_katalog_izdanaj);
//            tekst = (TextView)itemView.findViewById(R.id.print_tekst_katalog_izdanaj);
//            link = (TextView)itemView.findViewById(R.id.print_link_katalog_izdanaj);
//            cijena = (TextView)itemView.findViewById(R.id.print_cijena_katalog_izdanaj);
//            tip_naslova = (TextView)itemView.findViewById(R.id.print_tip_naslova_katalog_izdanaj);
//            opsirnijeBtn = (Button)itemView.findViewById(R.id.katalog_izdanja_opsirnije_btn);

        }

    }
}
