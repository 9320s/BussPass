package com.example.busspass;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class currentPass extends AppCompatActivity {
    // Defining variables going to user below
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference faresRef = db.collection("BusPass");
    String email = user.getEmail();
    String user_name = user.getDisplayName();
    public String updated_Route,date_created;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_pass);
        Intent intent = getIntent();

        TextView fare_cp = findViewById(R.id.textView12);
        TextView duration_cp = findViewById(R.id.textView14);
        TextView source_cp = findViewById(R.id.textView17);
        TextView target_cp = findViewById(R.id.textView18);
        TextView username = findViewById(R.id.textView20);
        TextView date_cp = findViewById(R.id.textView11);


        //Firebase auth code
        faresRef.document(email).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {

                            // Fetch the data from firebase and assing them to variables

                            double updated_Fare = documentSnapshot.getDouble("totalFare") ;
                            updated_Route = documentSnapshot.getString("route");
                            String updated_Duration = documentSnapshot.getString("duration");
                            date_created = documentSnapshot.getString("date");
                            duration_cp.setText("Valid Upto: " + updated_Duration);
                            fare_cp.setText("" + updated_Fare + " RS");

                            username.setText("" + user_name);
                            date_cp.setText(date_created);


                            // defining Source and Destination by Separating them

                            if (updated_Route.equals("BHIWANDI - THANE")) {
                                source_cp.setText("BHIWANDI");
                                target_cp.setText("THANE");

                            } else if (updated_Route.equals("BHIWANDI - KALYAN")) {
                                source_cp.setText("BHIWANDI");
                                target_cp.setText("KALYAN");

                            } else if (updated_Route.equals("BHIWANDI - MULUND")) {
                                source_cp.setText("BHIWANDI");
                                target_cp.setText("MULUND");

                            } else if (updated_Route.equals("BHIWANDI - KOPAR")) {
                                source_cp.setText("BHIWANDI");
                                target_cp.setText("KOPAR");

                            } else if (updated_Route.equals("BHIWANDI - VASAI")) {
                                source_cp.setText("BHIWANDI");
                                target_cp.setText("VASAI");


                            } else if (updated_Route.equals("BHIWANDI - VAJRESHWARI")) {
                                source_cp.setText("BHIWANDI");
                                target_cp.setText("VAJRESJWARI");

                            } else if (updated_Route.equals("BHIWANDI - PUNE")) {
                                source_cp.setText("BHIWANDI");
                                target_cp.setText("PUNE");

                            } else if (updated_Route.equals("BHIWANDI - NASHIK")) {
                                    source_cp.setText("BHIWANDI");
                                    target_cp.setText("NASHIK");

                            } else {
                                source_cp.setText("$$$$");
                                target_cp.setText("$$$$");

                            }

                            Toast.makeText(currentPass.this, "Successfully fetched data", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.d(MainActivity.class.getSimpleName(), "No such document");
                            Toast.makeText(currentPass.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(MainActivity.class.getSimpleName(), "get failed with ", e);
                    }
                });
    }


    // Function to save Ticket Image in Gallery
    public void download_image(View view){
        View v = findViewById(R.id.frameLayout3);
        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache();
        Bitmap bm = v.getDrawingCache();
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), bm, "My Image", "My Image");
        Uri imageUri = Uri.parse(path);
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, imageUri));
        Toast.makeText(this, "Pass Saved in Gallery", Toast.LENGTH_SHORT).show();
    }

    // Go Back Code
    public void goBack1(View view) {
        Intent intent = new Intent(currentPass.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }


}

