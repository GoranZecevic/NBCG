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
import android.widget.ImageView;
import android.widget.TextView;

import com.androidapp.nbcg.R;
import com.androidapp.nbcg.api_urls.ApiUrls;
import com.androidapp.nbcg.fragments.GalerijaZoom;
import com.androidapp.nbcg.models.Slike;
import com.bumptech.glide.Glide;

import java.util.List;

public class GalerijaAdapter extends RecyclerView.Adapter<GalerijaAdapter.ViewHolder> {

        List<Slike> SlikeiLista;
        View galerijaFragment;
        private Context mContext;

        public GalerijaAdapter(View galerijaFragment, List<Slike> SlikeiLista)
        {
            this.galerijaFragment =  galerijaFragment;
            this.SlikeiLista = SlikeiLista;
        }

        @Override
        public GalerijaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view ;
            LayoutInflater mInflater = LayoutInflater.from(galerijaFragment.getContext());
            view = mInflater.inflate(R.layout.card_layout_slike,parent,false);
            mContext = galerijaFragment.getContext();
            return new ViewHolder(view);
        }


        @Override
        public void onBindViewHolder(GalerijaAdapter.ViewHolder holder, final int position) {
            final Slike slike = SlikeiLista.get(position);

            final String NASLOV = slike.getNaslov();
            final String FAJL = slike.getFajl();


            holder.naslov.setText(slike.getNaslov());
            Glide.with(mContext).load(ApiUrls.GET_PICTURES + FAJL).into(holder.slika);

            holder.slika.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    GalerijaZoom galerijaZoom = new GalerijaZoom();

                    Bundle bundel = new Bundle();

                    bundel.putString("naslov", NASLOV);
                    bundel.putString("fajl", FAJL);

                    galerijaZoom.setArguments(bundel);

                    FragmentManager manager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = manager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, galerijaZoom);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                }
            });
        }

        @Override
        public int getItemCount() {
            return SlikeiLista.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder
        {
            TextView naslov;
            ImageView slika;


            CardView cv;

            public ViewHolder(View itemView)
            {
                super(itemView);
                cv = (CardView)itemView.findViewById(R.id.galerija_card);
                naslov = (TextView)itemView.findViewById(R.id.galerija_naslov);
                slika = (ImageView)itemView.findViewById(R.id.galerija_slika);


            }

        }
    }

