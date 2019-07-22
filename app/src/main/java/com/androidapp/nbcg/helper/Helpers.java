package com.androidapp.nbcg.helper;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.androidapp.nbcg.MainActivity;
import com.androidapp.nbcg.R;

import org.apache.commons.lang3.StringUtils;

public class Helpers extends Fragment {

    // Open url address
    public void goToUrl (String url, Context context) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        context.startActivity(launchBrowser);
    }

    // Return tekst depending on language
    public String mne(String tekst){
        return StringUtils.substringBetween(tekst, "[0]", "[/0]");
    }
    public String eng(String tekst){
        return StringUtils.substringBetween(tekst, "[1]", "[/1]");
    }
    public String rus(String tekst){
        return StringUtils.substringBetween(tekst, "[2]", "[/2]");
    }
    public String ger(String tekst){
        return StringUtils.substringBetween(tekst, "[3]", "[/3]");
    }
    public String ita(String tekst){
        return StringUtils.substringBetween(tekst, "[4]", "[/4]");
    }
    public String fra(String tekst){
        return StringUtils.substringBetween(tekst, "[5]", "[/5]");
    }

    // Convert text in date format dd.mm.year.
    public  String dateConverter(String date){
        String year = date.substring(0,4);
        String month = date.substring(5,7);
        String day = date.substring(8,10);

        return day + "." +  month + "." + year + ".";
    }

    //Allert box
    public void alert(Context context, String titleText, String bodyText){
        new AlertDialog.Builder(context)
                .setTitle(titleText)
                .setMessage(bodyText)

                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    //Allert box
    public void alertInfo(Context context, String titleText, String bodyText){
        new AlertDialog.Builder(context)
                .setTitle(titleText)
                .setMessage(bodyText)

                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }


    //Open new fragment
    public void openFragment(Fragment fragment, FragmentManager fragmentManager){
        Fragment newFragment = fragment;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }







}
