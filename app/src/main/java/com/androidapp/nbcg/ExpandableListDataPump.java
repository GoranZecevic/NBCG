package com.androidapp.nbcg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> izvestaji = new ArrayList<String>();
        izvestaji.add("Izvestaj 1");
        izvestaji.add("Izvestaj 2");
        izvestaji.add("Izvestaj 3");
        izvestaji.add("Izvestaj 4");

        List<String> programi = new ArrayList<String>();
        programi.add("Program 1");
        programi.add("Program 2");
        programi.add("Program 3");
        programi.add("Program 4");

        List<String> nalozi = new ArrayList<String>();
        nalozi.add("Putni nalozi 1");
        nalozi.add("Putni nalozi 2");
        nalozi.add("Putni nalozi 3");
        nalozi.add("Putni nalozi 4");

        List<String> kartice = new ArrayList<String>();
        kartice.add("Analiticke kartice 1");
        kartice.add("Analiticke kartice 2");
        kartice.add("Analiticke kartice 3");
        kartice.add("Analiticke kartice 4");

        expandableListDetail.put("Izvestaji o radu", izvestaji);
        expandableListDetail.put("Programi rada", programi);
        expandableListDetail.put("Putni nalozi", nalozi);
        expandableListDetail.put("Analiticke kartice", kartice);
        return expandableListDetail;
    }
}