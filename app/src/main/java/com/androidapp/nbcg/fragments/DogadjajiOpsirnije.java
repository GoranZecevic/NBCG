package com.androidapp.nbcg.fragments;

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
 * {@link DogadjajiOpsirnije.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DogadjajiOpsirnije#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DogadjajiOpsirnije extends Fragment {

    private View thisFragment;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DogadjajiOpsirnije() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DogadjajiOpsirnije.
     */
    // TODO: Rename and change types and number of parameters
    public static DogadjajiOpsirnije newInstance(String param1, String param2) {
        DogadjajiOpsirnije fragment = new DogadjajiOpsirnije();
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

        thisFragment = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_dogadjaji_opsirnije, null);

        if(bundle != null){
            String naslov  = bundle.getString("naslov");
            String datumod = bundle.getString("datumod");
            String tip_novpsti = bundle.getString("tip_novpsti");
            String opis = bundle.getString("opis");


            TextView naslovTxt = (TextView)thisFragment.findViewById(R.id.opsirnije_naslov);
            naslovTxt.setText(naslov);
            TextView datumOdTxt = (TextView)thisFragment.findViewById(R.id.opsirnije_datumod);
            datumOdTxt.setText(datumod);
            TextView tipNovostiTxt = (TextView)thisFragment.findViewById(R.id.opsirnije_tip_novpsti);
            tipNovostiTxt.setText(tip_novpsti);
            TextView opisTxt = (TextView)thisFragment.findViewById(R.id.opsirnije_opis);
            opisTxt.setText(opis);
        }


        return thisFragment;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
