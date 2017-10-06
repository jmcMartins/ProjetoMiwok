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

public class FamiliaActivity extends AppCompatActivity {

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
        palavras.add(new Palavra("Pai", "әpә", R.drawable.family_father, R.raw.family_father));
        palavras.add(new Palavra("Mãe", "әṭa", R.drawable.family_mother, R.raw.family_mother));
        palavras.add(new Palavra("Filho", "Angsi", R.drawable.family_son, R.raw.family_son));
        palavras.add(new Palavra("Filha", "Tune", R.drawable.family_daughter, R.raw.family_daughter));
        palavras.add(new Palavra("Irmão mais velho", "Taachi", R.drawable.family_older_brother, R.raw.family_older_brother));
        palavras.add(new Palavra("Irmão mais novo", "Chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        palavras.add(new Palavra("Irmã mais velha", "Teṭe", R.drawable.family_older_sister, R.raw.family_older_sister));
        palavras.add(new Palavra("Irmã mais nova", "Kolliti",R.drawable.family_younger_sister, R.raw.family_younger_sister));
        palavras.add(new Palavra("Avó", "Ama", R.drawable.family_grandmother, R.raw.family_grandmother));
        palavras.add(new Palavra("Avô", "Paapa", R.drawable.family_grandfather, R.raw.family_grandfather));


        //Percorre o array completamente e printa no log
        //de que classe que o log vem e a mensagem que
        //deve ser exibido
        PalavraAdapter  intensAdapter = new PalavraAdapter(this, palavras, R.color.categoria_familia);

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
                    mMediaPlayer = MediaPlayer.create(FamiliaActivity.this,
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
