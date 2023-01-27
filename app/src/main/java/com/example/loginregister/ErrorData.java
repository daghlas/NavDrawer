package com.example.loginregister;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class ErrorData extends AppCompatActivity {

    TextInputEditText textInputEditTextErrorName, textInputEditTextErrorDescription;
    Button buttonSubmit;
    Button buttonCapture;
    TextView textViewCancel;
    ImageView imageView1;
    ProgressBar progressBarUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.error_data);

        textInputEditTextErrorName = findViewById(R.id.name);
        textInputEditTextErrorDescription = findViewById(R.id.description);
        buttonSubmit = findViewById(R.id.btnsubmit);
        buttonCapture = findViewById(R.id.btnCapture);
        textViewCancel = findViewById(R.id.cancel);
        imageView1 = findViewById(R.id.imageView);
        progressBarUpload = findViewById(R.id.progressBarUpload);

        buttonCapture.setOnClickListener(view -> {
            Intent capture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(capture, 101);
        });

        textViewCancel.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        });

        buttonSubmit.setOnClickListener(v -> {
            String name, image, description;
            name = String.valueOf(textInputEditTextErrorName.getText());
            image = String.valueOf(imageView1.getImageAlpha());
            description = String.valueOf(textInputEditTextErrorDescription.getText());
            if (!name.equals("") && !description.equals("")) {
                progressBarUpload.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.post(() -> {
                    String[] field = new String[3];
                    field[0] = "name";
                    field[1] = "image";
                    field[2] = "description";
                    String[] data = new String[3];
                    data[0] = name;
                    data[1] = image;
                    data[2] = description;
                    PutData putData = new PutData("http://192.168.104.87/LoginRegister/error.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            progressBarUpload.setVisibility(View.GONE);
                            String result = putData.getResult();
                            if (result.equals("Uploaded Successfully")) {
                                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
            } else {
                Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_LONG).show();
            }
        });

    }
    @Override
    public void onResume() {
        super.onResume();
        if (hasPermission())
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 101);
        else if (hasPermission())
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 101);
    }
    private boolean hasPermission() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView1.setImageBitmap(photo);
        }
    }
}
