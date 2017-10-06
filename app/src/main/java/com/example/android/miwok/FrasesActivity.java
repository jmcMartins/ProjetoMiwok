package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.os.Build.VERSION_CODES.M;


public class FrasesActivity extends AppCompatActivity {

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
        palavras.add(new Palavra("Onde você vai?", "Minto wuksus", R.raw.phrase_where_are_you_going));
        palavras.add(new Palavra("Qual é o seu nome?", "Tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        palavras.add(new Palavra("Meu nome é...", "Oyaaset...", R.raw.phrase_my_name_is));
        palavras.add(new Palavra("Como você está se sentindo?", "Michәksәs?", R.raw.phrase_how_are_you_feeling));
        palavras.add(new Palavra("Eu estou me sentindo bem.", "Kuchi achit", R.raw.phrase_im_feeling_good));
        palavras.add(new Palavra("Você está vindo?", "әәnәs'aa?", R.raw.phrase_are_you_coming));
        palavras.add(new Palavra("Sim, estou chegando.", "Hәә’ әәnәm", R.raw.phrase_yes_im_coming));
        palavras.add(new Palavra("Estou chegando.", "әәnәm", R.raw.phrase_im_coming));
        palavras.add(new Palavra("Vamos.", "Yoowutis", R.raw.phrase_lets_go));
        palavras.add(new Palavra("Venha aqui.", "әnni'nem", R.raw.phrase_come_here));


        //Percorre o array completamente e printa no log
        //de que classe que o log vem e a mensagem que
        //deve ser exibido
        PalavraAdapter  intensAdapter = new PalavraAdapter(this, palavras, R.color.categoria_frases);

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
                    mMediaPlayer = MediaPlayer.create(FrasesActivity.this,
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
