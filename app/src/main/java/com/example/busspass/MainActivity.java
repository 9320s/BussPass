package com.example.busspass;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Function to LogOut
    public void signOut(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

// Function to forward user to NewPass Page
    public void Buyer(View view) {
        Intent intent = new Intent(MainActivity.this, newPass.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

// function to forward user to Current Pass Page
    public void Ticket(View view) {
        Intent intent = new Intent(MainActivity.this, currentPass.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

// Funtion to download the pdf for fare
    public void download_pdf(View view){
        downloadPdf();
        Toast.makeText(this, "Downloading PDF ... ", Toast.LENGTH_SHORT).show();
    }


    private void downloadPdf() {
        String pdfUrl = "https://firebasestorage.googleapis.com/v0/b/buspass-af.appspot.com/o/busspassFare.pdf?alt=media&token=b0b8ef8b-b11a-47f0-9d08-3f0c9c4c27a0";
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(pdfUrl));
        request.setTitle("Bus Pass fare");
        request.setDescription("Downloading");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "my.pdf");
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }


}

