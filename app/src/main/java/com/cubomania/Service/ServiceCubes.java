package com.cubomania.Service;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.cubomania.Cube.Cube;
import com.cubomania.Fragment.CubesFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ServiceCubes extends AsyncTask<Void,Void,Void>{

    private final static String CUBE_URL = "http://ec2-52-24-4-97.us-west-2.compute.amazonaws.com:8080/CuboMania/lista.json";
    private static final String TAG_CUBES = "cubes";
    private JSONArray cubes = null;

    private ProgressDialog progressDialog;
    private CubesFragment cubesFragment;
    private List<Cube> cubesList;

    public ServiceCubes(CubesFragment cubesFragment) {
        this.cubesFragment = cubesFragment;
    }

    public void getAll() {
        execute();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(cubesFragment.getContext(),"Aguarde","Carregando lista, Por favor aguarde...");
    }

    @Override
    protected Void doInBackground(Void... params) {
        ServiceHandler sh = new ServiceHandler();

        String jsonString = sh.makeServiceCall(CUBE_URL,ServiceHandler.GET);
        Log.d("RESPONSE ==> ",jsonString);

        if (jsonString != null){
            try{
                JSONObject jsonObject = new JSONObject(jsonString);

                cubes = jsonObject.getJSONArray(TAG_CUBES);
                List<Cube> cubeList = new ArrayList<Cube>();

                for (int i = 0; i < cubes.length(); i++){
                    JSONObject c = cubes.getJSONObject(i);
                    Cube cube = new Cube();

                    cube.setId(Integer.parseInt(c.getString("id")));
                    cube.setNome(c.getString("nome"));
                    cube.setTamanho(c.getString("tamanho"));
                    cube.setTipo(c.getString("tipo"));
                    cube.setDificuldade(c.getString("dificuldade"));
                    cube.setPreco(Double.parseDouble(c.getString("preco")));
                    cube.setImagem(c.getString("imagem"));

                    cubeList.add(cube);
                }
                this.cubesList = cubeList;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {
            Log.e("ERROR RESPONSE","Json vazio");
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        cubesFragment.updateCubes(cubesList);
        progressDialog.dismiss();
    }
}
