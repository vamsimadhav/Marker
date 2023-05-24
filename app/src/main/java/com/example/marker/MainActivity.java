package com.example.marker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GestureDetectorCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private List<Marker> markers;

    private FrameLayout containerView;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.dots);
        containerView = findViewById(R.id.linLayout);

        markers = new ArrayList<>();

        imageView.setOnTouchListener((v, event) -> gestureDetector.onTouchEvent(event));

    }

final GestureDetector gestureDetector = new GestureDetector(getBaseContext(), new GestureDetector.SimpleOnGestureListener() {

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            // Get the double-tap coordinates
            float x = event.getX();
            float y = event.getY();

            // Create a new ImageView
            ImageView anchorImageView = new ImageView(getApplicationContext());

            // Set the anchor image drawable to the ImageView
            anchorImageView.setImageResource(R.drawable.anchor);

            // Define the layout parameters for the ImageView
            int anchorSize = 48; // Set the desired size of the anchor image
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(anchorSize, anchorSize);
            layoutParams.leftMargin = (int) x - anchorSize / 2; // Adjust the position based on the anchor size
            layoutParams.topMargin = (int) y - anchorSize / 2; // Adjust the position based on the anchor size

            // Add the ImageView to the container view with the defined layout parameters
            containerView.addView(anchorImageView, layoutParams);

            // Set a click listener on the anchor ImageView
            anchorImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Perform the necessary actions to navigate to other activities
                    // For example, start a new activity
                    Intent intent = new Intent(getBaseContext(), MarkerDetailActivity.class);
                    startActivity(intent);
                }
            });

            return super.onDoubleTap(event);
            }
        return true;
    }
});
}