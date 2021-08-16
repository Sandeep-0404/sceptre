package com.example.eduapp.teachers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.eduapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Ques_assignment extends AppCompatActivity {


StorageReference storageReference;
DatabaseReference firebaseDatabase;
String ic;
Button browse,upload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ques_assignment);
        storageReference = FirebaseStorage.getInstance().getReference();
        firebaseDatabase=FirebaseDatabase.getInstance().getReference();
        upload=findViewById(R.id.upload);
        browse=findViewById(R.id.browse);
        Intent intent=getIntent();
        ic=intent.getStringExtra("ic");

    }

    public void browse(View view) {
        Intent intent=new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"PDF FILE SELECT"),11);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode==11&&resultCode==RESULT_OK && data.getData()!=null){
browse.setEnabled(false);
uploadxxx(data.getData());
        }
    }

    public void uploadxxx(Uri data) {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("File is Uploading");
        progressDialog.show();
        StorageReference ref=storageReference.child("pdf").child("hjhjh"+System.currentTimeMillis()+".pdf");
        ref.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri>uriTask=taskSnapshot.getStorage().getDownloadUrl();
                        while (!uriTask.isComplete());
                        Uri uri=uriTask.getResult();
                        DatabaseReference reft=FirebaseDatabase.getInstance().getReference("group").child(ic).child("assignment");
                        reft.push().setValue(uri.toString());
                        Toast.makeText(getApplicationContext(), "uploaded", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        float percent = (100 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount();
                        progressDialog.setMessage("uploaded :" + (int) percent + "%");
                    }
                });


    }
}