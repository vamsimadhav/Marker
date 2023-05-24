package com.example.marker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Marker> markers;
    private FrameLayout containerView;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Marker List
        markers = new ArrayList<>();

        //Frame Layout
        containerView = findViewById(R.id.frameLayout);

        //Drawing Image
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.dots);

        //Drawing Image Gesture Listener
        imageView.setOnTouchListener((v, event) -> gestureDetector.onTouchEvent(event));

    }

    private void addAnchor(PointF pointF){
        // Set the anchor image drawable to the ImageView
        ImageView anchorImageView = new ImageView(getApplicationContext());
        anchorImageView.setImageResource(R.drawable.anchor);

        // Define the layout parameters for the ImageView
        int anchorSize = 72; // Set the desired size of the anchor image
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(anchorSize, anchorSize);
        layoutParams.leftMargin = (int) pointF.x - anchorSize / 2; // Adjust the position based on the anchor size
        layoutParams.topMargin = (int) pointF.y - anchorSize / 2; // Adjust the position based on the anchor size

        // Add the ImageView to the container view with the defined layout parameters
        containerView.addView(anchorImageView, layoutParams);

        //Anchor Click listener
        anchorImageView.setOnClickListener(v -> {
            Intent intent = new Intent(getBaseContext(), MarkerDetailActivity.class);
            startActivity(intent);
        });
    }

final GestureDetector gestureDetector = new GestureDetector(getBaseContext(), new GestureDetector.SimpleOnGestureListener() {
    @Override
    public boolean onDown(@NonNull MotionEvent e) {
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            // Get the double-tap coordinates
            float x = event.getX();
            float y = event.getY();

            markers.add(new Marker(new PointF(x,y)));

            redrawAnchors(markers);

            }
        return true;
    }
});

    private void redrawAnchors(List<Marker> markers) {
        for(Marker marker: markers){
            addAnchor(marker.getPoint());
        }
    }
}