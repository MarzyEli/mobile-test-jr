package com.example.testappmarzy.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.testappmarzy.R;
import com.google.android.gms.maps.MapView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyFragment extends Fragment {
    private CardView cardView;
    private CircleImageView circleImageView;
    private TextView txtName;
    private TextView txtUniversity;
    private TextView txtHoobies;
    private TextView txtHoobiesDesc;
    private TextView txtExperience;
    private TextView txtExperienceDesc;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        circleImageView = view.findViewById(R.id.myImage);
        cardView = view.findViewById(R.id.myCard);
        txtName = view.findViewById(R.id.txtName);
        txtUniversity = view.findViewById(R.id.txtUniversity);
        txtHoobies = view.findViewById(R.id.txtHoobies);
        txtHoobiesDesc = view.findViewById(R.id.txtHoobiesDesc);
        txtExperience = view.findViewById(R.id.txtWorking);
        txtExperienceDesc = view.findViewById(R.id.txtWorkingDesc);
    }
}
