package com.androidapp.nbcg.helper;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class Helpers extends Fragment {


    public void flip_image(int i, ViewFlipper v_flipper, Fragment hostFragment){
        ImageView view  = new ImageView((Context) hostFragment.getHost());
        view.setBackgroundResource(i);
        v_flipper.addView(view);
        v_flipper.setFlipInterval(4000);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation((Context) getHost(), android.R.anim.slide_in_left);
        v_flipper.setOutAnimation((Context) getHost(), android.R.anim.slide_out_right);
    }


}
