package com.androidapp.nbcg.fragments.O_Nama;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.androidapp.nbcg.MainActivity;
import com.androidapp.nbcg.R;
import com.androidapp.nbcg.adapters.OrganizacijaAdapter;
import com.androidapp.nbcg.api_urls.ApiUrls;
import com.androidapp.nbcg.helper.Helpers;
import com.androidapp.nbcg.models.JavneNabavke;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Organizacija extends Fragment {

    private RequestQueue requestQueue;
    private String noConnectionTitle;
    private String noConnectionBody;
    private ArrayList<JavneNabavke> arrayList;

    private View mView;
    private Helpers helper = new Helpers();
    private int language = MainActivity.lang;

    private OrganizacijaAdapter adapter;
    private RecyclerView recycleView;

    private OnFragmentInteractionListener mListener;

    LottieAnimationView lottieAnimationView;

    public Organizacija() {
    }

    public static Organizacija newInstance(String param1, String param2) {
        Organizacija fragment = new Organizacija();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_organizacija, container, false);
        handleAnimation();

        lottieAnimationView = (LottieAnimationView) mView.findViewById(R.id.animation_view);
        lottieAnimationView.setImageAssetsFolder("images/");
        lottieAnimationView.setAnimation("data.json");
        lottieAnimationView.loop(true);
        lottieAnimationView.playAnimation();

        textPopulate();

        arrayList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this.getContext());
        recycleView = (RecyclerView)mView.findViewById(R.id.recycler_view_organizacija);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycleView.setLayoutManager(layoutManager);
        PARSEDATA();

        if(language == 1){
            imageButtonHandlerWeb(ApiUrls.ORGANIZACIJA_FB_ENG, R.id.vb_btn_fb);
            imageButtonHandlerWeb(ApiUrls.ORGANIZACIJA_TW_ENG, R.id.vb_btn_tw);
            imageButtonHandlerWeb(ApiUrls.ORGANIZACIJA_LN_ENG, R.id.vb_btn_ln);
        }

        if(language == 0) {
            imageButtonHandlerWeb(ApiUrls.ORGANIZACIJA_FB_MNE, R.id.vb_btn_fb);
            imageButtonHandlerWeb(ApiUrls.ORGANIZACIJA_TW_MNE, R.id.vb_btn_tw);
            imageButtonHandlerWeb(ApiUrls.ORGANIZACIJA_LN_MNE, R.id.vb_btn_ln);
        }

        return mView;
    }

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
        void onFragmentInteraction(Uri uri);
    }

    public void PARSEDATA() {
        final String URL = ApiUrls.GET_ORGANIZACIJA ;

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

                            adapter = new OrganizacijaAdapter(mView , arrayList);

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

    public void textPopulate() {

        switch (language) {
            case 0:
                noConnectionTitle = "Nema interneta!";
                noConnectionBody = "Za izlistavanje sadržaja ove strane potrebna Vam je internet konekcija!";
                break;
            case 1:
                noConnectionTitle = "No internet connection!";
                noConnectionBody = "To access content of this page You need internet connection!";
                break;
        }

        Button header = (Button) mView.findViewById(R.id.o_nama_title);
        String headerStr = "";
        switch (language) {
            case 0:
                headerStr = helper.mne(getResources().getString(R.string.str_onama_title));
                break;
            case 1:
                headerStr = helper.eng(getResources().getString(R.string.str_onama_title));
                break;
        }
        header.setText(headerStr);


        TextView title = (TextView) mView.findViewById(R.id.str_za_izdavace_title);
        String titleStr = "";
        switch (language) {
            case 0:
                titleStr = helper.mne(getResources().getString(R.string.str_organ_title));
                break;
            case 1:
                titleStr = helper.eng(getResources().getString(R.string.str_organ_title));
                break;
        }
        title.setText(titleStr);


        TextView podjeli = (TextView)mView.findViewById(R.id.podjeli);
        String podjeliStr= "";
        switch (language){
            case 0: podjeliStr = helper.mne(getResources().getString(R.string.str_podelite)); break;
            case 1: podjeliStr = helper.eng(getResources().getString(R.string.str_podelite)); break;
        }
        podjeli.setText(podjeliStr);
    }

    // region helpers
    private void newFragment(Fragment fragment){
        Fragment newFragment = fragment;

        FragmentManager manager = getFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, newFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void buttonHandler(final Fragment fragment, final int buttonId){
        Button button = (Button) mView.findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener()
        {
            Fragment proxyFragment = fragment;
            int id = buttonId;
            @Override
            public void onClick(View v)
            {
                newFragment(proxyFragment);
            }
        });
    }

    private void buttonHandlerWeb(final String url, final int buttonId){
        Button button = (Button) mView.findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener()
        {
            int id = buttonId;
            Helpers help = new Helpers();
            Context ctx = mView.getContext();
            @Override
            public void onClick(View v)
            {
                help.goToUrl(url, ctx);
            }
        });
    }

    private void imageButtonHandlerWeb(final String url, final int buttonId){
        ImageButton button = (ImageButton) mView.findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener()
        {
            int id = buttonId;
            Helpers help = new Helpers();
            Context ctx = mView.getContext();
            @Override
            public void onClick(View v)
            {
                help.goToUrl(url, ctx);
            }
        });
    }

    public void handleAnimation(){
        ScrollView title = (ScrollView) mView.findViewById(R.id.home_scroll);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(title, View.ALPHA, 0.0f, 1.0f);
        alpha.setDuration(400);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(alpha);
        animatorSet.start();
    }
}
