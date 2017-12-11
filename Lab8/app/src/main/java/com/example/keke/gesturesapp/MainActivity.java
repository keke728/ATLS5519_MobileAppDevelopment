package com.example.keke.gesturesapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private GestureDetector mGestureDetector;
    private ImageView sunflower;
    private ScaleGestureDetector mScaleDetector;


    private int mActivePointerId = MotionEvent.INVALID_POINTER_ID;
    private float lastTouchX;
    private float lastTouchY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGestureDetector = new GestureDetector(this, new CustomGestureDetector());

        sunflower = (ImageView)findViewById(R.id.sunflower);

        mScaleDetector = new ScaleGestureDetector(this, new CustomScaleListener());

        final View.OnTouchListener onTouchListener = new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                mGestureDetector.onTouchEvent(event);
                mScaleDetector.onTouchEvent(event);

                final int action = event.getActionMasked();

                Log.d("Gesture", "onTouch" + action);

                switch (action){
                    case MotionEvent.ACTION_DOWN: {
                        final int pointerIndex = event.getActionIndex();
                        final float x = event.getRawX();
                        final float y = event.getRawY();

                        lastTouchX = x;
                        lastTouchY = y;

                        mActivePointerId = event.getPointerId(pointerIndex);
                        break;
                    }

                    case MotionEvent.ACTION_MOVE: {
                        final float x = event.getRawX();
                        final float y = event.getRawY();
                        final float dx = x - lastTouchX;
                        final float dy = y - lastTouchY;

                        v.setX(x - 193);
                        v.setY(y - 400);

                        v.invalidate();

                        lastTouchY = x;
                        lastTouchY = y;

                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        mActivePointerId = MotionEvent.INVALID_POINTER_ID;
                        break;
                    }
                    case MotionEvent.ACTION_CANCEL: {
                        mActivePointerId = MotionEvent.INVALID_POINTER_ID;
                        break;
                    }
                    case MotionEvent.ACTION_POINTER_UP: {
                        final int pointerIndex = event.getActionIndex();
                        final int pointerId = event.getPointerId(pointerIndex);

                        if(pointerId == mActivePointerId) {
                            final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
                            lastTouchX = event.getX(newPointerIndex);
                            lastTouchY = event.getY(newPointerIndex);
                            mActivePointerId = event.getPointerId(newPointerIndex);
                        }
                        break;
                        }
                    }
                    return true;
                }

            };
        sunflower.setOnTouchListener(onTouchListener);
    }

    class CustomGestureDetector extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onDown(MotionEvent e) {
         Log.d("Gesture", "on down");
         return true;
    }

    @Override
        public boolean onDoubleTapEvent(MotionEvent e){
        Log.d("Gesture", "on double tap event");
        Toast toast = Toast.makeText(getApplicationContext(), "I'm a cute flower", Toast.LENGTH_SHORT);
        toast.show();
        return false;
        }
    }

    class CustomScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{
        private float scale = 1f;

        @Override
        public boolean onScale(ScaleGestureDetector detector){
            scale = scale * detector.getScaleFactor();
            sunflower.setScaleX(scale);
            sunflower.setScaleY(scale);
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector){
            Log.d("Gesture", "on scale end");
            super.onScaleEnd(detector);
        }
    }
}
