package de.ecotastic.android.camerautil;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.ecotastic.android.camerautil.lib.CameraIntentHelperActivity;
import de.ecotastic.android.camerautil.util.BitmapHelper;

/**
 * Example Activity of how to use the CameraIntentHelperActivity
 *
 * @author Ralf Gehrer <ralf@ecotastic.de>
 */
public class PhotoBreakfastActivity extends CameraIntentHelperActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_breakfast);
    }

    public void onStartCamera(View view) {
        startCameraIntent();
    }

    @Override
    protected void onPhotoUriFound() {//dostanem uri do contentprovidera kde sa ta fotka nachadza
        TextView uirView = (TextView) findViewById(R.id.activity_take_photo_image_uri);
        uirView.setText("photo uri: " + photoUri.toString());

        Bitmap photo = BitmapHelper.readBitmap(this, photoUri);
        if (photo != null) {
            photo = BitmapHelper.shrinkBitmap(photo, 300, rotateXDegrees);
            //list.add(photo);
            // listView = (GridView)findViewById(R.id.listView);
            // ArrayAdapter<Bitmap> adapter = new ArrayAdapter<Bitmap>(this, android.R.layout.simple_gallery_item, list);
            ImageView imageView = (ImageView) findViewById(R.id.activity_take_photo_image_view);
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
}