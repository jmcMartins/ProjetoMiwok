package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CoresActivity extends AppCompatActivity {

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
        palavras.add(new Palavra("Vermelho", "Wetetti", R.drawable.color_red, R.raw.color_red));
        palavras.add(new Palavra("Verde", "Chokokki", R.drawable.color_green, R.raw.color_green));
        palavras.add(new Palavra("Castanho", "Takaakki", R.drawable.color_brown, R.raw.color_brown));
        palavras.add(new Palavra("Cinzento", "Topoppi", R.drawable.color_gray, R.raw.color_gray));
        palavras.add(new Palavra("Preto", "Kululli", R.drawable.color_black, R.raw.color_black));
        palavras.add(new Palavra("Branco", "Kelelli", R.drawable.color_white, R.raw.color_white));
        palavras.add(new Palavra("Amarelo Empoeirado", "Topiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        palavras.add(new Palavra("Amarelo Mostarda", "Chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));


        //Percorre o array completamente e printa no log
        //de que classe que o log vem e a mensagem que
        //deve ser exibido
        PalavraAdapter  intensAdapter = new PalavraAdapter(this, palavras, R.color.categoria_cores);

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
                    mMediaPlayer = MediaPlayer.create(CoresActivity.this,
                            palavraClicada.getReferenciaAudio());
                    mMediaPlayer.start();

                    mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
                }
            }
        });
    }

    /**Metodo capaz de finalizar operaçoes quando o usuario deixa a aplicação */
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
