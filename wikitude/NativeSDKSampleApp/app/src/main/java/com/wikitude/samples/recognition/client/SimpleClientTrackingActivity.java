package com.wikitude.samples.recognition.client;

import android.app.Activity;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wikitude.WikitudeSDK;
import com.wikitude.WikitudeSDKStartupConfiguration;
import com.wikitude.common.camera.CameraSettings;
import com.wikitude.common.rendering.RenderExtension;
import com.wikitude.common.tracking.RecognizedTarget;
import com.wikitude.nativesdksampleapp.R;
import com.wikitude.rendering.ExternalRendering;
import com.wikitude.samples.WikitudeSDKConstants;
import com.wikitude.samples.rendering.external.CustomSurfaceView;
import com.wikitude.samples.rendering.external.Driver;
import com.wikitude.samples.rendering.external.GLRenderer;
import com.wikitude.tracker.ClientTracker;
import com.wikitude.tracker.ClientTrackerEventListener;
import com.wikitude.tracker.Tracker;

public class SimpleClientTrackingActivity extends Activity implements ClientTrackerEventListener, ExternalRendering {

    private static final String TAG = "SimpleClientTracking";

    private WikitudeSDK _wikitudeSDK;
    private CustomSurfaceView _view;
    private Driver _driver;
    private GLSurfaceView view;
    private GLRenderer _glRenderer;
    private  EditText editText;
    private RenderExtension renderExtension;
    private TextView campusLocationName, campusMessageBackGroup, campusOpenTime, campusMoreDetail;
    private String targetDetails;
    private Button buttonMovie;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _wikitudeSDK = new WikitudeSDK(this);
        WikitudeSDKStartupConfiguration startupConfiguration = new WikitudeSDKStartupConfiguration(WikitudeSDKConstants.WIKITUDE_SDK_KEY, CameraSettings.CameraPosition.BACK, CameraSettings.CameraFocusMode.CONTINUOUS);
        _wikitudeSDK.onCreate(getApplicationContext(), startupConfiguration);

        ClientTracker tracker = _wikitudeSDK.getTrackerManager().create2dClientTracker("file:///android_asset/test2.wtc");
        tracker.registerTrackerEventListener(this);


        


    }

    @Override
    protected void onResume() {
        super.onResume();
        _wikitudeSDK.onResume();
        _view.onResume();
        _driver.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        _wikitudeSDK.onPause();
        _view.onPause();
        _driver.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        _wikitudeSDK.onDestroy();
    }

    @Override
    public void onRenderExtensionCreated(final RenderExtension renderExtension_) {
        _glRenderer = new GLRenderer(renderExtension_);
        _view = new CustomSurfaceView(getApplicationContext(), _glRenderer);
        _driver = new Driver(_view, 30);

        FrameLayout viewHolder = new FrameLayout(getApplicationContext());
        setContentView(viewHolder);

        viewHolder.addView(_view);

        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        RelativeLayout controls = (RelativeLayout) inflater.inflate(R.layout.activity_campus_recoder, null);
        viewHolder.addView(controls);

    }

//    @Override
//    public void onRenderExtensionCreated(final RenderExtension renderExtension_) {
//        renderExtension = renderExtension_;
//
//
//    }


    @Override
    public void onErrorLoading(final ClientTracker clientTracker_, final String errorMessage_) {
        Log.v(TAG, "onErrorLoading: " + errorMessage_);
    }

    @Override
    public void onTrackerFinishedLoading(final ClientTracker clientTracker_, final String trackerFilePath_) {

    }

    @Override
    public void onTargetRecognized(final Tracker tracker_, final String targetName_) {

        targetDetails = Service.loginByget(targetName_);

//        _glRenderer = new GLRenderer(renderExtension);
//        _view = new CustomSurfaceView(getApplicationContext(), _glRenderer);
//        _driver = new Driver(view, 30);

//        CustomSurfaceView view = View.inflate(this, R.layout.activity_continuous_cloud_tracking, null);
//        TextView textView = (TextView) _view.findViewById(R.id.continuous_tracking_info_field);
//        textView.setText(targetName_);
//        _glRenderer = new GLRenderer(renderExtension);
//        _view = new CustomSurfaceView(getApplicationContext(), _glRenderer);
//        _driver = new Driver(_view, 30);
//        setContentView(_view);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                campusMessageBackGroup = (TextView) findViewById(R.id.campus_location_name);
                campusMessageBackGroup.setVisibility(View.VISIBLE);
//              campusMessageBackGroup.setBackgroundColor(Color.argb(255,0,255,0));
                campusMessageBackGroup.setAlpha(0.8f);
                campusLocationName = (TextView) findViewById(R.id.campus_location_name_item3);
                campusLocationName.setText("Locaion:" + targetDetails);
                campusLocationName.setVisibility(View.VISIBLE);
//                campusOpenTime = (TextView) findViewById(R.id.campus_location_open_time);
//                campusOpenTime.setText("Open TIme :" + "6:00 - 23:00");
//                campusOpenTime.setVisibility(View.VISIBLE);
//                campusMoreDetail = (TextView) findViewById(R.id.campus_location_more_detail);
//                campusMoreDetail.setText("If you want more details. " +
//                        "Please Visit the website : www.baidu.com");
//                campusMoreDetail.setVisibility(View.VISIBLE);
                buttonMovie = (Button) findViewById(R.id.movie_detail);
                buttonMovie.setVisibility(View.VISIBLE);
                buttonMovie.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SimpleClientTrackingActivity.this, MoviePlay.class);
                        startActivity(intent);
                    }
                });
            }
        });



    }



    @Override
    public void onTracking(final Tracker tracker_, final RecognizedTarget recognizedTarget_) {
        _glRenderer.setCurrentlyRecognizedTarget(recognizedTarget_);
//        FrameLayout viewHolder = new FrameLayout(getApplicationContext());


//        viewHolder.addView(_view);
//        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
//        LinearLayout controls = (LinearLayout) inflater.inflate(R.layout.activity_continuous_cloud_tracking, null);
//        TextView textView = (TextView) _view.findViewById(R.id.continuous_tracking_info_field);
//        textView.setText(recognizedTarget_.getName());
//        _view.addTouchables(textView);
//        viewHolder.addView(_view);
//        setContentView(viewHolder);
    }

    @Override
    public void onTargetLost(final Tracker tracker_, final String targetName_) {
        _glRenderer.setCurrentlyRecognizedTarget(null);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                campusLocationName.setVisibility(View.INVISIBLE);
                campusMoreDetail.setVisibility(View.INVISIBLE);
                campusOpenTime.setVisibility(View.INVISIBLE);
                campusMessageBackGroup.setVisibility(View.INVISIBLE);
            }
        });

    }

    @Override
    public void onExtendedTrackingQualityUpdate(final Tracker tracker_, final String targetName_, final int oldTrackingQuality_, final int newTrackingQuality_) {

    }


}