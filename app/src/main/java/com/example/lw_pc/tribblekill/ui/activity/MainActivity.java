package com.example.lw_pc.tribblekill.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lw_pc.tribblekill.R;
import com.example.lw_pc.tribblekill.ui.fragment.ListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_details);

       /* getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, ListFragment.newInstance())
                .commit();*/
    }
}