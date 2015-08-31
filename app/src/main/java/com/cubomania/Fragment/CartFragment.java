package com.cubomania.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cubomania.Adapter.CubesAdapter;
import com.cubomania.Cube.Cube;
import com.cubomania.R;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {

    private TextView tvTitle;
    private List<Cube> cubesList;
    private ImageView ivEmptyCart;
    private ListView listView;
    private CubesAdapter cubesAdapter;
    private FloatingActionButton btRemoveAll,btCheck;
    private LinearLayout layoutTable;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cart_fragment,container,false);

        layoutTable = (LinearLayout) view.findViewById(R.id.layout_tabela);
        layoutTable.setVisibility(View.GONE);

        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        ivEmptyCart = (ImageView) view.findViewById(R.id.iv_empty_cart);

        cubesList = new ArrayList<>();
        cubesAdapter = new CubesAdapter(getActivity(),cubesList);

        listView = (ListView) view.findViewById(R.id.list_cart);
        listView.setAdapter(cubesAdapter);

        btRemoveAll = (FloatingActionButton) view.findViewById(R.id.bt_clear_cart);
        btCheck = (FloatingActionButton) view.findViewById(R.id.bt_check);

        btRemoveAll.setOnClickListener(v -> clearCart());

        hideAll();

        return view;
    }

    public void addCube(Cube cubeToAdd) {
        RelativeLayout.LayoutParams llp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        llp.setMargins(10, 0, 0, 0);
        tvTitle.setLayoutParams(llp);
        tvTitle.setText("Cubos escolhidos");
        tvTitle.setGravity(Gravity.CENTER);
        boolean hasCube = false;
        for(Cube c :cubesList) {
            if(c.getNome().equals(cubeToAdd.getNome())){
                c.setQuantidade(c.getQuantidade()+1);
                hasCube = true;
            }
        }
        if(!hasCube){
            cubesList.add(cubeToAdd);
        }

        cubesAdapter.notifyDataSetChanged();
        showAll();
    }

    private void clearCart() {
        cubesList.clear();
        cubesAdapter.notifyDataSetChanged();
        RelativeLayout.LayoutParams llp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        llp.setMargins(30, 0, 0, 0);
        tvTitle.setLayoutParams(llp);
        tvTitle.setText("Carrinho vazio");
        tvTitle.setGravity(Gravity.CENTER);

        hideAll();
    }

    private void showAll() {
        btCheck.show();
        btRemoveAll.show();
        ivEmptyCart.setVisibility(View.GONE);
        layoutTable.setVisibility(View.VISIBLE);
    }

    private void hideAll() {
        btCheck.hide();
        btRemoveAll.hide();
        layoutTable.setVisibility(View.GONE);
        ivEmptyCart.setVisibility(View.VISIBLE);
    }
}