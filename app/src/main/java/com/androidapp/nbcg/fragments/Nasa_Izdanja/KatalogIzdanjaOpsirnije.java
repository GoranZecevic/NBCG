package com.androidapp.nbcg.fragments.Nasa_Izdanja;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidapp.nbcg.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link KatalogIzdanjaOpsirnije.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link KatalogIzdanjaOpsirnije#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KatalogIzdanjaOpsirnije extends Fragment {


    private View thisFragment;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public KatalogIzdanjaOpsirnije() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment KatalogIzdanjaOpsirnije.
     */
    // TODO: Rename and change types and number of parameters
    public static KatalogIzdanjaOpsirnije newInstance(String param1, String param2) {
        KatalogIzdanjaOpsirnije fragment = new KatalogIzdanjaOpsirnije();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();

        thisFragment = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_katalog_izdanja_opsirnije, null);

        if(bundle != null){
            String datumod = bundle.getString("datumod");
            String naslov  = bundle.getString("naslov");
            String opis = bundle.getString("opis");
            String tekst = bundle.getString("tekst");
            String link = bundle.getString("link");
            double cijena = bundle.getDouble("cijena");
            String tip_naslova = bundle.getString("tip_naslova");


            TextView datumOdTxt = (TextView)thisFragment.findViewById(R.id.opsirnije_datumod);
            datumOdTxt.setText(datumod);
            TextView naslovTxt = (TextView)thisFragment.findViewById(R.id.opsirnije_naslov);
            naslovTxt.setText(naslov);
            TextView opisTxt = (TextView)thisFragment.findViewById(R.id.opsirnije_opis);
            opisTxt.setText(opis);
            TextView tekstTxt = (TextView)thisFragment.findViewById(R.id.opsirnije_tekst);
            tekstTxt.setText(tekst);
            TextView linkTxt = (TextView)thisFragment.findViewById(R.id.opsirnije_link);
            linkTxt.setText(link);
            TextView cijenaTxt = (TextView)thisFragment.findViewById(R.id.opsirnije_cijena);
            cijenaTxt.setText(String.valueOf(cijena));
            TextView tipNaslovaTxt = (TextView)thisFragment.findViewById(R.id.opsirnije_tip_nslova);
            tipNaslovaTxt.setText(String.valueOf(tip_naslova));
        }


        return thisFragment;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
