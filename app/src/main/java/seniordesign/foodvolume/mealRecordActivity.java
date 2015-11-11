package seniordesign.foodvolume;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by dah106 on 10/23/15.
 */
public class mealRecordActivity extends Activity implements
        AdapterView.OnItemClickListener, Serializable {

    private final static String TAG = "mealRecord";

    private ArrayList<Bitmap> imageBitmapList;

    private ArrayList<Uri> imageUriList;

    private Button mealRecordButton;

    private ListView listView;

    ArrayList<mealRecordItem> rowItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_record);


            mealRecordButton = (Button) findViewById(R.id.mealRecordDone);
            mealRecordButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Log.e(TAG, "Camera is done!!!!");
                        Toast.makeText(getApplicationContext(), "Quiting meal record...", Toast.LENGTH_SHORT).show();
                        finish();
                    }
            });



            imageBitmapList = new ArrayList<>();
            imageUriList = new ArrayList<>();
            rowItems = new ArrayList<>();

            listView = (ListView) findViewById(R.id.mealRecordList);
            listView.setOnItemClickListener(this);

            new backgroundLoadListView().execute();

            Log.d(TAG, "Creating the layout!");

    }

//    @Override
//    protected void onSaveInstanceState(final Bundle outState) {
//        outState.putSerializable("bitmapList", imageBitmapList);
//        outState.putSerializable("urlList", imageUriList);
//        outState.putSerializable("rowItems", (Serializable) rowItems);
//    }

    @Override
    protected void onResume()
    {
        Log.e(TAG, "onResume");
        super.onResume();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

        Log.d(TAG, "Uri of the first image is: " + imageUriList.get(position));

        Uri imageUri = imageUriList.get(position);
        Intent intent = new Intent(this, fullImageDemoActivity.class);
        intent.setData(imageUri);
        startActivity(intent);
    }

    private void populateListView()
    {
        for (int i = 0; i < imageBitmapList.size();i++)
        {
            mealRecordItem item = new mealRecordItem(i, imageBitmapList.get(i), imageUriList.get(i));
            rowItems.add(item);
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

                Uri imageUri = Uri.fromFile(new File(imageFileLocation));

                imageBitmapList.add(thumbnail);
                imageUriList.add(imageUri);
            }
            result = true;
        }
        return result;
    }

    private class backgroundLoadListView extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected void onPostExecute(Void result) {

            Log.d(TAG, "setListAdapter after bitmap preloaded");
            mealRecordListViewAdapter adapter = new mealRecordListViewAdapter(getApplicationContext(), R.layout.meal_record_list_item, rowItems);
            listView.setAdapter(adapter);
        }

        @Override
        protected void onPreExecute() {

            Log.d(TAG, "preload bitmap in AsyncTask");

        }

        @Override
        protected Void doInBackground(Void... params) {

            loadImageFromGallery();
            populateListView();
            return null;
        }
    }

}
