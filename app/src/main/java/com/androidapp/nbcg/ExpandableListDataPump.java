package com.androidapp.nbcg;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.androidapp.nbcg.api_urls.ApiUrls;
import com.androidapp.nbcg.helper.Helpers;
import com.androidapp.nbcg.models.Izvestaji;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ExpandableListDataPump  {

//    static int language = MainActivity.lang;

    private static Context context;

    private static Helpers helper = new Helpers();

    private static String izvestaji_o_radu;
    private static String putni_nalozi;
    private static String programi_rada;
    private static String analiticke_kartice;

    private static ArrayList<Izvestaji> programiRadaLista = new ArrayList<>();
    private static ArrayList<Izvestaji> izvestajiRadaList = new ArrayList<>();
    private static ArrayList<Izvestaji> putniNaloziLista = new ArrayList<>();
    private static ArrayList<Izvestaji> analitickeKarticeLista = new ArrayList<>();

    private RequestQueue requestQueue;

    public ExpandableListDataPump(Context context){
        this.context=context;
        requestQueue = Volley.newRequestQueue(this.context);

        getIzvestajiRada();
        getProgramiRada();
        getPutniNalozi();
        getAnalitickeKartice();
    }

    public static HashMap<String, List<Izvestaji>> getData() {
        switch (MainActivity.lang) {
            case 0:
                izvestaji_o_radu = helper.mne(context.getResources().getString(R.string.str_izvestaji_body));
                putni_nalozi = helper.mne(context.getResources().getString(R.string.str_nalozi_body));
                programi_rada = helper.mne(context.getResources().getString(R.string.str_programi_body));
                analiticke_kartice = helper.mne(context.getResources().getString(R.string.str_kartice_body));
                break;
            case 1:
                izvestaji_o_radu = helper.eng(context.getResources().getString(R.string.str_izvestaji_body));
                putni_nalozi = helper.eng(context.getResources().getString(R.string.str_nalozi_body));
                programi_rada = helper.eng(context.getResources().getString(R.string.str_programi_body));
                analiticke_kartice = helper.eng(context.getResources().getString(R.string.str_kartice_body));
                break;
        }


        HashMap<String, List<Izvestaji>> expandableListDetail = new HashMap<String, List<Izvestaji>>();

        List<String> izvestaji= new ArrayList<>();
        List<String> programi = new ArrayList<>();
        List<String> nalozi = new ArrayList<>();
        List<String> kartice = new ArrayList<>();


        for(int i=0; i< izvestajiRadaList.size(); i++){
            izvestaji.add(izvestajiRadaList.get(i).getNaslov());
        }

        for(int i=0; i< programiRadaLista.size(); i++){
            programi.add(programiRadaLista.get(i).getNaslov());
        }

        for(int i=0; i< putniNaloziLista.size(); i++){
            nalozi.add(putniNaloziLista.get(i).getNaslov());
        }

        for(int i=0; i< analitickeKarticeLista.size(); i++){
            kartice.add(analitickeKarticeLista.get(i).getNaslov());
        }

        expandableListDetail.put(izvestaji_o_radu, izvestajiRadaList);
        expandableListDetail.put(putni_nalozi, putniNaloziLista);
        expandableListDetail.put(programi_rada, programiRadaLista);
        expandableListDetail.put(analiticke_kartice, analitickeKarticeLista);

        return expandableListDetail;
    }

    public void getIzvestajiRada() {
        final String URL = ApiUrls.GET_IZVESTAJI_RADA ;

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("server_response");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);

                                String naslov = hit.getString("NASLOV");
                                naslov = StringUtils.substringBetween(naslov, "[0]", "[/0]");

                                String fajl = hit.getString("FAJL");

                                izvestajiRadaList.add(new Izvestaji(naslov, fajl));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        requestQueue.add(request);
    }

    public void getProgramiRada() {
        final String URL = ApiUrls.GET_PROGRAMI_RADA ;

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("server_response");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);

                                String naslov = hit.getString("NASLOV");
                                naslov = StringUtils.substringBetween(naslov, "[0]", "[/0]");

                                String fajl = hit.getString("FAJL");

                                programiRadaLista.add(new Izvestaji(naslov, fajl));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        requestQueue.add(request);
    }

    public void getPutniNalozi() {
        final String URL = ApiUrls.GET_PUTNI_NALOZI ;

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("server_response");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);

                                String naslov = hit.getString("NASLOV");
                                naslov = StringUtils.substringBetween(naslov, "[0]", "[/0]");

                                String fajl = hit.getString("FAJL");

                                putniNaloziLista.add(new Izvestaji(naslov, fajl));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        requestQueue.add(request);
    }

    public void getAnalitickeKartice() {
        final String URL = ApiUrls.GET_ANALITICKE_KARTICE ;

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("server_response");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);

                                String naslov = hit.getString("NASLOV");
                                naslov = StringUtils.substringBetween(naslov, "[0]", "[/0]");

                                String fajl = hit.getString("FAJL");

                                analitickeKarticeLista.add(new Izvestaji(naslov, fajl));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        requestQueue.add(request);
    }


}