package iesmm.pmdm.pmdm_t3_listview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayAdapter adaptador; //Adaptador para mantener datos en ListView
    private final String LOGTAG = "PMDM";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //cargar datos
        loadData();
    }

    private void loadData(){
        ArrayList lista = new ArrayList();

        for (int i=0; i<40; i++){
            lista.add("item" + i);

        }

        addItemsInListView(lista);
    }

    private void addItemsInListView(ArrayList datos){
        //1. Localizar listview
     ListView lista = this.findViewById(R.id.listView1);

     //2. Vincular listview al modelo de datos, a traves del adaptador
        adaptador = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_2, datos);
       lista.setAdapter(adaptador);

       //3. Asociar un evento a cada item
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "se ha pulsado", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void putItem(View view){
        //Localizar el texto del EditText
        EditText texto = this.findViewById(R.id.editText);
        if (!texto.getText().toString().isEmpty()){
            adaptador.add(texto.getText().toString());
            adaptador.sort(Comparator<String>){
                @Override
                public int compare(String cad1, String cad2){
                    return cad1.compareTo(cad2);
                }
            }
        } else {
            Toast.makeText(this, "No hay nada que insertar", Toast.LENGTH_SHORT);
        }
    }

    public void clearItems(View view){
        if (adaptador != null){
            //Vaciar adaptador de datos
            adaptador.clear();
        }
    }
}