package com.example.drivetomsu;

import androidx.fragment.app.FragmentActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Automatically pre-populate Montclair State University's address
        navigateToMSU();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * In this case, we just add a marker for Montclair State University.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker for Montclair State University
        LatLng montclair = new LatLng(40.8606, -74.1975);
        mMap.addMarker(new MarkerOptions().position(montclair).title("Montclair State University"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(montclair, 15));
    }

    private void navigateToMSU() {
        // Get the URI for Montclair State University
        String address = "Montclair State University, Montclair, NJ";
        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + address);

        // Create an Intent to launch Google Maps with the navigation URI
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");

        // Verify if there's an app to handle the intent
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            // Start the intent
            startActivity(mapIntent);
        } else {
            // If there's no app to handle the intent, show a dialog box error
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Google Maps is not installed. Please install a map app to navigate.")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
    }
}
