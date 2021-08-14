package com.example.eduapp.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.eduapp.Dashboard;
import com.example.eduapp.R;
import com.example.eduapp.databinding.FragmentSignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class FragmentSignup extends Fragment {

    FragmentSignupBinding fragmentSignupBinding;
    FirebaseAuth auth;
    DatabaseReference myRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentSignupBinding=FragmentSignupBinding.inflate(getLayoutInflater());
        auth = FirebaseAuth.getInstance();
        fragmentSignupBinding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = fragmentSignupBinding.name.getText().toString();
                String userName = fragmentSignupBinding.username.getText().toString();
                String email = fragmentSignupBinding.email.getText().toString();
                String confirmPassword = fragmentSignupBinding.confirmPassword.getText().toString();
                String password = fragmentSignupBinding.password.getText().toString();
                if (name.isEmpty()) {
                    fragmentSignupBinding.name.setError("Name is Required");
                    fragmentSignupBinding.name.requestFocus();
                    return;
                }
                if (userName.isEmpty()) {
                    fragmentSignupBinding.username.setError("User Name is Required");
                    fragmentSignupBinding.username.requestFocus();
                    return;
                }
                if (email.isEmpty()) {
                    fragmentSignupBinding.email.setError("Email ID is Required");
                    fragmentSignupBinding.email.requestFocus();
                    return;
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    fragmentSignupBinding.email.setError("Email ID is invalid");
                    fragmentSignupBinding.email.requestFocus();
                    return;
                }
                if (password.isEmpty()) {
                    fragmentSignupBinding.password.setError("Password is Required");
                    fragmentSignupBinding.password.requestFocus();
                    return;
                } else if (password.length() < 6) {
                    fragmentSignupBinding.password.setError("Password should contain minimum 6 characters!");
                    fragmentSignupBinding.password.requestFocus();
                    return;
                }

                if (confirmPassword.isEmpty()) {
                    fragmentSignupBinding.confirmPassword.setError("Confirm your Password!");
                    fragmentSignupBinding.confirmPassword.requestFocus();
                    return;
                } else if (password.length() < 6) {
                    fragmentSignupBinding.confirmPassword.setError("Password should contain minimum 6 characters!");
                    fragmentSignupBinding.confirmPassword.requestFocus();
                    return;
                } else if (password.equals(confirmPassword) == false) {
                    fragmentSignupBinding.confirmPassword.setError("Does not match the Password!");
                    fragmentSignupBinding.confirmPassword.requestFocus();
                    return;
                }
                RegisterNow(email, password, userName, name);
                fragmentSignupBinding.progressBar.setVisibility(View.VISIBLE);
            }

        });

        return fragmentSignupBinding.getRoot();
    }

    private void RegisterNow(String email, String password, String username, String name) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    auth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            String userid = firebaseUser.getUid();
                            myRef = FirebaseDatabase.getInstance().getReference("Users").child(userid);
                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("id", userid);
                            hashMap.put("name", name);
                            hashMap.put("username", username);
                            hashMap.put("imageurl", "https://tse2.mm.bing.net/th?id=OIP.NYi0ibx-GnoFsgKhwj8WfgHaLZ&pid=Api&P=0&w=300&h=300");

                            myRef.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getActivity(), "Check your email and verify your email address!", Toast.LENGTH_SHORT).show();
                                        fragmentSignupBinding.progressBar.setVisibility(View.GONE);
                                        if (auth.getCurrentUser().isEmailVerified()) {
                                            Intent i = new Intent(getActivity(), Dashboard.class);
                                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(i);
                                        }
                                    }
                                }
                            });
                        }

                    });
                }
                else {
                    Toast.makeText(getActivity(), "Invalid!", Toast.LENGTH_SHORT).show();
                    fragmentSignupBinding.progressBar.setVisibility(View.GONE);
                }
            }
        });

    }

}