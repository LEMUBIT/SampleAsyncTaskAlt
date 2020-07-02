package com.lemubit.lemuel.asynctaskalt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lemubit.lemuel.asynctaskalt.databinding.ActivityMainBinding;

import java.util.concurrent.CompletableFuture;

import lombok.var;

public class MainActivity extends AppCompatActivity {

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        var binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btnGreet.setOnClickListener(view -> {
            var name = binding.eTxtGreet.getText().toString();

            CompletableFuture.supplyAsync(() -> delayGreeting(name))
                    .thenAccept(s -> runOnUiThread(() -> binding.txtGreetMsg.setText(s)));

        });

        binding.btnAdd.setOnClickListener(view -> {
            count++;
            binding.txtAdd.setText(String.valueOf(count));
        });

    }

    String delayGreeting(String name) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Hello " + name;
    }

    String quickGreeting(String name) {
        return "Hello " + name;
    }


}