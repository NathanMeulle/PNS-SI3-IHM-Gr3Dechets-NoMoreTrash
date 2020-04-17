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
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;
import java.util.Locale;

public class LocalisationFragment extends Fragment {

    private MapView map;
    private SignalementObject signalementObject;
    ImageButton boutonMaPosition;
    private static final int PERMISSION_CODE = 1000;
    private int etat = 0;
    String coordonnees;
    Double latitude;
    Double longitude;



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

        //Création de la map
        map = rootView.findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);

        // Ajout du controler et du point 0
        mapController = map.getController();
        mapController.setZoom(18.0);
        GeoPoint startPoint = new GeoPoint(43.181866, 5.703372);
        mapController.setCenter(startPoint);

        //Ajout d'une echelle
        ScaleBarOverlay myScaleBarOverlay = new ScaleBarOverlay(map);
        map.getOverlays().add(myScaleBarOverlay);

        //Ajout d'une boussole
        CompassOverlay mCompassOverlay = new CompassOverlay(getContext(), new InternalCompassOrientationProvider(getContext()), map);
        mCompassOverlay.enableCompass();
        map.getOverlays().add(mCompassOverlay);

        final LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                coordonnees = String.format("Latitude : %f - Longitude : %f\n", latitude, longitude);
                Log.d("GPS", coordonnees);
                map.getController().setCenter(new GeoPoint(latitude, longitude));
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                {
                    if (etat != status)
                    {
                        switch (status)
                        {
                            case LocationProvider.AVAILABLE:
                                Toast.makeText(getContext(), provider + " état disponible", Toast.LENGTH_SHORT).show();
                                break;
                            case LocationProvider.OUT_OF_SERVICE:
                                Toast.makeText(getContext(), provider + " état indisponible", Toast.LENGTH_SHORT).show();
                                break;
                            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                                Toast.makeText(getContext(), provider + " état temporairement indisponible", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                Toast.makeText(getContext(), provider + " état : " + status, Toast.LENGTH_SHORT).show();
                        }
                    }
                    etat = status;
                }
            }

            @Override
            public void onProviderEnabled(String provider) {
                Toast.makeText(getContext(), provider + " activé !", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onProviderDisabled(String provider) {
                Toast.makeText(getContext(), provider + " désactivé !", Toast.LENGTH_SHORT).show();

            }
        };



        //Création du bouton pour recentrer la carte sur soi
        boutonMaPosition = rootView.findViewById(R.id.boutonMaPosition);
        boutonMaPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(LocalisationFragment.this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
                        //Permission non accordée, on demande de nouveau la permission
                        String[] permission = {Manifest.permission.ACCESS_FINE_LOCATION};
                        //POP UP
                        requestPermissions(permission, PERMISSION_CODE);//On demande l'accès au GPS
                    } else {
                        //permission ok
                    }
                    initialiserLocalisation(null, "", locationListener);
                    addItemPosition();
                }
            }
        });



        return rootView;
    }

    private void addItemPosition() {
        // ajout d'un point
        ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();
        OverlayItem home = new OverlayItem("Vous êtes ici", "", new GeoPoint(latitude, longitude));
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
    }

    private void initialiserLocalisation(LocationManager locationManager, String fournisseur, LocationListener ecouteurGPS)
    {
        if (locationManager == null) {
            locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
            Criteria criteres = new Criteria();

            // la précision  : (ACCURACY_FINE pour une haute précision ou ACCURACY_COARSE pour une moins bonne précision)
            criteres.setAccuracy(Criteria.ACCURACY_FINE);

            // l'altitude
            criteres.setAltitudeRequired(true);

            // la direction
            criteres.setBearingRequired(true);

            // la vitesse
            criteres.setSpeedRequired(true);

            // la consommation d'énergie demandée
            criteres.setCostAllowed(true);
            //criteres.setPowerRequirement(Criteria.POWER_HIGH);
            criteres.setPowerRequirement(Criteria.POWER_MEDIUM);

            fournisseur = locationManager.getBestProvider(criteres, true);
            Log.d("GPS", "fournisseur : " + fournisseur);
        }

        if (fournisseur != null) {
            // dernière position connue
            if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Log.d("GPS", "no permissions !");
                return;
            }

            Location localisation = locationManager.getLastKnownLocation(fournisseur);
            if(localisation != null) {
                // on notifie la localisation
                ecouteurGPS.onLocationChanged(localisation);
            }

            // on configure la mise à jour automatique : au moins 10 mètres et 15 secondes
            locationManager.requestLocationUpdates(fournisseur, 15000, 10, ecouteurGPS);
        }
    }

}
