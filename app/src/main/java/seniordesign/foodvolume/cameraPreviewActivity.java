package seniordesign.foodvolume;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by dah106 on 10/23/15.
 */
public class cameraPreviewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_preview);
//        if (null == savedInstanceState) {
//            getFragmentManager().beginTransaction()
//                    .replace(R.id.cameraPreviewContainer, fvCameraFragment.newInstance())
//                    .commit();
//        }

        Intent intent = new Intent(this, fvCameraFragment.class);
        startActivity(intent);

    }
}
