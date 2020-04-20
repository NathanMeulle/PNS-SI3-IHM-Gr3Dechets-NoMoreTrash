package com.example.nomoretrash.signalements;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Address;
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

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.nomoretrash.R;

import org.osmdroid.api.IMapController;

import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LocalisationFragment extends Fragment {

    private MapView map;
    private SignalementObject signalementObject;
    private ImageButton boutonMaPosition;
    private static final int PERMISSION_CODE = 1000;
    private int etat = 0;
    private String coordonnees;
    private Double latitude;
    private Double longitude;
    private LocationManager locationManager = null;
    private LocationListener locationListener;
    private Double latitudeDechet;
    private Double longitudeDechet;
    private Marker marker;
    private int nbMarker = 0;



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

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                coordonnees = String.format("Latitude : %f - Longitude : %f\n", latitude, longitude);
                Log.d("GPS", coordonnees);
                GeoPoint nouvelleLocalisation = new GeoPoint(location);
                map.getController().animateTo(nouvelleLocalisation);
                map.getController().setCenter(new GeoPoint(latitude, longitude));
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                if (etat != status) {
                    switch (status) {
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
        try {
            boutonMaPosition.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ContextCompat.checkSelfPermission(LocalisationFragment.this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
                            //Permission non accordée, on demande de nouveau la permission
                            String[] permission = {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.WRITE_EXTERNAL_STORAGE};
                            //POP UP
                            requestPermissions(permission, PERMISSION_CODE);//On demande l'accès au GPS
                        } else {
                            //permission ok
                        }
                        initialiserLocalisation("", locationListener);
                        addItemMyPosition();//Ajoute ma position
                    }
                }
            });
        } catch (Exception e ) {
            Toast.makeText(getContext(),  "Une erreur est survenue...", Toast.LENGTH_SHORT).show();
        }

        // Ajout d'un MapEventsReceiver pour detecter les clics de l'utilisateur
        onSingleTapUpHelper();



        return rootView;
    }

    private void addItemMyPosition() throws NullPointerException {
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
        map.getController().setZoom(18.0);
    }



    public  void addItemTrash(GeoPoint location){
        if(nbMarker==0) { // si aucun marker n'est créé, on le crée
            marker = new Marker(map);
            marker.setPosition(new GeoPoint(location.getLatitude(), location.getLongitude()));
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            marker.setIcon(getResources().getDrawable(R.drawable.trash));
            marker.setTitle("Position du dechet");
            map.getOverlays().add(marker);
            map.invalidate();
            signalementObject.setHaveLocalisation(true);
        }else { // sinon on le recupère et on change sa position
            map.getOverlays().remove(marker);
            marker = new Marker(map);
            marker.setPosition(new GeoPoint(location.getLatitude(), location.getLongitude()));
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            marker.setIcon(getResources().getDrawable(R.drawable.trash));
            marker.setTitle("Trash");
            map.getOverlays().add(marker);
            map.invalidate();

        }
        nbMarker++;
    }


    public void onSingleTapUpHelper() {
        MapEventsReceiver mReceive = new MapEventsReceiver() {
            @Override
            public boolean singleTapConfirmedHelper(GeoPoint p) {
                addItemTrash(p);// on ajoute un marker
                signalementObject.setLatitude(p.getLatitude()); // on enregistre ses coordonnées
                signalementObject.setLongitude(p.getLongitude());
                try {
                    signalementObject.setLocalisation(localisationToString(p.getLatitude(), p.getLongitude()));
                } catch (IOException e) {
                    e.printStackTrace();
                    signalementObject.setLocalisation(toStringLocalisation(p.getLatitude(), p.getLongitude()));
                }
                return false;
            }

            @Override
            public boolean longPressHelper(GeoPoint p) {
                return false;
            }
        };

        MapEventsOverlay OverlayEvents = new MapEventsOverlay(getActivity().getBaseContext(), mReceive);
        map.getOverlays().add(OverlayEvents);

    }

    private void initialiserLocalisation(String fournisseur, LocationListener ecouteurGPS) {
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
            
        }
    }

    private void arreterLocalisation() {
        if(locationManager != null) {
            locationManager.removeUpdates(locationListener);
            locationListener = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        arreterLocalisation();
    }

    public String localisationToString(double latitude, double longitude) throws IOException {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this.getContext(), Locale.getDefault());
        addresses = geocoder.getFromLocation(latitude, longitude, 1);

        String address = addresses.get(0).getAddressLine(0);
        return "Localisation : " + address;
    }

    public String toStringLocalisation(double latitude, double longitude){
        return "Localisation : " + latitude +", " + longitude ;
    }

}
