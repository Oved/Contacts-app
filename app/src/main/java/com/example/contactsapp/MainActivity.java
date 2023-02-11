package com.example.contactsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.contactsapp.databinding.ActivityMainBinding;
import com.example.contactsapp.interfaces.iView;

public class MainActivity extends AppCompatActivity implements iView {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}