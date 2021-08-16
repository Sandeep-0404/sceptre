package com.example.eduapp.teachers;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.eduapp.student.assignment;

public class frag_windows_adapter extends FragmentStateAdapter {
    public frag_windows_adapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new post_assignment();
            case 2:
                return new attendence();

        }

        return new chats();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}