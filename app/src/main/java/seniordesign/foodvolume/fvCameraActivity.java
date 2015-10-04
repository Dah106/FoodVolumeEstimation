package seniordesign.foodvolume;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class fvCameraActivity extends Activity {

    public TextView cameraTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fv_camera);

        Intent intent = getIntent();
        String message = intent.getStringExtra(entranceActivity.FVCAMERA_PREVIEW_MESSAGE);
        cameraTextView = (TextView) findViewById(R.id.cameraPreviewTextView);
        cameraTextView.setText(message);
    }
}
