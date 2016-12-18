package edu.wisc.ece.wiscotrail;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Random;

import static java.lang.Math.abs;

public class Hunt extends AppCompatActivity implements SensorEventListener {
    private Camera mCamera = null;
    private CameraView mCameraView = null;
    final int MY_PERMISSIONS_REQUEST_USE_CAMERA=101; //camera request constant

    private float epsilon = .2f; //changes sensitivity to "jostling"
    private double hAngle, vAngle; //these are the camera view angles

    private int height, width = 0;
    private int numBullets = 0;
    //containers for bullets and bulletHole images
    private ImageView[] bullets;
    private ImageView[] bulletHoles;

    // Create a constant to convert nanoseconds to seconds.
    private static final float NS2S = 1.0f / 1000000000.0f;
    private float timestamp = 0;

    int meatGained = 0;

    //this is the cow on the screen
    ImageView cow;

    private SensorManager sensorManager; //sensor attempt
    //gunshot sound effect
    MediaPlayer mp;

    Random rand = new Random();

    RelativeLayout layout;
    ImageView bulletHole;  //used to draw bullet holes
    long lastShootTime = 0; //when the player last shot, used for timing
    long lastHitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

       if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED){

           ActivityCompat.requestPermissions(this,
                   new String[]{Manifest.permission.CAMERA},
                   MY_PERMISSIONS_REQUEST_USE_CAMERA);
           Toast toast = Toast.makeText(Hunt.this, "Allow camera permissions to hunt", Toast.LENGTH_LONG);
           toast.show();
           finish();
       }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hunt);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //initialize number of bullets
        if (UserVars.ammunition < 10){
            numBullets = UserVars.ammunition;
        }
        else {
            numBullets = 10;
        }

        try {
            mCamera = Camera.open();//you can use open(int) to use different cameras
        } catch (Exception e) {
            Log.d("ERROR", "Failed to get camera: " + e.getMessage());
        }

        if (mCamera != null) {
            mCameraView = new CameraView(this, mCamera);//create a SurfaceView to show camera data
            FrameLayout camera_view = (FrameLayout) findViewById(R.id.camera_view);
            camera_view.addView(mCameraView);//add the SurfaceView to the layout
           camera_view.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if(numBullets > 0) {
                       if(System.currentTimeMillis() - lastShootTime > 300) {
                           lastShootTime = System.currentTimeMillis();
                           shootGun();
                       }
                   }
               }
           });
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
        cow = (ImageView) findViewById(R.id.cow);
        cow.setX(3000 - rand.nextInt(6000));
        cow.setY(300 - rand.nextInt(600));
        cow.setScaleType(ImageView.ScaleType.MATRIX);
        scaleImage(cow, (600 - rand.nextInt(500)));

        //queue up a gunshot
        mp = MediaPlayer.create(this, R.raw.gunshot);

        //if the cow is touched add a bullet hole at the touch location
        bulletHoles = new ImageView[numBullets];
        Drawable drawing = cow.getDrawable();
        final Bitmap bitmap = ((BitmapDrawable)drawing).getBitmap();

        cow.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                //if there are any bullets to shoot
                if (numBullets > 0) {

                    int pixel = 0;
                    int pixX = (int) event.getX();
                    int pixY = (int) event.getY();
                    //this part is sort of a stretch...try to find pixel on bitmap translated from real image
                    //update: it works
                    if (bitmap != null || pixX < 0 || pixY < 0 || pixX > cow.getWidth() || pixY > cow.getHeight()) {
                        int scaleX = (int) ((double) pixX * ((double) bitmap.getWidth() / (double) cow.getWidth()));
                        int scaleY = (int) ((double) pixY * ((double) bitmap.getHeight() / (double) cow.getHeight()));
                        //crashes without checks for bounds
                        if (scaleX < bitmap.getWidth() && scaleY < bitmap.getHeight() && scaleX > 0 && scaleY > 0) {
                            pixel = bitmap.getPixel(scaleX, scaleY);
                        }
                    }

                    //shootGun(); //simulate gun shooting

                    //timing so that you don't accidentally shoot too fast
                    //check that the alpha of the pixel isn't transparent, either
                    if ((event.getEventTime() - lastHitTime > 300) && Color.alpha(pixel) != 0) {
                        lastHitTime = event.getEventTime();
                        bulletHole = new ImageView(Hunt.this);
                        bulletHole.setImageResource(R.drawable.bullethole);

                        shootGun();

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

                        bulletHoles[numBullets] = bulletHole;

                        checkKill();

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
            bullet.setY(bullet.getY() + 18);
        }

        //exit button
        Button exitButton = (Button) findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) { //release sound player
                mp.release();
                //reward meat
                UserVars.food_lbs += meatGained;
                Toast toast = Toast.makeText(Hunt.this, "You were able to take back " + meatGained + " lbs of meat", Toast.LENGTH_LONG);
                toast.show();
                finish();
            }

        });

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
                    float deltaY = (float) ((y * dT * 20) / vAngle) * height;

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

    //let us scale an image down
    public void scaleImage(ImageView iv, int boundsDP){
        // Get the ImageView and its bitmap
        Drawable drawing = iv.getDrawable();
        Bitmap bitmap = ((BitmapDrawable)drawing).getBitmap();
        // Get current dimensions
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        // Determine how much to scale: the dimension requiring less scaling is
        // closer to the its sie. This way the image always stays inside your
        // bounding box AND either x/y axis touches it.
        float xScale = ((float) boundsDP) / width;
        float yScale = ((float) boundsDP) / height;
        float scale = (xScale <= yScale) ? xScale : yScale;

        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);

        // Create a new bitmap and convert it to a format understood by the ImageView
        Bitmap scaledBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
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

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //unused
    }

    public void shootGun(){
        //play gunshot for successful shot (override current gunshot)
        if(mp.isPlaying()){
            mp.stop();
            mp.release();
        }
        mp = MediaPlayer.create(Hunt.this, R.raw.gunshot);
        mp.start();

        //expend one bullet and add bullet hole to array
        numBullets--;
        UserVars.ammunition--;
        bullets[numBullets].setVisibility(View.INVISIBLE);
    }

    public void checkKill(){

        //check if cow is dead and add meat
        int count = 0;
        for(ImageView i: bulletHoles){
            if(i != null){
                count++;
            }
        }
        if (count >=3){
            cow.setVisibility(View.GONE);

            for(ImageView i: bulletHoles){
                if(i != null){
                    i.setVisibility(View.GONE);
                }
            }
            bulletHoles = new ImageView[numBullets];
           meatGained += 40 + rand.nextInt(60);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

    }

}

