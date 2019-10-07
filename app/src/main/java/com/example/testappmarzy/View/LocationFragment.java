package com.example.testappmarzy.View;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.testappmarzy.Data.ISSData;
import com.example.testappmarzy.Data.ISSDataService;
import com.example.testappmarzy.Model.ISSLocation;
import com.example.testappmarzy.R;
import com.example.testappmarzy.Util.DateUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Clase que implementa un mapa donde se muestra la ubicación
 * actual del ISS (International Space Station Current Location)
 */
public class LocationFragment extends Fragment implements OnMapReadyCallback {
    private MapView mapView;
    private GoogleMap googleMap;

    private Timer timerUbication;

    private ISSData dataApi;
    private ISSLocation ubication;

    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_location, container, false);
        mapView = view.findViewById(R.id.mapView);

        inicializarHiloDeActualizacion();
        initGoogleMap(savedInstanceState);

        return view;
    }

    private void initGoogleMap(Bundle savedInstanceState){
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }

        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
    }

    @Override
    public void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    /**
     * Método contiene un hilo encargado
     * de realizar la petición al API cada 15 seg
     */
    private void inicializarHiloDeActualizacion() {
        final Handler handler = new Handler();
        timerUbication= new Timer();

        TimerTask taskUbication= new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            getLocation();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("Error", e.getMessage());
                        }
                    }
                });
            }
        };

        timerUbication.schedule(taskUbication, 0, 15000);
    }

    /**
     * Método para realizar la petición al API
     */
    private void getLocation(){
        Call<ISSLocation> call = dataApi.getDataService().getData();
        call.enqueue(new Callback<ISSLocation>() {
            @Override
            public void onResponse(Call<ISSLocation> call, Response<ISSLocation> response) {
                ubication = response.body();
                setMaker(ubication);
            }

            @Override
            public void onFailure(Call<ISSLocation> call, Throwable t) {
                Log.i("Error",t.getMessage());
            }
        });
    }

    /**
     * Método que actualiza el marcador de la ubicación del ISS
     * @param location Pojo que contiene la información de la ubicación actual
     */
    private void setMaker(ISSLocation location){
        Double latitude = Double.parseDouble(location.getIss_position().getLatitude());
        Double longitude = Double.parseDouble(location.getIss_position().getLongitude());
        Long time = Long.parseLong(location.getTimestamp());
        LatLng coordinates  = new LatLng(latitude, longitude);

        googleMap.clear();
        googleMap.addMarker(new MarkerOptions()
                .position(coordinates )
                .title(DateUtil.convertTimetimestamp(time))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_nano))).showInfoWindow();
        googleMap.addCircle(new CircleOptions()
                .center(coordinates)
                .radius(10000)
                .strokeColor(Color.RED)
                .fillColor(Color.BLUE));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(coordinates));
    }
}
