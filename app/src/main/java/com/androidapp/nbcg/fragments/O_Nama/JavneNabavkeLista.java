package com.androidapp.nbcg.fragments.O_Nama;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.net.Uri;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.androidapp.nbcg.MainActivity;
import com.androidapp.nbcg.R;
import com.androidapp.nbcg.adapters.JavneNabavkeAdapter;
import com.androidapp.nbcg.api_urls.ApiUrls;
import com.androidapp.nbcg.helper.Helpers;
import com.androidapp.nbcg.models.JavneNabavke;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JavneNabavkeLista extends Fragment {

    private RequestQueue requestQueue;
    private String noConnectionTitle;
    private String noConnectionBody;
    private ArrayList<JavneNabavke> arrayList;

    private Helpers helper = new Helpers();
    private int language = MainActivity.lang;

    private View mView;

    private JavneNabavkeAdapter adapter;
    private RecyclerView recycleView;

    private OnFragmentInteractionListener mListener;


    LottieAnimationView lottieAnimationView;

    public JavneNabavkeLista() {
    }

    public static JavneNabavkeLista newInstance(String param1, String param2) {
        JavneNabavkeLista fragment = new JavneNabavkeLista();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_javne_nabavke_lista, container, false);
        handleAnimation();
        textPopulate();

        lottieAnimationView = (LottieAnimationView) mView.findViewById(R.id.animation_view);
        lottieAnimationView.setImageAssetsFolder("images/");
        lottieAnimationView.setAnimation("data.json");
        lottieAnimationView.loop(true);
        lottieAnimationView.playAnimation();

        arrayList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this.getContext());
        recycleView = (RecyclerView)mView.findViewById(R.id.recycler_view_javne_nabavke);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycleView.setLayoutManager(layoutManager);
        PARSEDATA();
        return mView;
    }

    public void PARSEDATA() {
        final String URL = ApiUrls.GET_JAVNE_NABAVKE ;

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("server_response");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);

                                int id =  hit.getInt("ID");

                                String naslov = hit.getString("NASLOV");
                                naslov = helper.mne(naslov);

                                String fajl = hit.getString("FAJL");

                                arrayList.add(new com.androidapp.nbcg.models.JavneNabavke(id, naslov, fajl ));
                            }
                            if(!arrayList.isEmpty()) lottieAnimationView.setVisibility(View.GONE);

                            adapter = new JavneNabavkeAdapter(mView , arrayList);

                            recycleView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        lottieAnimationView.setVisibility(View.GONE);
                        helper.alert(mView.getContext(), noConnectionTitle, noConnectionBody );
                    }
                });
        requestQueue.add(request);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    private void textPopulate(){
        switch (language) {
            case 0:
                noConnectionTitle = "Nema interneta!";
                noConnectionBody = "Za pregled javnih nabavki potrebna Vam je internet konekcija!";
                break;
            case 1:
                noConnectionTitle = "No internet connection!";
                noConnectionBody = "You need internet connection to see public procurement!";
                break;
        }
    }

    public void handleAnimation(){
        ConstraintLayout title = (ConstraintLayout) mView.findViewById(R.id.home_scroll);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(title, View.ALPHA, 0.0f, 1.0f);
        alpha.setDuration(400);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(alpha);
        animatorSet.start();
    }
}
