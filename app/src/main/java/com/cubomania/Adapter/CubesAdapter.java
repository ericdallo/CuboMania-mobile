package com.cubomania.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cubomania.Cube.Cube;
import com.cubomania.R;

import java.util.List;

public class CubesAdapter extends ArrayAdapter<Cube>{

    private final Context context;
    private List<Cube> cubesList;

    public CubesAdapter(Context context, List<Cube> cubesList) {
        super(context, -1,cubesList);
        this.context = context;
        this.cubesList = cubesList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.cube_list_item,parent,false);
        TextView tvCubeName = (TextView) rowView.findViewById(R.id.tv_cube_name);

        tvCubeName.setText(cubesList.get(position).getNome());

        TextView tvCubeQuantity = (TextView) rowView.findViewById(R.id.tv_quantity);
        tvCubeQuantity.setText(String.valueOf(cubesList.get(position).getQuantidade()) );

        return rowView;
    }
}
