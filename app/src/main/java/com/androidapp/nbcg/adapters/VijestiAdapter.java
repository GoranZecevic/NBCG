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
import com.androidapp.nbcg.models.Vijesti;
import com.androidapp.nbcg.R;
import java.util.List;

public class VijestiAdapter  extends RecyclerView.Adapter<VijestiAdapter.ViewHolder> {

    List<Vijesti> VijestiLista;
    View dogadjajiFragment;
    private Context context;

    public VijestiAdapter(View dogadjajiFragment, List<Vijesti> VijestiLIsta)
    {
        this.dogadjajiFragment =  dogadjajiFragment;
        this.VijestiLista = VijestiLIsta;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater mInflater = LayoutInflater.from(dogadjajiFragment.getContext());
        view = mInflater.inflate(R.layout.card_layout_dogadjaji,parent,false);
        context = dogadjajiFragment.getContext();
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Vijesti vijesti = VijestiLista.get(position);

        final int newId = vijesti.getId();
        final String NASLOV = vijesti.getNaslov();
        final String DATUMOD = vijesti.getDatumod();
        final String TIP_NOVOSTI = vijesti.getTip_novosti();
        final String OPIS = vijesti.getOpis();

            holder.vijestiId.setText(String.valueOf(vijesti.getId()));
            holder.datumod.setText(vijesti.getDatumod());
            holder.naslov.setText(vijesti.getNaslov());
            holder.opis.setText(vijesti.getOpis());
            holder.description.setText(vijesti.getDescription());
            holder.tip_novosti.setText(vijesti.getTip_novosti());

            holder.opsirnijeBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    System.out.println("Opsirnije radi!" + newId);

                    DogadjajiOpsirnije dogadjajiOpsirnije = new DogadjajiOpsirnije();

                    Bundle bundel = new Bundle();
                    bundel.putString("naslov", NASLOV);
                    bundel.putString("datumod", DATUMOD);
                    bundel.putString("tip_novpsti", TIP_NOVOSTI);
                    bundel.putString("opis", OPIS);

                    dogadjajiOpsirnije.setArguments(bundel);

                    FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = manager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, dogadjajiOpsirnije);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                }
            });

    }

    @Override
    public int getItemCount() {
        return VijestiLista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView vijestiId, datumod, naslov, opis, description, tip_novosti;
        Button opsirnijeBtn;

        CardView cv;

        public ViewHolder(View itemView)
        {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.vijesti_card);
            vijestiId = (TextView)itemView.findViewById(R.id.print_id);
            datumod = (TextView)itemView.findViewById(R.id.print_datumod);
            naslov = (TextView)itemView.findViewById(R.id.print_naslov);
            opis = (TextView)itemView.findViewById(R.id.print_opis);
            description = (TextView)itemView.findViewById(R.id.print_description);
            tip_novosti = (TextView)itemView.findViewById(R.id.print_tip_novosti);
            opsirnijeBtn = (Button)itemView.findViewById(R.id.dogadjaji_opsirnije_btn);

        }

    }
}