package io.github.ngbrown11.tipvision.camera;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

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
    private boolean accepted;

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
        captureButton.setAlpha(0.9f);
        captureButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // get an image from the camera
                        cam.takePicture(null, null, null);
                        onPictureTaken(cam);
                        /*Intent intent = new Intent(CameraActivity.this, CalculationResultsActivity.class);
                        intent.putExtra(EXTRA_MESSAGE, configs);
                        startActivity(intent);
                        // TODO: preview.surfaceDestroyed(preview.getHolder());
                        CameraActivity.this.finish();*/
                    }
                }
        );
        // Tell user how to capture image
        Snackbar.make(preview, "Touch anywhere to capture receipt",
                Toast.LENGTH_LONG)
                .show();
        // Tell user how to cancel image capture
        Snackbar.make(preview, "Touch bottom left to go back",
                Toast.LENGTH_LONG)
                .show();
    }

    public void onPictureTaken(Camera camera) {
        // TODO make buttons visible for user
        Button capture = (Button) findViewById(R.id.button_capture);
        capture.setVisibility(View.GONE);

        FloatingActionButton check = (FloatingActionButton) findViewById(R.id.fabCheck);
        check.setVisibility(View.VISIBLE);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acceptedCapture();
            }
        });
        // Tell user how to accept image
        Snackbar.make(preview, "Touch bottom right to calculate",
                Toast.LENGTH_LONG)
                .show();

        FloatingActionButton redo = (FloatingActionButton) findViewById(R.id.fabRedo);
        redo.setVisibility(View.VISIBLE);
        redo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redo();
            }
        });
        // Tell user how to redo image
        Snackbar.make(preview, "Touch bottom center to redo",
                Toast.LENGTH_LONG)
                .show();

        FloatingActionButton cancel = (FloatingActionButton) findViewById(R.id.fabCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                canceledCapture();
            }
        });
        // Tell user how to cancel image capture
        Snackbar.make(preview, "Touch bottom left to go back",
                Toast.LENGTH_LONG)
                .show();

    }

    private void redo() {
        preview.onResume();
    }

    private void acceptedCapture() {
        Intent intent = new Intent(CameraActivity.this, CalculationResultsActivity.class);
        intent.putExtra(EXTRA_MESSAGE, configs);
        startActivity(intent);
        CameraActivity.this.finish();
    }

    private void canceledCapture() {
        CameraActivity.this.finish();
    }
}
