package io.github.ngbrown11.tipvision.camera;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import io.github.ngbrown11.tipvision.CalculationResultsActivity;
import io.github.ngbrown11.tipvision.R;

import static android.R.attr.id;
import static io.github.ngbrown11.tipvision.camera.CameraSource.getCameraInstance;

@SuppressWarnings("deprecation")
public class CameraActivity extends Activity {

    public final static String EXTRA_MESSAGE = "io.github.ngbrown11.tipvision.camera.MESSAGE";
    private Camera cam;
    private CameraPreview preview;
    private int[] configs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        // Get intent from prev activity
        Intent intent = getIntent();
        configs = intent.getExtras().getIntArray("io.github.ngbrown11.tipvision.MESSAGE");

        // Create an instance of Camera
        cam = getCameraInstance();

        // Create our Preview view and set it as the content of our activity.
        preview = new CameraPreview(this, cam);
        FrameLayout previewL = (FrameLayout) findViewById(R.id.camera_preview);
        previewL.addView(preview);

        // Add a listener to the Capture button
        Button captureButton = (Button) findViewById(R.id.button_capture);
        captureButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // get an image from the camera
                        cam.takePicture(null, null, null);
                        Intent intent = new Intent(CameraActivity.this, CalculationResultsActivity.class);
                        intent.putExtra(EXTRA_MESSAGE, configs);
                        startActivity(intent);
                        // TODO: preview.surfaceDestroyed(preview.getHolder());
                        CameraActivity.this.finish();
                    }
                }
        );
    }
}
