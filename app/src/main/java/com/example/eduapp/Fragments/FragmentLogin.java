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

import com.example.eduapp.Auth.ForgotPassword;
import com.example.eduapp.Dashboard;
import com.example.eduapp.MainActivity;
import com.example.eduapp.R;
import com.example.eduapp.databinding.FragmentLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class FragmentLogin extends Fragment {

    FragmentLoginBinding fragmentLoginBinding;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;

    @Override
    public void onStart() {
        super.onStart();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser != null) {
            Intent i = new Intent(getActivity(), Dashboard.class);
            startActivity(i);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentLoginBinding=FragmentLoginBinding.inflate(getLayoutInflater());
        fragmentLoginBinding.forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ForgotPassword.class));
            }
        });

        auth=FirebaseAuth.getInstance();
        fragmentLoginBinding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_text=fragmentLoginBinding.emailLogin.getText().toString();
                String pass_text=fragmentLoginBinding.passwordLogin.getText().toString();
                if (email_text.isEmpty()) {
                    fragmentLoginBinding.emailLogin.setError("Email ID is Required");
                    fragmentLoginBinding.emailLogin.requestFocus();
                    return;
                }else if (!Patterns.EMAIL_ADDRESS.matcher(email_text).matches()) {
                    fragmentLoginBinding.emailLogin.setError("Email ID is invalid");
                    fragmentLoginBinding.emailLogin.requestFocus();
                    return;
                }
                if (pass_text.isEmpty()) {
                    fragmentLoginBinding.passwordLogin.setError("Password is Required");
                    fragmentLoginBinding.passwordLogin.requestFocus();
                    return;
                }

                fragmentLoginBinding.progressBar2.setVisibility(View.VISIBLE);

                auth.signInWithEmailAndPassword(email_text,pass_text).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent i = new Intent(getActivity(), Dashboard.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i);
                            fragmentLoginBinding.progressBar2.setVisibility(View.GONE);

                        }
                        else {
                            Toast.makeText(getActivity(),"Authentication failed!",Toast.LENGTH_SHORT).show();
                            fragmentLoginBinding.progressBar2.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });
        return fragmentLoginBinding.getRoot();
    }
}