package com.example.busspass;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import org.checkerframework.checker.nullness.qual.NonNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


@RequiresApi(api = Build.VERSION_CODES.O)
public class newPass extends AppCompatActivity {
    public String routes_selected , duration_selected,amount_fare;
    public int fare,total_fare,month_days;
    LocalDate today = LocalDate.now();
    String date = today.format(DateTimeFormatter.ofPattern("dd-MM-yy"));

    // Fetching Logged in User data from Firebase Auth
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference faresRef = db.collection("BusPass");
    String email = user.getEmail();


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_pass);
        Intent intent=getIntent();

        // Select Route Spinner Setting
        String[] items1 = {"SELECT ROUTE", "BHIWANDI - KALYAN" , "BHIWANDI - MULUND" , "BHIWANDI - PUNE" , "BHIWANDI - NASHIK" , "BHIWANDI - KOPAR" , "BHIWANDI - VASAI" , "BHIWANDI - VAJRESHWARI" , "BHIWANDI - THANE"
        };
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner1 = findViewById(R.id.spinner4);
        spinner1.setAdapter(adapter1);
        spinner1.setBackground(getResources().getDrawable(R.drawable.spinner_border));

        // Select Destination Spinner Setting
        String[] items2 = {"SELECT DURATION","1 MONTH", "2 MONTH", "3 MONTH"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner2 = findViewById(R.id.spinner5);
        spinner2.setAdapter(adapter2);
        spinner2.setBackground(getResources().getDrawable(R.drawable.spinner_border));

        // Select Mode of Payment Setting
        String[] items3 = {"SELECT MODE OF PAYMENT","DEBIT CARD","CREDIT CARD","NET BANKING","UPI"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items3);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner3 = findViewById(R.id.spinner6);
        spinner3.setAdapter(adapter3);
        spinner3.setElevation(8);
        spinner3.setBackground(getResources().getDrawable(R.drawable.spinner_border));


        // assigning variables to textview
        TextView textview6 = findViewById(R.id.textView6);
        TextView textview15 = findViewById(R.id.textView15);
        TextView total_Price = findViewById(R.id.textView13);

        // Spinner1 On Selection
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                routes_selected = parent.getItemAtPosition(position).toString();
                fare = 0;
                if (routes_selected.equals("BHIWANDI - KALYAN")) {
                    textview6.setText("Selected Route: " + routes_selected);
                    amount_fare = "20";
                    fare = 20;
                } else if (routes_selected.equals("BHIWANDI - THANE")) {
                    textview6.setText("Selected Route: " + routes_selected);
                    amount_fare = "40";
                    fare = 40;
                } else if (routes_selected.equals("BHIWANDI - MULUND")) {
                    textview6.setText("Selected Route: " + routes_selected);
                    amount_fare = "60";
                    fare = 60;
                } else if (routes_selected.equals("BHIWANDI - KOPAR")) {
                    textview6.setText("Selected Route: " + routes_selected);
                    amount_fare = "45";
                    fare = 45;
                } else if (routes_selected.equals("BHIWANDI - VASAI")) {
                    textview6.setText("Selected Route: " + routes_selected);
                    amount_fare = "70";
                    fare = 70;
                } else if (routes_selected.equals("BHIWANDI - VAJRESHWARI")) {
                    textview6.setText("Selected Route: " + routes_selected);
                    amount_fare = "80";
                    fare = 80;
                } else if (routes_selected.equals("BHIWANDI - PUNE")) {
                    textview6.setText("Selected Route: " + routes_selected);
                    amount_fare = "130";
                    fare = 130;
                } else if (routes_selected.equals("BHIWANDI - NASHIK")) {
                    textview6.setText("Selected Route: " + routes_selected);
                    amount_fare = "110";
                    fare = 110;
                } else {
                    textview6.setText("Selected Route: " + "");
                    textview6.setText("No Route Selected");
                    amount_fare = "0";
                    fare = 0;
                }
                total_fare= fare * month_days;
                total_Price.setText("Total Fare: " + total_fare);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // do nothing
            }
        });

        //Spinner2 on selection
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                duration_selected = parent.getItemAtPosition(position).toString();
                month_days = 0;

                if (duration_selected.equals("1 MONTH")){
                    textview15.setText("Selected option: " + duration_selected);
                    month_days= 30 ;
                }else if (duration_selected.equals("2 MONTH")){
                    textview15.setText("Selected option: " + duration_selected);
                    month_days= 60 ;

                }else if (duration_selected.equals("3 MONTH")){
                    textview15.setText("Selected option: " + duration_selected);
                   month_days= 90 ;

                }else{
                    textview15.setText("Selected option: " + "");
                     month_days= 0 ;
                }
                total_fare= fare * month_days;
                total_Price.setText("Total Fare: " + total_fare);

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // do nothing
            }
        });
    }

    // Go Back Code
    public void goBack(View view) {
        Intent intent = new Intent(newPass.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    // Function for Proceed to Pay
    public void proceedToPay(View view){
        Map<String, Object> fare = new HashMap<>();
        fare.put("totalFare", total_fare);
        fare.put("route", routes_selected);
        fare.put("duration", duration_selected);
        fare.put("date",date);

        faresRef.document(email).set(fare)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(newPass.this, "Payment Successfull ", Toast.LENGTH_SHORT).show();
                        Log.d(MainActivity.class.getSimpleName(), "DocumentSnapshot added with email: " + email);
                        Intent intent = new Intent(newPass.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(newPass.this, "Something went Wrong", Toast.LENGTH_SHORT).show();
                        Log.w(MainActivity.class.getSimpleName(), "Error adding document", e);
                    }
                });


    }

}

