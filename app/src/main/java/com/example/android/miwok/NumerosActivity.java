package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumerosActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;

    private MediaPlayer.OnCompletionListener mOnCompletionListener
            = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPLayer){
            releaseMediaPlayer();
        }
    };

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if( focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                //Pausar o audio
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            }else if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
                //Reproduz o audio
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS){
                //Para o audio
                releaseMediaPlayer();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_palavras);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Palavra> palavras = new ArrayList<>();
        palavras.add(new Palavra("Um", "Lutti", R.drawable.number_one, R.raw.number_one));
        palavras.add(new Palavra("Dois", "Otiiko", R.drawable.number_two, R.raw.number_two));
        palavras.add(new Palavra("Três", "Tolookosu", R.drawable.number_three, R.raw.number_three));
        palavras.add(new Palavra("Quatro", "Oyyisa", R.drawable.number_four, R.raw.number_four));
        palavras.add(new Palavra("Cinco", "Massokka", R.drawable.number_five, R.raw.number_five));
        palavras.add(new Palavra("Seis", "Temmokka", R.drawable.number_six, R.raw.number_six));
        palavras.add(new Palavra("Sete", "Kenekaku", R.drawable.number_seven, R.raw.number_seven));
        palavras.add(new Palavra("Oito", "Kawinta", R.drawable.number_eight, R.raw.number_eight));
        palavras.add(new Palavra("Nove", "Wo’e", R.drawable.number_nine, R.raw.number_nine));
        palavras.add(new Palavra("Dez", "Na’aacha", R.drawable.number_ten, R.raw.number_ten));

        //Percorre o array completamente e printa no log
        //de que classe que o log vem e a mensagem que
        //deve ser exibido
        PalavraAdapter  intensAdapter = new PalavraAdapter(this, palavras, R.color.categoria_numeros);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(intensAdapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Palavra palavraClicada = palavras.get(position);
                releaseMediaPlayer();

                int resultado = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener
                , AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(resultado == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mMediaPlayer = MediaPlayer.create(NumerosActivity.this,
                            palavraClicada.getReferenciaAudio());
                    mMediaPlayer.start();

                    mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
                }
            }
        });
    }

    /**Metodo capaz de finalizar operaçoes quando o usuario deixa a aplicação*/
    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    /**
     * Libera o recurso de memoria utilizado pelo MediaPlayer.
     * Se o media player for igual a null, diz que ele pode estar tocando um audio.
     * */
    private void releaseMediaPlayer(){
        if(mMediaPlayer != null){
            mMediaPlayer.release();
            mMediaPlayer = null;

            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
