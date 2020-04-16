package com.example.nomoretrash.signalements;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.nomoretrash.R;

import org.osmdroid.api.IMapController;
import org.osmdroid.events.MapListener;
import org.osmdroid.events.ScrollEvent;
import org.osmdroid.events.ZoomEvent;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;
import java.util.Locale;

public class LocalisationFragment extends Fragment {

    private MapView map;
    private SignalementObject signalementObject;
    ImageButton boutonMaPosition;
    private static final int PERMISSION_CODE = 1000;
    private int etat;
    LocationListener ecouteurGPS;
    LocationManager locationManager = null;
    private String fournisseur;



    public LocalisationFragment() {
        //on récupère l'objet signalement
        this.signalementObject = DescriptionFragment.getSignalementObject();
    }

    public static LocalisationFragment newInstance() {
        return (new LocalisationFragment());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.localisation_fragment, container, false);
        final IMapController mapController;

        map = rootView.findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);

        mapController = map.getController();
        mapController.setZoom(18.0);
        GeoPoint startPoint = new GeoPoint(43.181866, 5.703372);
        mapController.setCenter(startPoint);

        ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();
        OverlayItem home = new OverlayItem("Maison de Léo", "C'est là où ses parents habitent", new GeoPoint(43.189122, 5.704391));
        Drawable m = home.getMarker(0);

        items.add(home);
        ItemizedOverlayWithFocus<OverlayItem> mOverlay = new ItemizedOverlayWithFocus<OverlayItem>(this.getActivity(), items,
                new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
                    @Override
                    public boolean onItemSingleTapUp(int index, OverlayItem item) {
                        return false;
                    }

                    @Override
                    public boolean onItemLongPress(int index, OverlayItem item) {
                        return false;
                    }
                });

        mOverlay.setFocusItemsOnTap(true);
        map.getOverlays().add(mOverlay);
        boutonMaPosition = rootView.findViewById(R.id.boutonMaPosition);
        boutonMaPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(LocalisationFragment.this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
                        //Permission non accordée, on demande de nouveau la permission
                        String[] permission = {Manifest.permission.ACCESS_FINE_LOCATION};
                        //POP UP
                        requestPermissions(permission, PERMISSION_CODE);
                    } else {
                        //permission ok
                    }
                    //recentrateMap(mapController);
                }
            }
        });

        return rootView;
    }

}
