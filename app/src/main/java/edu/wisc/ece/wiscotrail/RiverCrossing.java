package edu.wisc.ece.wiscotrail;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.lang.Math;
import java.util.Random;
import android.graphics.Matrix;

public class RiverCrossing extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    Matrix matrix = new Matrix();
    double total_rot = 0;
    double artificial_center = 0;
    Random rand;
    ImageView wagon_img;
    long tStart;
    long elapsed_secs = 0;
    boolean lockHeld = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_river_crossing);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        wagon_img = (ImageView)findViewById(R.id.wagon_img);
        rand = new Random();
        wagon_img.setScaleType(ImageView.ScaleType.MATRIX);
        scaleImage(wagon_img, 450);
        tStart = System.currentTimeMillis();

        //TODO this doesn't work
//        PlayGifView pGif = (PlayGifView) findViewById(R.id.waveGif);
//        pGif.setImageResource(R.drawable.waves);


        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_UI);

    }

    public void onSensorChanged(SensorEvent event) {

        if(event.sensor.getType() == Sensor.TYPE_ORIENTATION){
            doOrientationThings(event, rand);

        }


    }

    /*
    function taken from argillander.wordpress.com
    */
    public void scaleImage(ImageView iv, int boundsDP){

        // Get the ImageView and its bitmap
        Drawable drawing = iv.getDrawable();
        Bitmap bitmap = ((BitmapDrawable)drawing).getBitmap();

        // Get current dimensions
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        // Determine how much to scale: the dimension requiring less scaling is
        // closer to the its side. This way the image always stays inside your
        // bounding box AND either x/y axis touches it.
        float xScale = ((float) boundsDP) / width;
        float yScale = ((float) boundsDP) / height;
        float scale = (xScale <= yScale) ? xScale : yScale;

        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);

        // Create a new bitmap and convert it to a format understood by the ImageView
        Bitmap scaledBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        BitmapDrawable result = new BitmapDrawable(scaledBitmap);
        width = scaledBitmap.getWidth();
        height = scaledBitmap.getHeight();

        // Apply the scaled bitmap
        iv.setImageBitmap(scaledBitmap);

        // Now change ImageView's dimensions to match the scaled image
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) iv.getLayoutParams();
        params.width = width;
        params.height = height;
        iv.setLayoutParams(params);

    }

    public void onAccuracyChanged(Sensor sensor, int i){

    }

    public void doOrientationThings(SensorEvent event, Random irand){

        elapsed_secs = System.currentTimeMillis() - tStart;
        //grace period of 3 seconds
        if(elapsed_secs < 1000){
            return;
        }
        //after 30 seconds you cross the river
        if(elapsed_secs > 30000) {
            if(!lockHeld) {
                lockHeld = true;
                crossedSuccessfully();
            }
        }
        else {

            double rand_num = (irand.nextDouble() * 2) - 1; //rand double btwn 0.0 and 1.0
            if (rand_num < 0) {
                rand_num = -1 * (Math.sqrt(Math.abs(rand_num)));
            } else {
                rand_num = Math.sqrt(rand_num);
            }
            double rand_skew = rand_num * 8; //now you have a random number btwn -8 and 8, more heavily weighted towards the edges
            //double rand_skew = (rand_num * 16) - 8; //get it to be a double between -2.000 and 2.000
            artificial_center += rand_skew;
            float x_rot = (-1) * event.values[2];

            float pivotX = (wagon_img.getWidth() / 2);
            float pivotY = (wagon_img.getHeight() / 2);

            if (total_rot + rand_skew + (x_rot / 1.5) > 75) {
                total_rot = 75;
                if (!lockHeld) {
                    lockHeld = true;
                    sunkWagon();
                }
            } else if (total_rot + rand_skew + (x_rot / 1.5) < -75) {
                total_rot = -75;
                if (!lockHeld) {
                    lockHeld = true;
                    sunkWagon();
                }
            } else
                total_rot += rand_skew + (x_rot / 1.5);

            //if not maxed out, rotate more
            if ((total_rot < 75) && (total_rot > -75))
                matrix.preRotate((float) ((x_rot / 1.5) + rand_skew), pivotX, pivotY);

            wagon_img.setImageMatrix(matrix);
        }
        }


    public void crossedSuccessfully(){
        endCrossingAlert("You made it across the river!");
    }

    public void sunkWagon(){
        //Todo: leave activity and calculate lost items
        endCrossingAlert("Your wagon has sunk");
    }

    public void endCrossingAlert(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        finish();
                    }
                });
        final AlertDialog dialog = builder.create();
        dialog.show();

    }



}
