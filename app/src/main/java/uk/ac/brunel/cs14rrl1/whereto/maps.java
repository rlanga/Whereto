package uk.ac.brunel.cs14rrl1.whereto;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class maps extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    public LatLng ords;
    public String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent i = getIntent();

        Bundle bundle = getIntent().getParcelableExtra("bundle");
        ords = bundle.getParcelable("ords");
        name = bundle.getString("name");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                .getMap();
        setUpMapIfNeeded();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    public void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(ords).title(name));
    }


//    public static final LatLng st_pauls = new LatLng(51.5138486, -0.1005393);
//    public static final LatLng westminster = new LatLng(51.4991869, -0.1296608);
//    public static final LatLng all_hallows = new LatLng(51.5093622,-0.0815313);
//    public static final LatLng st_martin = new LatLng(51.5087948,-0.1288587);
//
//    Marker church1 = mMap.addMarker(new MarkerOptions()
//            .position(st_pauls)
//            .draggable(false));
//
//    Marker church2 = mMap.addMarker(new MarkerOptions()
//            .position(westminster)
//            .draggable(false));
//
//    Marker church3 = mMap.addMarker(new MarkerOptions()
//            .position(all_hallows)
//            .draggable(false));
//
//    Marker church4 = mMap.addMarker(new MarkerOptions()
//            .position(st_martin)
//            .draggable(false));


}
