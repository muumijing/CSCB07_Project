package com.example.cscb07_project;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MVPOwnerModel implements Contract.MVPOwnerModel{
    List<String> emails;
    List<String> passwords;

    public MVPOwnerModel(){
        emails = new ArrayList<String>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Owners");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    String email = ds.child("email").getValue(String.class);
                    emails.add(email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public boolean emailExists(String email) {
        return emails.contains(email);
    }

    public boolean userFound(String email, String password){
        passwords = new ArrayList<String>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Owners");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    System.out.println("abcabc");
                    String Email = ds.child("email").getValue(String.class);
                    System.out.println(Email);
                    if (Email.equals(email)){
                        String Password = ds.child("password").getValue(String.class);
                        System.out.println(Password);
                        passwords.add(Password);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        return passwords.get(0).equals(password);
    }

}
