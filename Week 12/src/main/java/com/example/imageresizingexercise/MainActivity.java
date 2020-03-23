package com.example.imageresizingexercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    EditText imageHeight;
    EditText imageWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        imageHeight = findViewById(R.id.editText);
        imageWidth = findViewById(R.id.editText2);

        //Asks user for permission to write to external storage (i.e. the photo folder on the phone).
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
    }

    public void onCameraButtonPressed(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        PackageManager packageManager = getPackageManager();
        if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            startActivityForResult(intent, 1);
        }
    }

    public void onResizeButtonPressed(View view) {
        // Gets the new width and height from the text fields.
        int newHeight = Integer.parseInt(imageHeight.getText().toString());
        int newWidth = Integer.parseInt(imageWidth.getText().toString());

        //Gets the original picture from the imageView.
        Bitmap originalPicture = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        //Creates a new bitmap with the new width and height.
        Bitmap resized =
                Bitmap.createScaledBitmap(originalPicture, newWidth, newHeight, true);
        //Set the resized image to the imageView instead.
        imageView.setImageBitmap(resized);
    }

    public void onSaveButtonPressed(View view) {
        //Insert the image from imageView to photo folder on phone.
        imageView.setDrawingCacheEnabled(true);
        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "Picture", "This is a description");

        //Clear the fields after insertion
        imageWidth.setText("");
        imageHeight.setText("");
        imageView.setImageResource(0);
    }

    /**
     * When startActivityForResult is run, this method will fire and process the activity.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // From Camera
        if (requestCode == 1 && resultCode == MainActivity.RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(bitmap);

            String height = Integer.toString(imageView.getMeasuredHeight());
            String width = Integer.toString(imageView.getMeasuredWidth());

            /**
             * When a picture is taken and put into the imageView, the width and height from that
             * picture is put into two text fields. The user can then edit the text fields with
             * their desired value and save it.
             */

            imageHeight.setText(height);
            imageWidth.setText(width);
        }
    }
}
