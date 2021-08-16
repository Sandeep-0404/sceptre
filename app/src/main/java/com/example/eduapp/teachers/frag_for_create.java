package com.example.eduapp.teachers;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eduapp.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class frag_for_create extends BottomSheetDialogFragment {
    EditText groupname;
    String pic="notuploadeimg";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View v = inflater.inflate(R.layout.fragment_frag_for_create, container, false);

        TextView usernamef=v.findViewById(R.id.fragusername);
        ImageView cgprofilepic = v.findViewById(R.id.cgprofpic);
        groupname = v.findViewById(R.id.dfgrupname);
        String str=getArguments().getString("username");
        usernamef.setText(str);

        String link=getArguments().getString("link");
        v.findViewById(R.id.creategrup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (groupname.getText().toString().trim().isEmpty()) {
                    groupname.setError("Group name can't be empty");
                    groupname.requestFocus();
                    return;
                }

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user.getUid();


                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                String date1=formatter.format(date);

                String invitecode = getrandomstring(5);
                String edtgrup = groupname.getText().toString().trim();

                DatabaseReference ret2= FirebaseDatabase.getInstance().getReference("totalgroup");
                ret2.push().child("invitecode").setValue(invitecode);

                DatabaseReference refw = FirebaseDatabase.getInstance().getReference("group").child(invitecode);
                modelgrupparticipants modelgroup = new modelgrupparticipants();
                modelgroup.setUid(uid);
                refw.child("members").push().setValue(modelgroup);
                modelgrupdetails modelgrupdetails = new modelgrupdetails();
                modelgrupdetails.setGroupname(edtgrup);
                modelgrupdetails.setDate(date1);
                modelgrupdetails.setInvitecode(invitecode);
               // modelgrupdetails.setGruppic(pic);
                refw.child("groupdetails").setValue(modelgrupdetails);

                DatabaseReference ret23=FirebaseDatabase.getInstance().getReference("userowngroup").child(uid);
                // ret23.push().child("invitecode").setValue(invitecode);
                modelgrupdetails modelgrupdetails1 = new modelgrupdetails();
                modelgrupdetails1.setGroupname(edtgrup);
                modelgrupdetails1.setDate(date1);
                modelgrupdetails1.setInvitecode(invitecode);
               // mdt.setGrouppic(pic);
                ret23.push().setValue(modelgrupdetails1);


                // dismiss();












                /*
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Successfully Created!!")
                        .setMessage("Referal Code is '" + invitecode + "'")
                        .setPositiveButton("copy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                // ClipboardManager clipboard=(ClipboardManager)

                            }
                        })
                        .setNegativeButton("share", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                */

            }
        });




        return v;


    }
    public static String getrandomstring(int i) {
        final String chaaracters = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJklMNOPQRSTUV";
        StringBuilder result = new StringBuilder();
        while (i > 0) {
            Random rand = new Random();
            result.append(chaaracters.charAt(rand.nextInt(chaaracters.length())));
            i--;
        }
        return result.toString();
    }
}