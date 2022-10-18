package co.edu.unal.reto10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public RequestQueue queue;
    private Button mConsultar;
    private ListView mListaPeriodo;
    private ArrayList<String> periodos = new ArrayList<String>();
    ArrayAdapter<String> adaptadorPeriodo;
    private ListView mListaApoyo;
    private ArrayList<String> apoyos = new ArrayList<String>();
    ArrayAdapter<String> adaptadorApoyo;
    private ListView mListaGenero;
    private ArrayList<String> generos = new ArrayList<String>();
    ArrayAdapter<String> adaptadorGenero;
    private ListView mListaFacultad;
    private ArrayList<String> facultades = new ArrayList<String>();
    ArrayAdapter<String> adaptadorFacultad;
    private ListView mListaCarrera;
    private ArrayList<String> carreras = new ArrayList<String>();
    ArrayAdapter<String> adaptadorCarrera;
    private ListView mListaMetodologia;
    private ArrayList<String> metodologias = new ArrayList<String>();
    ArrayAdapter<String> adaptadorMetodologia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);
        mConsultar = (Button) findViewById(R.id.do_query_button);
        mListaPeriodo = (ListView) findViewById(R.id.lista_periodo);
        mListaApoyo = (ListView) findViewById(R.id.lista_apoyo);
        mListaGenero = (ListView) findViewById(R.id.lista_genero);
        mListaFacultad = (ListView) findViewById(R.id.lista_facultad);
        mListaCarrera = (ListView) findViewById(R.id.lista_carrera);
        mListaMetodologia = (ListView) findViewById(R.id.lista_metodologia);
        adaptadorPeriodo = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, periodos);
        adaptadorApoyo = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, apoyos);
        adaptadorGenero = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, generos);
        adaptadorFacultad = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, facultades);
        adaptadorCarrera = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, carreras);
        adaptadorMetodologia = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, metodologias);
        mConsultar.setEnabled(true);
        mConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                periodos.clear();
                apoyos.clear();
                generos.clear();
                facultades.clear();
                carreras.clear();
                metodologias.clear();
                String url = "https://www.datos.gov.co/resource/q455-q5b5.json?$limit=57400";
                JsonArrayRequest getDataRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i = 0; i<response.length(); i++){
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                //Log.i("JUEGO: ",obj.get("id").toString() + " " +String.valueOf(obj.get("actualTurn")));
                                periodos.add(obj.get("periodo").toString());
                                apoyos.add(obj.get("nombre_del_apoyo").toString());
                                generos.add(obj.get("genero").toString());
                                facultades.add(obj.get("facultad").toString());
                                carreras.add(obj.get("programa_academico").toString());
                                metodologias.add(obj.get("metodolog_a").toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        mListaPeriodo.setAdapter(adaptadorPeriodo);
                        mListaApoyo.setAdapter(adaptadorApoyo);
                        mListaGenero.setAdapter(adaptadorGenero);
                        mListaFacultad.setAdapter(adaptadorFacultad);
                        mListaCarrera.setAdapter(adaptadorCarrera);
                        mListaMetodologia.setAdapter(adaptadorMetodologia);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("ERROR: ", error.toString());
                    }
                });
                queue.add(getDataRequest);
            }
        });
    }
}