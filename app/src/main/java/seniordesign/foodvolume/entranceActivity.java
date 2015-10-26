package seniordesign.foodvolume;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class entranceActivity extends Activity {

    private static final int ACTIVITY_START_CAMERA_APP = 0;


    private Button cameraButton;
    private Button mealButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);
    }

    public void getFvCameraPreview(View view)
    {
        Intent intent = new Intent(this, fvCameraFragment.class);
        startActivity(intent);
        Toast.makeText(this, "Starting camera preview...", Toast.LENGTH_SHORT).show();
    }

    public void checkMealRecord(View view)
    {
        //Intent intent = new Intent(this, mealRecordActivity.class);
        Intent intent = new Intent(this, mealRecordActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Displaying meal record...", Toast.LENGTH_SHORT).show();
    }


}
