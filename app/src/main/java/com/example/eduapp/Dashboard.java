package com.example.eduapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.eduapp.Auth.AuthActivity;
import com.example.eduapp.Auth.Update_Profile;
import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

    }

    @Override
    public void onBackPressed() {
        showAlertDialog();
    }

    private void showAlertDialog() {

        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create().show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Dashboard.this, AuthActivity.class));
                finish();
                return true;

            case R.id.profile :
                startActivity(new Intent(Dashboard.this, Update_Profile.class));
                finish();
                return true;
        }
        return false;
    }
}