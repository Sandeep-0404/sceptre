package com.example.eduapp.ToDoList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.eduapp.R;
import com.example.eduapp.databinding.ActivityAddNoteBinding;

public class AddNoteActivity extends AppCompatActivity {

    ActivityAddNoteBinding activityAddNoteBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAddNoteBinding=ActivityAddNoteBinding.inflate(getLayoutInflater());
        setContentView(activityAddNoteBinding.getRoot());
        activityAddNoteBinding.numberPickerPriority.setMinValue(1);
        activityAddNoteBinding.numberPickerPriority.setMaxValue(10);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_close_24);
        Intent intent=getIntent();
        if(intent.hasExtra("EXTRA_ID")) {
            setTitle("Edit Note");
            activityAddNoteBinding.editTextTitle.setText(intent.getStringExtra("EXTRA_TITLE"));
            activityAddNoteBinding.editTextDescription.setText(intent.getStringExtra("EXTRA_DESCRIPTION"));
            activityAddNoteBinding.numberPickerPriority.setValue(intent.getIntExtra("EXTRA_PRIORITY",1));
        }
        else
            setTitle("Add Note");

    }

    private void saveNote()
    {
        String title=activityAddNoteBinding.editTextTitle.getText().toString();
        String description=activityAddNoteBinding.editTextDescription.getText().toString();
        int priority=activityAddNoteBinding.numberPickerPriority.getValue();
        if (title.trim().isEmpty() || description.trim().isEmpty())
        {
            Toast.makeText(this, "Enter Your Notes", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent=new Intent();
        intent.putExtra("EXTRA_TITLE",title);
        intent.putExtra("EXTRA_DESCRIPTION",description);
        intent.putExtra("EXTRA_PRIORITY",priority);
        int id=getIntent().getIntExtra("EXTRA_ID",-1);
        if (id!=-1)
            intent.putExtra("EXTRA_ID",id);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.add,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.save :
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}