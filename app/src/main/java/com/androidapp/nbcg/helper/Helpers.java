package com.androidapp.nbcg.helper;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.androidapp.nbcg.R;

import org.apache.commons.lang3.StringUtils;

public class Helpers extends Fragment {

//
//    public void flip_image(int i, ViewFlipper v_flipper, Fragment hostFragment){
//        ImageView view  = new ImageView((Context) hostFragment.getHost());
//        view.setBackgroundResource(i);
//        v_flipper.addView(view);
//        v_flipper.setFlipInterval(4000);
//        v_flipper.setAutoStart(true);
//
//        v_flipper.setInAnimation((Context) getHost(), android.R.anim.slide_in_left);
//        v_flipper.setOutAnimation((Context) getHost(), android.R.anim.slide_out_right);
//    }
//

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


    //Open new fragment
    public void openFragment(Fragment fragment, FragmentManager fragmentManager){
        Fragment newFragment = fragment;
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }


}
