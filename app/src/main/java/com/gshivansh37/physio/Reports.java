package com.gshivansh37.physio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Reports extends AppCompatActivity {

    TextView first_value, second_value, third_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        first_value = findViewById(R.id.first_value);
        second_value = findViewById(R.id.second_value);
        third_value = findViewById(R.id.third_value);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        DatabaseReference myRef2 = database.getReference("trig");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.child("trig").getValue(String.class);
                Integer value1 = dataSnapshot.child("data").getValue(Integer.class);

                if(value.equals("1")) {
                    first_value.setText(String.valueOf(value1));
                }
                else
                if(value.equals("2")) {
                    second_value.setText(String.valueOf(value1));
                }
                else
                if(value.equals("3")) {
                    third_value.setText(String.valueOf(value1));
                }
//                second_value.setText(String.valueOf(value1));
                //  Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

//        myRef2.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String value = dataSnapshot.child("trig").getValue(String.class);
//
//                //  Log.d(TAG, "Value is: " + value);
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                //Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });

    }
}
