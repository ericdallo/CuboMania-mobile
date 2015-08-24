package com.cubomania.Fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.cubomania.Cube.Cube;
import com.cubomania.Image.ImageLoader;
import com.cubomania.R;
import com.cubomania.Service.ServiceCubes;

import java.util.List;

public class MainActivityFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private ServiceCubes serviceCubes;
    private TextView tvPrice, tvName, tvSize, tvType, tvDificulty;
    private ImageView ivCube;
    private FloatingActionButton btNext,btPrevious,btSearch;
    private List<Cube> cubeList;
    private Cube currentCube;
    private int currentPosition = 0;
    private ImageLoader imageLoader;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        serviceCubes = new ServiceCubes(this);
        imageLoader = new ImageLoader(this.getContext());

        createSpinner(view,R.id.cube_size,R.array.cube_size);
        createSpinner(view,R.id.cube_type,R.array.cube_type);
        createSpinner(view,R.id.cube_dificulty,R.array.cube_dificulty);

        tvName = (TextView) view.findViewById(R.id.cube_title);
        tvSize = (TextView) view.findViewById(R.id.res_size);
        tvType = (TextView) view.findViewById(R.id.res_type);
        tvDificulty = (TextView) view.findViewById(R.id.res_dificulty);
        tvPrice = (TextView) view.findViewById(R.id.res_price);
        ivCube = (ImageView) view.findViewById(R.id.cube_image);

        ivCube = (ImageView) view.findViewById(R.id.cube_image);

        btNext = (FloatingActionButton) view.findViewById(R.id.bt_next);
        btPrevious = (FloatingActionButton) view.findViewById(R.id.bt_previous);
        btSearch = (FloatingActionButton) view.findViewById(R.id.bt_search);

        checkIfEnableNext();
        checkIfEnablePrevious();

        btNext.setOnClickListener((View v) -> nextCube());
        btPrevious.setOnClickListener((View v) -> previousCube());
        btSearch.setOnClickListener((View v) -> serviceCubes.getAll() );

        return view;
    }

    private void createSpinner(View view, int spinnerId, int cubeList) {
        Spinner spinner = (Spinner) view.findViewById(spinnerId);
        ArrayAdapter<CharSequence> items = ArrayAdapter.createFromResource(view.getContext(),cubeList,android.R.layout.simple_spinner_item);
        items.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(items);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void updateCubes(List<Cube> cubeList) {
        this.cubeList = cubeList;
        this.serviceCubes = new ServiceCubes(this);
        currentPosition = 0;

        if(!cubeList.isEmpty()){
            currentCube = cubeList.get(currentPosition);
            tvName.setText(currentCube.getNome());
            tvSize.setText(currentCube.getTamanho());
            tvType.setText(currentCube.getTipo());
            tvDificulty.setText(currentCube.getDificuldade());
            tvPrice.setText(currentCube.getPreco()+ "");
            imageLoader.displayImage(currentCube.getImagem(), ivCube);
        }

        checkIfEnableNext();
        checkIfEnablePrevious();
    }

    private void nextCube() {
        if (currentPosition < cubeList.size() -1){
            currentCube = cubeList.get(++currentPosition);
            tvName.setText(currentCube.getNome());
            tvSize.setText(currentCube.getTamanho());
            tvType.setText(currentCube.getTipo());
            tvDificulty.setText(currentCube.getDificuldade());
            tvPrice.setText(currentCube.getPreco()+ "");
            imageLoader.displayImage(currentCube.getImagem(), ivCube);
        }
        checkIfEnableNext();
        checkIfEnablePrevious();
    }

    private void previousCube() {
        if (currentPosition > 0){
            currentCube = cubeList.get(--currentPosition);
            tvName.setText(currentCube.getNome());
            tvSize.setText(currentCube.getTamanho());
            tvType.setText(currentCube.getTipo());
            tvDificulty.setText(currentCube.getDificuldade());
            tvPrice.setText(currentCube.getPreco()+ "");
            imageLoader.displayImage(currentCube.getImagem(), ivCube);
        }
        checkIfEnableNext();
        checkIfEnablePrevious();
    }

    private void checkIfEnablePrevious() {
        if(cubeList != null && currentPosition > 0){
            btPrevious.show();
            return;
        }
        btPrevious.hide();
    }

    private void checkIfEnableNext() {
        if(cubeList != null && currentPosition < cubeList.size() - 1){
            btNext.show();
            return;
        }
        btNext.hide();
    }
}
