package com.cubomania.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.cubomania.R;

public class CartFinished extends AppCompatActivity{

    private TextView tvTotalPrice;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_finished);

        String totalPrice = getIntent().getExtras().getString("totalPrice");

        tvTotalPrice = (TextView) findViewById(R.id.tv_total_finished);
        tvTotalPrice.setText("Total: " + totalPrice);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
