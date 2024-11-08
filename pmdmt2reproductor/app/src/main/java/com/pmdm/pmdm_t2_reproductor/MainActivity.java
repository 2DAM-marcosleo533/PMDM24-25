package com.pmdm.pmdm_t2_reproductor;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int score = 0;
    private LinearLayout xilofono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xilofono = findViewById(R.id.xilofono);

        inicializarBotones();

        Log.d("Xilófono", "Aplicación iniciada, SCORE inicializado a 0.");
        enseñarNotificacion("Aplicación iniciada. SCORE inicializado a 0.");
    }

    private void inicializarBotones() {
        Button doButton = findViewById(R.id.do_button);
        Button reButton = findViewById(R.id.re_button);
        Button miButton = findViewById(R.id.mi_button);
        Button faButton = findViewById(R.id.fa_button);
        Button solButton = findViewById(R.id.sol_button);
        Button laButton = findViewById(R.id.la_button);
        Button siButton = findViewById(R.id.si_button);
        Button doHighButton = findViewById(R.id.do_high_button);

        doButton.setOnClickListener(v -> hacerSonido(R.raw.do_sound, "DO"));
        reButton.setOnClickListener(v -> hacerSonido(R.raw.re_sound, "RE"));
        miButton.setOnClickListener(v -> hacerSonido(R.raw.mi_sound, "MI"));
        faButton.setOnClickListener(v -> hacerSonido(R.raw.fa_sound, "FA"));
        solButton.setOnClickListener(v -> hacerSonido(R.raw.sol_sound, "SOL"));
        laButton.setOnClickListener(v -> hacerSonido(R.raw.la_sound, "LA"));
        siButton.setOnClickListener(v -> hacerSonido(R.raw.si_sound, "SI"));
        doHighButton.setOnClickListener(v -> hacerSonido(R.raw.do_high_sound, "DO AGUDO"));
    }

    private void hacerSonido(int soundResourceId, String noteName) {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, soundResourceId);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(mp -> mp.release());

        score++;
        Log.d("Xilófono", "Nota " + noteName + " tocada. SCORE: " + score);
        enseñarNotificacion("Nota " + noteName + " tocada. SCORE: " + score);

        scoreNotificacion(noteName);
    }

    private void scoreNotificacion(String noteName) {

        if (score == 8) {
            Toast.makeText(this, "Esta es la frase secreta", Toast.LENGTH_SHORT).show();
            Log.d("Xilófono", "Melodía reproducida. Reiniciando SCORE.");
            enseñarNotificacion("Melodía reproducida. Reiniciando SCORE.");
            score = 0;
        }
    }

    private void enseñarNotificacion(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}