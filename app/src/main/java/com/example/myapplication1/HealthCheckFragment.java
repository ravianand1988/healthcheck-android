package com.example.myapplication1;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Camera;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication1.data.HealthItemModel;
import com.example.myapplication1.util.ConnectionDetector;
import com.example.myapplication1.util.InternetActive;
import com.example.myapplication1.util.MemoryStatus;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HealthCheckFragment extends Fragment {


    RecyclerView quotesRecyclerView;

    public HealthCheckFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_health_check, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_second1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HealthCheckFragment.this)
                        .navigate(R.id.action_HealthFragment_to_FirstFragment);
            }
        });

        Context context = getActivity().getApplicationContext();

        quotesRecyclerView = view.findViewById(R.id.recycler_health);
        HealthListAdapter quotesListAdapter =
                new HealthListAdapter(initializeHealthData(context));
        LinearLayoutManager manager = new LinearLayoutManager
                (context, LinearLayoutManager.VERTICAL, false);

        quotesRecyclerView.setLayoutManager(manager);
        quotesRecyclerView.setAdapter(quotesListAdapter);
    }

    private HealthItemModel[] initializeHealthData(Context context) {
        HealthItemModel[] healthItemModels = new HealthItemModel[4];

        int connection = new ConnectionDetector(context).getConnectionType();

        switch (connection) {
            case 0:
                healthItemModels[0] = new HealthItemModel(
                        "Internet Not Connected",
                        android.R.drawable.ic_delete
                        );
                break;
            case 1:
                healthItemModels[0] = new HealthItemModel(
                        "Cellular Internet Connected",
                        android.R.drawable.ic_input_add
                );

//                new InternetActive(context).execute();

                break;
            case 2:
                healthItemModels[0] = new HealthItemModel(
                        "WiFi Internet Connected",
                        android.R.drawable.ic_input_add
                );
//                new InternetActive(context).execute();

                break;
        }

        healthItemModels[1] = batteryLevel(context);
        healthItemModels[2] = new HealthItemModel(
                MemoryStatus.formatSize(MemoryStatus.getAvailableInternalMemorySize()),
                android.R.drawable.ic_dialog_info
        );
        healthItemModels[3] = new HealthItemModel(
                getWifiLevel(context),
                android.R.drawable.ic_lock_power_off
        );

        return  healthItemModels;
    }

    public HealthItemModel batteryLevel(Context context)
    {
        Intent intent  = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int    level   = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
        int    scale   = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 100);
        int    percent = (level*100)/scale;
        String charged = String.valueOf(percent) + "%";

        return  new HealthItemModel(charged, android.R.drawable.ic_lock_idle_low_battery);
    }

    public String getWifiLevel(Context context)
    {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        int linkSpeed = wifiManager.getConnectionInfo().getRssi();
        int level = WifiManager.calculateSignalLevel(linkSpeed, 5);
        return String.valueOf(level);
    }

    public String getMobileSpeed(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkCapabilities nc = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
//        var downSpeed = nc.getLinkDownstreamBandwidthKbps();
//        var upSpeed = nc.getLinkUpstreamBandwidthKbps();

        return "Hello";
    }

}
