package edu.wisc.ece.wiscotrail;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import static java.lang.Math.abs;

public class Hunt extends AppCompatActivity implements SensorEventListener {
    private Camera mCamera = null;
    private CameraView mCameraView = null;

    private float epsilon = .2f; //changes sensitivity to "jostling"
    private double hAngle, vAngle; //these are the camera view angles

    private int height, width = 0;
    private int numBullets = 5;
    //containers for bullets and bulletHole images
    private ImageView[] bullets;
    private ImageView[] bulletHoles;

    // Create a constant to convert nanoseconds to seconds.
    private static final float NS2S = 1.0f / 1000000000.0f;
    private float timestamp = 0;

    //TODO random x position, random y position, offset calculations down below by these two
    private float xRand = 300;
    private float yRand = 300;

    private SensorManager sensorManager; //sensor attempt

    RelativeLayout layout;
    ImageView bulletHole;  //used to draw bullet holes
    long lastShootTime = 0; //when the player last shot, used for timing

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hunt);

        try {
            mCamera = Camera.open();//you can use open(int) to use different cameras
        } catch (Exception e) {
            Log.d("ERROR", "Failed to get camera: " + e.getMessage());
        }

        if (mCamera != null) {
            mCameraView = new CameraView(this, mCamera);//create a SurfaceView to show camera data
            FrameLayout camera_view = (FrameLayout) findViewById(R.id.camera_view);
            camera_view.addView(mCameraView);//add the SurfaceView to the layout
        }

        //find camera lens angles if needed
        hAngle = mCamera.getParameters().getHorizontalViewAngle();
        vAngle = mCamera.getParameters().getVerticalViewAngle();

        //set up sensors
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
                SensorManager.SENSOR_DELAY_GAME);

        layout = (RelativeLayout) findViewById(R.id.huntingLayout);

        //set "cow" randomly section
        final ImageView cow = (ImageView) findViewById(R.id.cow);
//////////////////////////////////////////////////////////////////////////////////////////////////
//       cow.setImageResource(R.drawable.deer);
//       cow.getLayoutParams().width = 200;
//////////////////////////////////////////////////////////////////////////////////////////////////
//        cow.setX(xRand);
//        cow.setY(yRand);

        //if the cow is touched add a bullet hole at the touch location
        bulletHoles = new ImageView[numBullets];
        final Bitmap bitmap = ((BitmapDrawable)cow.getBackground()).getBitmap();
        cow.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {

                int pixel = 0;
                int pixX = (int)event.getX();
                int pixY = (int)event.getY();
                //this part is sort of a stretch...try to find pixel on bitmap translated from real image
                //update: it works
                if(bitmap != null|| pixX <0 || pixY<0 || pixX > cow.getWidth() || pixY > cow.getHeight()) {
                    int projectedX = (int)((double)pixX * ((double)bitmap.getWidth()/(double)cow.getWidth()));
                    int projectedY = (int)((double)pixY * ((double)bitmap.getHeight()/(double)cow.getHeight()));
                    //crashes without checks for bounds
                    if(projectedX < bitmap.getWidth() && projectedY < bitmap.getHeight() && projectedX > 0 && projectedY > 0) {
                        pixel = bitmap.getPixel(projectedX, projectedY);
                    }
                }

                //timing so that you don't accidentally shoot too fast
                if ((event.getEventTime() - lastShootTime > 300) && Color.alpha(pixel) != 0) {
                    if (numBullets > 0) {
                        lastShootTime = event.getEventTime();
                        bulletHole = new ImageView(Hunt.this);
                        bulletHole.setImageResource(R.drawable.bullethole);

                        layout.addView(bulletHole);

                        bulletHole.getLayoutParams().height = 50;
                        bulletHole.getLayoutParams().width = 50;

                        //NOTE: Use getRawX/Y to get position relative to screen
                        bulletHole.setX(event.getRawX() - 25);
                        bulletHole.setY(event.getRawY() - 75);

                        //////////////////////////////////////////////////////////////////////DEBUG
                        //TextView t =(TextView)findViewById(R.id.bulletText);
                        //t.setText(String.valueOf(event.getRawX() + "/" ));
                        //////////////////////////////////////////////////////////////////////

                        //expend one bullet and add bullet hole to array
                        numBullets--;
                        bullets[numBullets].setVisibility(View.INVISIBLE);
                        //TODO store bullet holes with animal
                        bulletHoles[numBullets] = bulletHole;
                        return true;
                    }
                }
                return false;
            }
        });

        //get height and width in pixels
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        height = metrics.heightPixels;
        width = metrics.widthPixels;

        //make the bullet table to show the number of bullets remaining
        bullets = new ImageView[numBullets];
        for (int i = 0; i < numBullets; i++) {
            ImageView bullet = new ImageView(this);
            bullet.setImageResource(R.drawable.bullet);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_TOP, R.id.bulletText);
            bullets[i] = bullet;
            params.height = 55;
            bullet.setLayoutParams(params);
            layout.addView(bullet);
            //space the bullets apart
            bullet.setX(bullet.getX() + i * 65);
            bullet.setY(bullet.getY()+18);
        }
    }

    @SuppressWarnings("ResourceType") //annoying error that doesn't affect anything
    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            float dT;
            if (timestamp != 0) {
                dT = (event.timestamp - timestamp) * NS2S;

                float x = event.values[1];
                float y = event.values[0];

                width = mCameraView.getWidth();
                height = mCameraView.getHeight();
                if (abs(x) > epsilon || abs(y) > epsilon) {

//TODO make work for every phone dimensions
                    float deltaX = (float) ((x * dT * 40) / hAngle) * width;
                    float deltaY = (float) ((y * dT * 40) / vAngle) * height;

                    ImageView cow = (ImageView) findViewById(R.id.cow);
                    if (cow.getX() < -3056) cow.setX(1069);
                    else if (cow.getX() > 2975) cow.setX(-676);
                    cow.setX(cow.getX() + deltaX);
                    cow.setY(cow.getY() + deltaY);

                    //////////////////////////////////////////////////////////////////////DEBUG
                    //TextView t =(TextView)findViewById(R.id.bulletText);
                    //t.setText(String.valueOf(cow.getX()));
                    //////////////////////////////////////////////////////////////////////

                    if (bullets.length != 0) {
                        for (ImageView b : bulletHoles) {
                            if (b != null) {
                                if (b.getX() < -3056) b.setX(1069);
                                else if (b.getX() > 2975) b.setX(-676);
                                b.setX(b.getX() + deltaX);
                                b.setY(b.getY() + deltaY);
                            }
                        }
                    }
                }
            }
            timestamp = event.timestamp;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //unused
    }

}

