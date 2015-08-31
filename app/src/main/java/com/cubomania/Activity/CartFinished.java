package com.cubomania.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.cubomania.Adapter.CubesAdapter;
import com.cubomania.Cube.Cube;
import com.cubomania.R;

import java.util.ArrayList;

public class CartFinished extends AppCompatActivity{

    private TextView tvTotalPrice;
    private CubesAdapter cubesAdapter;
    private FloatingActionButton btNew;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_finished);

        String totalPrice = getIntent().getExtras().getString("totalPrice");
        ArrayList<Cube> cubeList = getIntent().getParcelableArrayListExtra("cubeList");

        tvTotalPrice = (TextView) findViewById(R.id.tv_total_finished);
        tvTotalPrice.setText("Total: " + totalPrice);

        btNew = (FloatingActionButton) findViewById(R.id.bt_new);
        btNew.setOnClickListener(v -> finish());

        cubesAdapter = new CubesAdapter(this,cubeList);

        ListView listView = (ListView) findViewById(R.id.list_checked_cubes);
        listView.setAdapter(cubesAdapter);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
