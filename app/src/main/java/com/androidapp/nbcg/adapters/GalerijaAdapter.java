package com.androidapp.nbcg.adapters;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

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
        public void onBindViewHolder(final GalerijaAdapter.ViewHolder holder, final int position) {
            final Slike slike = SlikeiLista.get(position);

            final String NASLOV = slike.getNaslov();
            final String FAJL = slike.getFajl();


            holder.naslov.setText(slike.getNaslov());
            Glide.with(mContext).load(ApiUrls.GET_PICTURES + FAJL)
                    .listener(new RequestListener<Drawable>() {
                                  @Override
                                  public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                      return false;
                                  }

                                  @Override
                                  public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                                      handleAnimation(holder.slika);
                                      return false;
                                  }
                              }
                    )
                    .into(holder.slika);

            holder.slika.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    handleFade(holder.slika);

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
            final ImageView slika;


            CardView cv;

            public ViewHolder(View itemView)
            {
                super(itemView);
                cv = (CardView)itemView.findViewById(R.id.galerija_card);
                naslov = (TextView)itemView.findViewById(R.id.galerija_naslov);
                slika = (ImageView)itemView.findViewById(R.id.galerija_slika);


            }

        }

    long animatorDuration = 200;
    public void handleAnimation(View view){
        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, View.ALPHA, 0.0f, 1.0f);
        alpha.setDuration(800);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(alpha);
        animatorSet.start();
    }

    public void handleFade(View view){
        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, View.ALPHA, 1.0f, 0.0f);
        alpha.setDuration(800);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(alpha);
        animatorSet.start();
    }
    }

