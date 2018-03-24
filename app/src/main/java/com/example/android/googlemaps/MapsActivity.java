package com.example.android.googlemaps;

import android.Manifest;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


import static android.R.attr.left;
import static android.R.attr.width;
import static android.os.Build.VERSION_CODES.M;
import static com.example.android.googlemaps.R.attr.height;
import static com.example.android.googlemaps.R.id.bottom;
import static com.example.android.googlemaps.R.id.map;
import static com.example.android.googlemaps.R.layout.custom_pop;
import static com.example.android.googlemaps.R.style.DialogSlideAnim;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {


    private GoogleMap mMap;
    private final static int MY_PERMISSIONS_FINE_LOCATION = 1;
    private static final String TAG = "MapsActivity";
    private Marker marker_1;
    private Marker marker_2;
    private Marker marker_3;
    private Marker marker_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);

    }

    

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    private Bitmap getMarkerBitmapFromView(@DrawableRes int resId) {

        View customMarkerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.view_custom_marker, null);
        ImageView markerImageView = (ImageView) customMarkerView.findViewById(R.id.profile_image);
        markerImageView.setImageResource(resId);
        customMarkerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        customMarkerView.layout(0, 0, customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight());
        customMarkerView.buildDrawingCache();
        Bitmap returnedBitmap = Bitmap.createBitmap(customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN);
        Drawable drawable = customMarkerView.getBackground();
        if (drawable != null)
            drawable.draw(canvas);
        customMarkerView.draw(canvas);
        return returnedBitmap;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        // Add a marker in Sydney and move the camera
        LatLng newdelhi = new LatLng(28.612912,77.2295097);
        mMap.addMarker(new MarkerOptions().position(newdelhi).title("New Delhi"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(newdelhi));



        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            mMap.setMyLocationEnabled(true);
        } else {
            if (Build.VERSION.SDK_INT >= M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_FINE_LOCATION);
            }
        }


        Log.d(TAG, "onMapReady() called with");
        mMap = googleMap;
        MapsInitializer.initialize(this);
        addCustomMarker();


    }

    private void addCustomMarker() {
        Log.d(TAG, "addCustomMarker()");
        if (mMap == null) {
            return;
        }

        // adding a marker on map with image from  drawable
        marker_1= mMap.addMarker(new MarkerOptions()
                .position(new LatLng(28.6121064,77.362276))
                .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(R.drawable.rxadvance_logo_1_318x177)))
                .title("RxAdvance PBM. LTD., Noida, Uttar Pradesh")

        );

        marker_2= mMap.addMarker(new MarkerOptions()
                .position(new LatLng(28.586481,77.363018))
                .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(R.drawable.mithaas_sweets)))
                .title("Mithaas Sweets and Restaurants Pvt. Ltd., Noida")
        );

        marker_3= mMap.addMarker(new MarkerOptions()
                .position(new LatLng(28.6184089,77.3725947))
                .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(R.drawable.fortis)))
                .title("Fortis Hospital, Noida")
        );

        marker_4= mMap.addMarker(new MarkerOptions()
                .position(new LatLng(28.620476,77.3563564))
                .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(R.drawable.barclays)))
                .title("Barclays, Noida")
        );

        mMap.setOnMarkerClickListener(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_FINE_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        mMap.setMyLocationEnabled(true);
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"This App requires permission request to be granted", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
        }
    }

    public static Rect locateView(View v)
    {
        int[] loc_int = new int[2];
        if (v == null) return null;
        try
        {
            v.getLocationOnScreen(loc_int);
        } catch (NullPointerException npe)
        {
            //Happens when the view doesn't exist on screen anymore.
            return null;
        }
        Rect location = new Rect();
        location.left = loc_int[0];
        location.top = loc_int[1];
        location.right = location.left + v.getWidth();
        location.bottom = location.top + v.getHeight();
        return location;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (marker.equals(marker_1)) {

            AlertDialog.Builder mBuilder= new AlertDialog.Builder(MapsActivity.this);
            View mView= getLayoutInflater().inflate(custom_pop, null);
            mBuilder.setView(mView);
            AlertDialog dialog= mBuilder.create();

            dialog.getWindow().getAttributes().windowAnimations = R.style.BottomSlide;
            getWindow().setGravity(Gravity.BOTTOM);

            Window window = dialog.getWindow();
            WindowManager.LayoutParams wlp = window.getAttributes();

            wlp.gravity = Gravity.BOTTOM;
            wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            window.setAttributes(wlp);

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.FILL_PARENT;
            lp.height = 1000;
            dialog.show();
            dialog.getWindow().setAttributes(lp);


        } else if (marker.equals(marker_2)) {

            AlertDialog.Builder mBuilder= new AlertDialog.Builder(MapsActivity.this);
            View mView= getLayoutInflater().inflate(custom_pop, null);
            mBuilder.setView(mView);
            AlertDialog dialog= mBuilder.create();

            dialog.getWindow().getAttributes().windowAnimations = R.style.BottomSlide;
            getWindow().setGravity(Gravity.BOTTOM);

            Window window = dialog.getWindow();
            WindowManager.LayoutParams wlp = window.getAttributes();

            wlp.gravity = Gravity.BOTTOM;
            wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            window.setAttributes(wlp);

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.FILL_PARENT;
            lp.height = 1000;
            dialog.show();
            dialog.getWindow().setAttributes(lp);

        } else if (marker.equals(marker_3)) {


            AlertDialog.Builder mBuilder= new AlertDialog.Builder(MapsActivity.this);
            View mView= getLayoutInflater().inflate(custom_pop, null);
            mBuilder.setView(mView);
            AlertDialog dialog= mBuilder.create();

            dialog.getWindow().getAttributes().windowAnimations = R.style.BottomSlide;
            getWindow().setGravity(Gravity.BOTTOM);

            Window window = dialog.getWindow();
            WindowManager.LayoutParams wlp = window.getAttributes();

            wlp.gravity = Gravity.BOTTOM;
            wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            window.setAttributes(wlp);

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.FILL_PARENT;
            lp.height = 1000;
            dialog.show();
            dialog.getWindow().setAttributes(lp);


        } else if (marker.equals(marker_4)) {

            AlertDialog.Builder mBuilder= new AlertDialog.Builder(MapsActivity.this);
            View mView= getLayoutInflater().inflate(custom_pop, null);
            mBuilder.setView(mView);
            AlertDialog dialog= mBuilder.create();

            dialog.getWindow().getAttributes().windowAnimations = R.style.BottomSlide;
            getWindow().setGravity(Gravity.BOTTOM);

            Window window = dialog.getWindow();
            WindowManager.LayoutParams wlp = window.getAttributes();

            wlp.gravity = Gravity.BOTTOM;
            wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            window.setAttributes(wlp);

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.FILL_PARENT;
            lp.height = 1000;
            dialog.show();
            dialog.getWindow().setAttributes(lp);

        }
        return false;
    }
}
