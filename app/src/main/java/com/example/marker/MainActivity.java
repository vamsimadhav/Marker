package com.example.marker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private List<PointF> markers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.dots);

        markers = new ArrayList<>();

        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                view.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            float x = event.getX();
                            float y = event.getY();

                            // Add marker
                            markers.add(new PointF(x, y));

                            // Update UI
                            redrawMarkers();

                            return true;
                        }
                        return false;
                    }
                });
                return false;
            }
        });
    }

    private void redrawMarkers() {

        //Anchor Drawable to be drawn on image
        Drawable anchorDrawable = ResourcesCompat.getDrawable(
                                    getResources(),
                                    R.drawable.anchor,
                                    null
                                );


        // Load the drawing image from resources
        Bitmap drawingBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dots);

        // Create a mutable bitmap to draw on
        Bitmap mutableBitmap = drawingBitmap.copy(Bitmap.Config.ARGB_8888, true);

        // Create a canvas to draw on the bitmap
        Canvas canvas = new Canvas(mutableBitmap);

        // Convert the Drawable to a Bitmap
        Bitmap markerBitmap = Bitmap.createBitmap(
                anchorDrawable.getIntrinsicWidth(),
                anchorDrawable.getIntrinsicHeight(),
                Bitmap.Config.ARGB_8888
        );
        Canvas markerCanvas = new Canvas(markerBitmap);
        anchorDrawable.setBounds(0, 0, markerCanvas.getWidth(), markerCanvas.getHeight());
        anchorDrawable.draw(markerCanvas);

        // Draw the markers on the canvas
        for (PointF marker : markers) {
            canvas.drawBitmap(markerBitmap, marker.x, marker.y, null);
        }

        // Set the updated bitmap on the ImageView
        imageView.setImageBitmap(mutableBitmap);
    }
}