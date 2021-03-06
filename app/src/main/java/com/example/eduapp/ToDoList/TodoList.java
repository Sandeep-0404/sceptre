package com.example.eduapp.ToDoList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.eduapp.Adapters.NoteAdapter;
import com.example.eduapp.R;
import com.example.eduapp.databinding.ActivityTodoListBinding;

import java.util.List;

public class TodoList extends AppCompatActivity {
    private NoteViewModel noteViewModel;
    ActivityTodoListBinding activityTodoListBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTodoListBinding=ActivityTodoListBinding.inflate(getLayoutInflater());
        setContentView(activityTodoListBinding.getRoot());
        getSupportActionBar().hide();
        activityTodoListBinding.buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TodoList.this,AddNoteActivity.class);
                startActivityForResult(intent,1);
            }
        });
        activityTodoListBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        activityTodoListBinding.recyclerView.setHasFixedSize(true);
        NoteAdapter adapter=new NoteAdapter();
        activityTodoListBinding.recyclerView.setAdapter(adapter);

        noteViewModel= ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.setNotes(notes);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                noteViewModel.delete(adapter.getNoteAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(activityTodoListBinding.recyclerView);
        adapter.setOnItemClickListener(new NoteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Note note) {
                Intent intent=new Intent(TodoList.this,AddNoteActivity.class);
                intent.putExtra("EXTRA_ID",note.getId());
                intent.putExtra("EXTRA_TITLE",note.getTitle());
                intent.putExtra("EXTRA_DESCRIPTION",note.getDescription());
                intent.putExtra("EXTRA_PRIORITY",note.getPriority());
                startActivityForResult(intent,2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK)
        {
            String title=data.getStringExtra("EXTRA_TITLE");
            String description=data.getStringExtra("EXTRA_DESCRIPTION");
            int priority=data.getIntExtra("EXTRA_PRIORITY",1);
            Note note=new Note(title,description,priority);
            noteViewModel.insert(note);
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        }
        else if (requestCode==2 && resultCode==RESULT_OK)
        {
            int id=data.getIntExtra("EXTRA_ID",-1);
            if (id==-1)
            {
                Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
                return;
            }
            String title=data.getStringExtra("EXTRA_TITLE");
            String description=data.getStringExtra("EXTRA_DESCRIPTION");
            int priority=data.getIntExtra("EXTRA_PRIORITY",1);

            Note note=new Note(title,description,priority);
            note.setId(id);
            noteViewModel.update(note);
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.note_menu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch ((item.getItemId()))
        {
            case R.id.deleteAllNotes:
                noteViewModel.deleteAllNotes();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}