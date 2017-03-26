package io.github.ngbrown11.tipvision.camera;

/**
 * Created by Nate on 3/25/2017.
 */
import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.annotation.StringDef;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import com.google.android.gms.common.images.Size;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;

import java.io.IOException;
import java.lang.Thread.State;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Manages the camera in conjunction with an underlying
 * {@link com.google.android.gms.vision.Detector}.  This receives preview frames from the camera at
 * a specified rate, sending those frames to the detector as fast as it is able to process those
 * frames.
 * The following Android permission is required to use the camera:
 * <ul>
 * <li>android.permissions.CAMERA</li>
 * </ul>
 */
@SuppressWarnings("deprecation")
public class CameraSource {
    /** A safe way to get an instance of the Camera object. */
    public static Camera getCameraInstance(){
        Camera cam = null;
        try {
            cam = Camera.open(); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return cam; // returns null if camera is unavailable
    }
}

