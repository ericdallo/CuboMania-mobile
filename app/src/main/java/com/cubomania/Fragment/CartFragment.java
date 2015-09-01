package com.cubomania.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cubomania.Activity.CartFinished;
import com.cubomania.Adapter.CubesAdapter;
import com.cubomania.Cube.Cube;
import com.cubomania.R;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {

    private List<Cube> cubesList;
    private ListView listView;
    private CubesAdapter cubesAdapter;
    private FloatingActionButton btRemoveAll,btCheck;
    private RelativeLayout layoutEmpty,layoutNotEmpty;
    private TextView tvTotal;
    private double totalPrice = 0.0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cart_fragment,container,false);

        layoutEmpty = (RelativeLayout) view.findViewById(R.id.layout_empty_cart);
        layoutNotEmpty = (RelativeLayout) view.findViewById(R.id.layout_not_empty_cart);


        tvTotal = (TextView) view.findViewById(R.id.tv_total);

        btRemoveAll = (FloatingActionButton) view.findViewById(R.id.bt_clear_cart);
        btCheck = (FloatingActionButton) view.findViewById(R.id.bt_check);

        btRemoveAll.setOnClickListener(v -> clearCart());
        btCheck.setOnClickListener(v -> checkCart() );

        cubesList = new ArrayList<>();
        cubesAdapter = new CubesAdapter(getActivity(),cubesList);

        listView = (ListView) view.findViewById(R.id.list_cart);
        listView.setAdapter(cubesAdapter);

        emptyCart();

        return view;
    }

    public void addCube(Cube cubeToAdd,ViewPager viewPager)  {

        boolean hasCube = false;
        for(Cube c :cubesList) {
            if(c.getNome().equals(cubeToAdd.getNome())){
                c.setQuantidade(c.getQuantidade()+1);
                hasCube = true;
            }
        }
        if (cubesList.size() == 0){
            viewPager.setCurrentItem(1);
        }
        if(!hasCube){
            cubesList.add(cubeToAdd);
        }
        totalPrice += cubeToAdd.getPreco();
        tvTotal.setText(String.valueOf(totalPrice));

        cubesAdapter.notifyDataSetChanged();
        notEmpty();


    }

    private void checkCart() {
        Intent intent = new Intent(getContext(),CartFinished.class);
        intent.putExtra("totalPrice", String.valueOf(totalPrice));
        intent.putParcelableArrayListExtra("cubeList", (ArrayList<Cube>) cubesList);
        startActivity(intent);
        clearCart();
    }

    private void clearCart() {
        cubesList.clear();
        cubesAdapter.notifyDataSetChanged();
        totalPrice = 0.0;
        emptyCart();
    }
    private void emptyCart() {
        layoutEmpty.setVisibility(View.VISIBLE);
        layoutNotEmpty.setVisibility(View.GONE);
        btCheck.hide();
        btRemoveAll.hide();
    }

    private void notEmpty() {
        layoutEmpty.setVisibility(View.GONE);
        layoutNotEmpty.setVisibility(View.VISIBLE);
        btCheck.show();
        btRemoveAll.show();
    }
}