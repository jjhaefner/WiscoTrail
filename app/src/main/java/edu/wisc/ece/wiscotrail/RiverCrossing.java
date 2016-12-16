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
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.Math;
import java.util.Random;
import android.graphics.Matrix;

public class RiverCrossing extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mSensor;
    Matrix matrix = new Matrix();
    // Create a constant to convert nanoseconds to seconds.
    private static final float NS2S = 1.0f / 1000000000.0f;
    private final float[] deltaRotationVector = new float[4];
    private float timestamp;
    private static final double EPSILON = 0.05;
    private float[] currentRotationMatrix = new float[9];
    private float[] gyroscopeOrientation = new float[3];
    double total_rot = 0;
    double artificial_center = 0;
    TextView oriView1;
    Random rand;
    ImageView wagon_img;
    long tStart;
    long elapsed_secs = 0;
    boolean lockHeld = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_river_crossing);
        wagon_img = (ImageView)findViewById(R.id.wagon_img);
        rand = new Random();
        wagon_img.setScaleType(ImageView.ScaleType.MATRIX);
        scaleImage(wagon_img, 150);
        tStart = System.currentTimeMillis();

        oriView1 = (TextView)findViewById(R.id.orientationVal1);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
                SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_NORMAL);

    }

    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_GYROSCOPE){
            doGyroscopeThings(event);
        }
        else if(event.sensor.getType() == Sensor.TYPE_ORIENTATION){
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
        if(elapsed_secs > 30000) {
            if(!lockHeld) {
                lockHeld = true;
                crossedSuccessfully();
            }
        }
        double rand_num = Math.sqrt(irand.nextDouble()); //rand double btwn 0.0 and 1.0
        double rand_skew = (rand_num * 16) - 8; //get it to be a double between -2.000 and 2.000
        artificial_center += rand_skew;
        float x_rot = (-1) * event.values[2];

        float pivotX = (wagon_img.getWidth()  / 2);
        float pivotY = (wagon_img.getHeight() / 2);

        if(total_rot + rand_skew + (x_rot/1.5) > 75) {
            total_rot = 75;
            if(!lockHeld) {
                lockHeld = true;
                sunkWagon();
            }
        }
        else if(total_rot + rand_skew + (x_rot/1.5) < -75) {
            total_rot = -75;
            if(!lockHeld) {
                lockHeld = true;
                sunkWagon();
            }
        }
        else
            total_rot += rand_skew + (x_rot/1.5);
        oriView1.setText(Float.toString(elapsed_secs));
        //if not maxed out, rotate more
        if((total_rot < 75) && (total_rot > -75))
            matrix.preRotate((float)((x_rot/1.5)+ rand_skew), pivotX, pivotY);


        wagon_img.setImageMatrix(matrix);
    }

    public void crossedSuccessfully(){
        endCrossingAlert("You made it across the river!");
    }

    public void sunkWagon(){
        //Todo: leave activity and calculate lost items
        endCrossingAlert("Your wagon has sunk");
    }


    public void doGyroscopeThings(SensorEvent event){
        /*
        // This timestep's delta rotation to be multiplied by the current rotation
        // after computing it from the gyro sample data.
        float axisX = 0;
        float axisY = 0;
        float axisZ = 0;
        float omegaMagnitude = 0;
        if (timestamp != 0) {
            final float dT = (event.timestamp - timestamp) * NS2S;
            // Axis of the rotation sample, not normalized yet.
            axisX = event.values[0];
            axisY = event.values[1];
            axisZ = event.values[2];

            // Calculate the angular speed of the sample
            omegaMagnitude = (float)Math.sqrt(axisX*axisX + axisY*axisY + axisZ*axisZ);

            // Normalize the rotation vector if it's big enough to get the axis
            // (that is, EPSILON should represent your maximum allowable margin of error)

            if (omegaMagnitude > EPSILON) {
                axisX /= omegaMagnitude;
                axisY /= omegaMagnitude;
                axisZ /= omegaMagnitude;
            }

            // Integrate around this axis with the angular speed by the timestep
            // in order to get a delta rotation from this sample over the timestep
            // We will convert this axis-angle representation of the delta rotation
            // into a quaternion before turning it into the rotation matrix.
            float thetaOverTwo = omegaMagnitude * dT / 2.0f;
            float sinThetaOverTwo = (float)Math.sin(thetaOverTwo);
            float cosThetaOverTwo = (float)Math.cos(thetaOverTwo);
            deltaRotationVector[0] = sinThetaOverTwo * axisX;
            deltaRotationVector[1] = sinThetaOverTwo * axisY;
            deltaRotationVector[2] = sinThetaOverTwo * axisZ;
            deltaRotationVector[3] = cosThetaOverTwo;
        }
        timestamp = event.timestamp;
        float[] deltaRotationMatrix = new float[9];
        SensorManager.getRotationMatrixFromVector(deltaRotationMatrix, deltaRotationVector);
        // User code should concatenate the delta rotation we computed with the current rotation
        // in order to get the updated rotation.
        currentRotationMatrix = matrixMultiplication(
                currentRotationMatrix,
                deltaRotationMatrix);

        SensorManager.getOrientation(currentRotationMatrix,
                gyroscopeOrientation);
        //if(Math.abs(deltaRotationVector[1]) > 0.05 && Math.abs(deltaRotationVector[1]) < 0.2)
        gyroView1.setText(Float.toString(deltaRotationMatrix[0]));
        gyroView2.setText(Float.toString(deltaRotationMatrix[1]));
        gyroView3.setText(Float.toString(deltaRotationMatrix[2]));
*/
    }

    /*private float[] matrixMultiplication(float[] a, float[] b)
    {
        float[] result = new float[9];

        result[0] = a[0] * b[0] + a[1] * b[3] + a[2] * b[6];
        result[1] = a[0] * b[1] + a[1] * b[4] + a[2] * b[7];
        result[2] = a[0] * b[2] + a[1] * b[5] + a[2] * b[8];

        result[3] = a[3] * b[0] + a[4] * b[3] + a[5] * b[6];
        result[4] = a[3] * b[1] + a[4] * b[4] + a[5] * b[7];
        result[5] = a[3] * b[2] + a[4] * b[5] + a[5] * b[8];

        result[6] = a[6] * b[0] + a[7] * b[3] + a[8] * b[6];
        result[7] = a[6] * b[1] + a[7] * b[4] + a[8] * b[7];
        result[8] = a[6] * b[2] + a[7] * b[5] + a[8] * b[8];

        return result;
    }*/

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
