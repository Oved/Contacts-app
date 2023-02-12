package com.example.contactsapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.contactsapp.R;
import com.example.contactsapp.databinding.SplashBinding;

public class SplashActivity extends AppCompatActivity {

    private SplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = SplashBinding.inflate(getLayoutInflater());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(binding.getRoot());

        Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.move_up);
        Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.move_bottom);

        binding.ivSplash.setAnimation(animation2);
        binding.tvSplash2.setAnimation(animation2);
        binding.image.setAnimation(animation1);

        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }, 3000);
    }
}