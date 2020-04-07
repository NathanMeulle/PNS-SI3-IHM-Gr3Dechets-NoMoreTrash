package com.example.nomoretrash.signalements;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.nomoretrash.R;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;

public class LocalisationFragment extends Fragment {

    private MapView map;

    public LocalisationFragment() {//vide
    }

    public static LocalisationFragment newInstance() {
        return (new LocalisationFragment());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.localisation_fragment, container, false);
        IMapController mapController;

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

        return rootView;
    }

}
