package com.knockknock.dragonra.smartdoor.utility;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.knockknock.dragonra.smartdoor.R;
import com.knockknock.dragonra.smartdoor.model.UserMember;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterUserDatabaseClient extends AppCompatActivity {

    private static final String TAG = "RegisterUserDBClient";

    private Button mAddToDb;
    private EditText mNewName;
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference myRef;
    private int count;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_to_database_layout);

        mAddToDb = (Button) findViewById(R.id.btnAddToDatabase);
        mNewName = (EditText) findViewById(R.id.add_new_user);

        myRef = mDatabase.getReference();

        mAddToDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: add new user");

                String name = mNewName.getText().toString();
                DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                Date date = new Date();
                String strDate = dateFormat.format(date);
                UserMember user = new UserMember(name, "1");
                myRef.child("member").child(""+strDate).setValue(user);
                finish();
            }
        });
    }
}
