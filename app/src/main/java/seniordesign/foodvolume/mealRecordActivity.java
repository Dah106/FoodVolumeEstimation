package seniordesign.foodvolume;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dah106 on 10/23/15.
 */
public class mealRecordActivity extends Activity implements
        AdapterView.OnItemClickListener {

    private final static int RESULT_LOAD_IMG = 1;
    private final static String TAG = "mealRecord";

    private ArrayList<Bitmap> imageList;

    private Button mealRecordButton;

    private ListView listView;

    List<mealRecordItem> rowItems;
    List<mrItem> rItems;


    private ImageView testImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_record);


        mealRecordButton = (Button)findViewById(R.id.mealRecordDone);
        mealRecordButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e(TAG, "Camera is done!!!!");
                Toast.makeText(getApplicationContext(), "Quiting meal record...", Toast.LENGTH_SHORT).show();
                mealRecordDone();
            }
        });


        imageList = new ArrayList<>();
        rItems = new ArrayList<>();

        populateListView();

        listView = (ListView) findViewById(R.id.mealRecordList);
        mealRecordListViewAdapter adapter = new mealRecordListViewAdapter(this, R.layout.meal_record_list_item, rItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        Toast toast = Toast.makeText(getApplicationContext(),
                "Item " + (position + 1) + ": " + rowItems.get(position),
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }

    private void mealRecordDone()
    {
        this.finish();
    }

    private void populateListView()
    {
        if(loadImageFromGallery())
        {
            for (int i = 0; i < imageList.size();i++)
            {
                mrItem item = new mrItem(i, imageList.get(i));
                rItems.add(item);
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Image folder is empty.", Toast.LENGTH_LONG).show();
        }
    }

    private boolean loadImageFromGallery()
    {

        boolean result = false;
        File imageFolder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString());

        Log.d(TAG, "Image Directory is: " + imageFolder.getAbsolutePath());

        if(imageFolder.exists())
        {
            String[] fileNames = imageFolder.list();

            final int miniThumbWidth=120;
            final int miniThumbHeight=120;

            for(int i = 0; i < fileNames.length; i++)
            {
                Log.d(TAG, "Image name is: " + fileNames[i]);
                String imageFileLocation = imageFolder.getAbsolutePath() + "/" + fileNames[i];

                Bitmap normalBitmap = BitmapFactory.decodeFile(imageFileLocation);

                Bitmap thumbnail = Bitmap.createScaledBitmap(normalBitmap, miniThumbWidth, miniThumbHeight, true);
                imageList.add(thumbnail);
            }
            result = true;
        }
        return result;
    }


    protected void startCameraPreview(Bundle savedInstanceState)
    {
        Intent intent = new Intent(this, fvCameraFragment.class);
        startActivity(intent);
        Toast.makeText(this, "Starting camera preview...", Toast.LENGTH_SHORT).show();
    }



}
