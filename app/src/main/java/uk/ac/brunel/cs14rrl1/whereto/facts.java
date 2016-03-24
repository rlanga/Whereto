package uk.ac.brunel.cs14rrl1.whereto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
//import android.widget.ImageView;
import android.widget.ViewFlipper;

public class facts extends AppCompatActivity {

//    private String[] facts;
    private ViewFlipper mViewFlipper;
    private GestureDetector mGestureDetector;

    /*int[] resources = {
            R.drawable.fact_1,
            R.drawable.fact_2,
            R.drawable.fact_3,
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts);


        /*facts = new String[] {"St Paul’s Cathedral is one of the largest churches in the world. It is located within the City of London on Ludgate Hill, the City’s highest point.",
                "St Paul’s Cathedral was the tallest building in London from its construction until 1962. The dome is the second largest in the world at 366 feet high and is reached by climbing 259 steps.",
                "The present cathedral was built between 1675 and 1710, although other church buildings have stood on the same spot. Old St Paul’s Cathedral was\n" +
                        "started by the Normans and completed by about 1240."};*/


        CustomGestureDetector customGestureDetector = new CustomGestureDetector();
        mGestureDetector = new GestureDetector(this, customGestureDetector);

            // Get the ViewFlipper
            mViewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);

            // Add all the images to the ViewFlipper
           /* for (int i = 0; i < resources.length; i++) {
                ImageView imageView = new ImageView(this);
                imageView.setImageResource(resources[i]);
                mViewFlipper.addView(imageView);
            }*/

            // Set in/out flipping animations
            mViewFlipper.setInAnimation(this, android.R.anim.fade_in);
            mViewFlipper.setOutAnimation(this, android.R.anim.fade_out);

    }

    class CustomGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            // Swipe left (next)
            if (e1.getX() > e2.getX()) {
                mViewFlipper.showNext();
            }

            // Swipe right (previous)
            if (e1.getX() < e2.getX()) {
                mViewFlipper.showPrevious();
            }

            return super.onFling(e1, e2, velocityX, velocityY);
        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);

        return super.onTouchEvent(event);
    }
}
