package com.example.myapplication1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication1.util.ConnectionDetector;
import com.example.myapplication1.util.HealthHelper;

import org.w3c.dom.Text;

public class FirstFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });



        view.findViewById(R.id.button_nav_health).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        NavHostFragment.findNavController(FirstFragment.this)
                                .navigate(R.id.action_FirstFragment_to_HealthChecksFragment);
                    }
                });

        view.findViewById(R.id.button_health_check).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                    Internet Connection (public internet reachable?)
                    WiFi Status (WiFi activated, connected?)
                    App Version (current vs. newest)
                    Additional ideas:

                    Speed test (upload vs download)
                    Backend (or API GW) reachable? (BE feature might be needed for that)
                    Fee Storage / Free Memory
                    Battery Status (Health and Charge)
                 */

                String data = "An Intent is an object that provides runtime binding" +
                        " between separate components, such as two activities." +
                        " The Intent represents an app’s intent to do something." +
                        " You can use intents for a wide variety of tasks," +
                        " but in this lesson, your intent starts another activity.";

                TextView tv2 = (TextView) getView().findViewById(R.id.textView1);
                tv2.setText(data);

                Context context = getActivity().getApplicationContext();

                HealthHelper healthHelper = new HealthHelper();
                healthHelper.checkInternetAvailibility(context);

                ConnectionDetector cd = new ConnectionDetector(context);
                if(cd.isInternetAvailable()) {
                    tv2.setText("Cheers!! Device is connected to internet.");
                    Toast.makeText(context, "Internet Active", Toast.LENGTH_LONG).show();
                } else {
                    tv2.setText("Schade!! Device is not connected to internet.");
                    Toast.makeText(context, "Internet Not Active", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
