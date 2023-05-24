package com.example.marker;

import android.content.Context;
import android.content.Intent;
import android.graphics.PointF;
import android.view.View;

public class Marker {
  private PointF mPointF;

    public Marker(PointF pointF){
        mPointF = pointF;
    }

    public PointF getPoint(){
        return mPointF;
    }

}
