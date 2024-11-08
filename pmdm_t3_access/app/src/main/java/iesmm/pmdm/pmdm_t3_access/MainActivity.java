package iesmm.pmdm.pmdm_t3_access;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MainActivity extends AppCompatActivity {
    private final String LOGTAG = "PMDM";
    private static final String FILENAME = "accesos.dat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Prueba
        //addTextView(LocalDate.now().toString());
        //addTextView("hola mundo");
        //addTextView(LocalDateTime.now().toString());

        //Ruta fichero: /data/data/nombrepaquete/accesos.dat
        saveInFile("ENTRADA" + LocalDateTime.now().toString());
        loadFile();
    }

    private void loadFile() {
        File f = this.getFileStreamPath(FILENAME);

        if (f.exists()){
            try {
                DataInputStream stream = new DataInputStream(this.openFileInput(FILENAME));
                while(stream.available() != 0){
                    addTextView(stream.readUTF());
                    Log.i(LOGTAG, "Fichero cargado");
                }
                stream.close();
            } catch (IOException e) {
                Log.e(LOGTAG, "Error en la lectura del archivo");
            }
        }
    }

    @Override
    protected void OnDestroy(){
        saveInFile("SALIDA" + LocalDateTime.now().toString());

        super.onDestroy();

    }

    private void saveInFile(String cad) {
        try {
            DataOutputStream stream = new DataOutputStream(this.openFileOutput(FILENAME, Context.MODE_APPEND));
            stream.writeUTF(cad);
            stream.close();
        } catch (IOException e) {
            Log.e(LOGTAG, "Error en escritorio de fichero");
        }catch(Exception e){
            Log.e(LOGTAG, "Excepcion genérica");
        }

    }


    private void addTextView(String cad) {
        // 1. Localizar el layout para agregarle el componente
        LinearLayout layout = this.findViewById(R.id.container);

        // 2. Personalizar TextView
        TextView box = new TextView(this);
        box.setText(cad);

        box.setGravity(Gravity.CENTER);
        box.setPadding(10, 20, 10, 20);


        if (cad.contains("ENTRADA")) {
            box.setBackgroundColor(Color.GREEN);
        } else if (cad.contains("SALIDA")) {
            box.setBackgroundColor(Color.RED);
        } else {
            box.setBackgroundColor(Color.YELLOW);
        }

        // 3. Agregar TextView al layout
        layout.addView(box);
    }


}