package com.example.searchview;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView lstNombres;
    private ArrayAdapter<String> adapter;
    private String[] nombres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar
        nombres = getResources().getStringArray(R.array.array_nombres);
        lstNombres = findViewById(R.id.lstNombres);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nombres);
        lstNombres.setAdapter(adapter);

        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    adapter.getFilter().filter(null);
                } else {
                    adapter.getFilter().filter(newText);
                }
                return true;
            }
        });

        lstNombres.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String alumnoSeleccionado = adapter.getItem(position);
                Toast.makeText(MainActivity.this, "Alumno seleccionado: " + alumnoSeleccionado, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
