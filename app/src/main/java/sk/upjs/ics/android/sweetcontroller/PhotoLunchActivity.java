package sk.upjs.ics.android.sweetcontroller;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class PhotoLunchActivity  extends CameraIntentHelperActivity {
    private ImageView imageView;
    private Bitmap photo;
    boolean resume;
    boolean pause=true;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_lunch);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_photo_lunch, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onStartCamera(View view) {

        startCameraIntent();

    }

    @Override
    protected void onPhotoUriFound() {//dostanem uri do contentprovidera kde sa ta fotka nachadza
        TextView uirView = (TextView) findViewById(R.id.activity_take_photo_image_uri);
        // uirView.setText("photo uri: " + photoUri.toString());
        editText = (EditText)findViewById(R.id.edit);

        photo = BitmapHelper.readBitmap(this, photoUri);
        if (photo != null) {
            photo = BitmapHelper.shrinkBitmap(photo, 300, rotateXDegrees);
            //list.add(photo);
            // listView = (GridView)findViewById(R.id.listView);
            // ArrayAdapter<Bitmap> adapter = new ArrayAdapter<Bitmap>(this, android.R.layout.simple_gallery_item, list);
            imageView = (ImageView) findViewById(R.id.activity_take_photo_image_view);
            imageView.setImageBitmap(photo);


        }

        //Delete photo in second location (if applicable)
        if (preDefinedCameraUri != null && !preDefinedCameraUri.equals(photoUri)) {
            BitmapHelper.deleteImageWithUriIfExists(preDefinedCameraUri, this);
        }
        //Delete photo in third location (if applicable)
        if (photoUriIn3rdLocation != null) {
            BitmapHelper.deleteImageWithUriIfExists(photoUriIn3rdLocation, this);
        }
    }

    @Override
    protected void onPhotoUriNotFound() {
        super.onPhotoUriNotFound();
        TextView uirView = (TextView) findViewById(R.id.activity_take_photo_image_uri);
        uirView.setText("photo uri: not found");
    }
    @Override
    protected void onPause() {
        if (photoUri != null) {
            getPreferences().edit()
                    .putString("lunchPhoto", photoUri.toString())
                    .commit();

        }
        getPreferences().edit()
                .putString("lunchDescription", editText.getText().toString())
                .commit();
        photoUri=null;
        super.onPause();

    }

    private SharedPreferences getPreferences() {
        return this
                .getPreferences(PhotoLunchActivity.MODE_PRIVATE);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(photoUri==null) {
            String breakfastPhoto = getPreferences().getString("lunchPhoto", "file:///storage/emulated/0/DCIM/Camera/1432646643954.jpg");
            System.out.println("uri" + Uri.parse(breakfastPhoto));
            photo = BitmapHelper.readBitmap(this, Uri.parse(breakfastPhoto));
            if (photo != null) {
                photo = BitmapHelper.shrinkBitmap(photo, 300, rotateXDegrees);
                //list.add(photo);
                // listView = (GridView)findViewById(R.id.listView);
                // ArrayAdapter<Bitmap> adapter = new ArrayAdapter<Bitmap>(this, android.R.layout.simple_gallery_item, list);
                imageView = (ImageView) findViewById(R.id.activity_take_photo_image_view);
                imageView.setImageBitmap(photo);



            }

            editText = (EditText)findViewById(R.id.edit);
            String text = getPreferences().getString("lunchDescription","");
            editText.setText(text);
        }




        //resume=true;
    }
}
