package com.androidapp.nbcg.adapters;

import android.content.Context;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.androidapp.nbcg.R;
import com.androidapp.nbcg.api_urls.ApiUrls;
import com.androidapp.nbcg.helper.Helpers;
import com.androidapp.nbcg.models.JavneNabavke;

import java.util.List;

public class OrganizacijaAdapter extends RecyclerView.Adapter<OrganizacijaAdapter.ViewHolder> {

    private Helpers helper = new Helpers();
    List<JavneNabavke> JavneNabavkeLista;
    View organizacijaFragment;
    private Context mContext;

    public OrganizacijaAdapter(View organizacijaFragment, List<JavneNabavke> JavneNabavkeLista)
    {
        this.organizacijaFragment =  organizacijaFragment;
        this.JavneNabavkeLista = JavneNabavkeLista;
    }

    @Override
    public OrganizacijaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater mInflater = LayoutInflater.from(organizacijaFragment.getContext());
        view = mInflater.inflate(R.layout.card_layout_organizacija ,parent,false);
        mContext = organizacijaFragment.getContext();
        return new OrganizacijaAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(OrganizacijaAdapter.ViewHolder holder, final int position) {
        final JavneNabavke javneNabavke = JavneNabavkeLista.get(position);

        final int ID = javneNabavke.getId();
        final String NASLOV = javneNabavke.getNaslov();
        final String FAJL = javneNabavke.getFajl();


        holder.id.setText(String.valueOf(ID));
        holder.nabavkaBtn.setText(NASLOV);
        holder.fajl.setText(javneNabavke.getFajl());


        holder.nabavkaBtn.setOnClickListener(new View.OnClickListener() {

            String url = ApiUrls.GET_DOCUMENTS + FAJL;

            @Override
            public void onClick(View v) {
                helper.goToUrl(url, mContext);
            }
        });
    }

    @Override
    public int getItemCount() {
        return JavneNabavkeLista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView id, fajl;
        Button nabavkaBtn;


        CardView cv;

        public ViewHolder(View itemView)
        {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.organizacije_card);
            id = (TextView)itemView.findViewById(R.id.nabavke_id);
            nabavkaBtn = (Button)itemView.findViewById(R.id.nabavke_btn);
            fajl = (TextView)itemView.findViewById(R.id.nabavke_fajl);

        }
    }
}