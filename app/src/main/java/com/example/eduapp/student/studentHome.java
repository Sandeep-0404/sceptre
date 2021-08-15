package com.example.eduapp.student;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.eduapp.R;
import com.example.eduapp.databinding.ActivityStudentHomeBinding;
import com.example.eduapp.databinding.ActivityStudentsHomeBinding;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;


public class studentHome extends AppCompatActivity {
ChipNavigationBar chipNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        chipNavigationBar=findViewById(R.id.chip);





       chipNavigationBar.setItemSelected(R.id.home,true);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new home()).commit();

        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch (i) {
                    case R.id.home:
                        fragment = new home();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();

                        break;

                    case R.id.result:
                        fragment = new result();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();

                        break;

                    case R.id.assignment:
                        fragment = new assignment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();

                        break;

                    case R.id.create_grp:
                        fragmentForcreate fragmentForcreate=new fragmentForcreate();
                        fragmentForcreate.show(getSupportFragmentManager(),fragmentForcreate.getTag());
                        break;
                    case R.id.join_grp:
                       bottomFragmentJoin bottomFragmentJoin=new bottomFragmentJoin();
                       bottomFragmentJoin.show(getSupportFragmentManager(),bottomFragmentJoin.getTag());

                        break;

                }



            }
        });



    }
}