package com.cubomania.Fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.cubomania.Cube.Cube;
import com.cubomania.Image.ImageLoader;
import com.cubomania.R;
import com.cubomania.Service.ServiceCubes;

import java.util.Iterator;
import java.util.List;

public class MainActivityFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    public static final String SELECT_SIZE = "Selecione um tamanho";
    public static final String SELECT_TYPE = "Selecione um tipo";
    private static final String SELECT_DIFICULTY = "Selecione uma dificuldade";
    private ServiceCubes serviceCubes;
    private TextView tvPrice, tvName, tvSize, tvType, tvDificulty;
    private ImageView ivCube;
    private FloatingActionButton btNext,btPrevious,btSearch;
    private List<Cube> cubeList;
    private Cube currentCube;
    private int currentPosition = 0;
    private ImageLoader imageLoader;
    private Spinner spinnerSize,spinnerType,spinnerDificulty;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        serviceCubes = new ServiceCubes(this);
        imageLoader = new ImageLoader(this.getContext());

        spinnerSize = createSpinner(view,R.id.cube_size,R.array.cube_size);
        spinnerType = createSpinner(view, R.id.cube_type, R.array.cube_type);
        spinnerDificulty = createSpinner(view,R.id.cube_dificulty,R.array.cube_dificulty);

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

        btNext.setOnClickListener( (View v) -> nextCube() );
        btPrevious.setOnClickListener( (View v) -> previousCube() );
        btSearch.setOnClickListener( (View v) -> serviceCubes.getAll() );

        return view;
    }

    private Spinner createSpinner(View view, int spinnerId, int cubeList) {
        Spinner spinner = (Spinner) view.findViewById(spinnerId);
        ArrayAdapter<CharSequence> items = ArrayAdapter.createFromResource(view.getContext(),cubeList,android.R.layout.simple_spinner_item);
        items.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(items);
        return spinner;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void updateCubes(List<Cube> cubeList) {
        sortListBySpinners(cubeList);
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
        }else{
            currentCube = null;
            tvName.setText(R.string.no_cubes);
            tvSize.setText("");
            tvType.setText("");
            tvDificulty.setText("");
            tvPrice.setText("");
            ivCube.setImageResource(R.drawable.black_cube_init);
        }

        checkIfEnableNext();
        checkIfEnablePrevious();
    }

    private void sortListBySpinners(List<Cube> cubeList) {
        String size = spinnerSize.getSelectedItem().toString();
        String type = spinnerType.getSelectedItem().toString();
        String dificulty = spinnerDificulty.getSelectedItem().toString();

        removeFromList(size,cubeList,SELECT_SIZE);
        removeFromList(type,cubeList,SELECT_TYPE);
        removeFromList(dificulty,cubeList,SELECT_DIFICULTY);
    }

    private void removeFromList(String filter, List<Cube> listCubes, String select){
        if (!filter.equals(select)){
            for (Iterator<Cube> it = listCubes.iterator(); it.hasNext();) {
                Cube cube = it.next();
                if ((!cube.getTamanho().equals(filter) && select.equals(SELECT_SIZE)) ||
                        (!cube.getTipo().equals(filter) && select.equals(SELECT_TYPE) ) ||
                        (!cube.getDificuldade().equals(filter) && select.equals(SELECT_DIFICULTY))){
                    it.remove();
                }
            }
        }
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
