package com.example.marker;

import android.content.Context;
import android.content.Intent;
import android.graphics.PointF;
import android.view.View;

public class Marker implements View.OnClickListener{

  private Context mContext;
  private PointF mPointF;

    public Marker(Context context, PointF pointF){
        this.mContext = context;
        mPointF = pointF;
    }

    public float x(){
        return mPointF.x;
    }
    public float y(){
        return mPointF.y;
    }

    @Override
    public void onClick(View view) {
     Intent i = new Intent(mContext, MarkerDetailActivity.class);
     mContext.startActivity(i);
    }

}
