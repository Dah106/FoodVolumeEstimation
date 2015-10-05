package seniordesign.foodvolume;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class fvCameraActivity extends Activity {

    public TextView cameraTextView;

    ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fv_camera);

        Intent intent = getIntent();
        String message = intent.getStringExtra(entranceActivity.FVCAMERA_PREVIEW_MESSAGE);
        cameraTextView = (TextView) findViewById(R.id.cameraPreviewTextView);
        cameraTextView.setText(message);

        cameraButtonPressed();
    }

    public void cameraButtonPressed() {

        imageButton = (ImageButton) findViewById(R.id.cameraButton);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(fvCameraActivity.this, "Taking picture now...", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
