package com.example.picturefirestorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.example.picturefirestorage.Repository.AuthRepository;
import com.example.picturefirestorage.Repository.PictureRepository;

import java.io.InputStream;

public class PictureUploadActivity extends AppCompatActivity {

    private ImageView imageView;
    private PictureRepository pictureRepository;
    private AuthRepository authRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_activity);
        imageView = findViewById(R.id.imageView);
        pictureRepository = new PictureRepository();
        authRepository = new AuthRepository();
    }

    public void onGalleryButtonPressed(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK); //Able to choose between different activities.
        intent.setType("image/*");
        startActivityForResult(intent, 1); // Activity 1
    }

    public void onCameraButtonPressed(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        PackageManager packageManager = getPackageManager();
        if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            startActivityForResult(intent, 2); //Activity 2
        }
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
        //Back from gallery
        if (requestCode == 1 && resultCode == MainActivity.RESULT_OK) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // From Camera
        if (requestCode == 2 && resultCode == MainActivity.RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(bitmap);
        }
    }

    public void onUploadPressed(View view) {
        if (imageView.getDrawable() == null) {
            System.out.println("No image!");
        } else {
            pictureRepository.uploadFile(imageView);
        }
    }

    public void onSignOutPressed(View view) {
        authRepository.signUserOut();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
