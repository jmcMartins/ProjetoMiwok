package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**Super classe de Fragmentos, capaz de organizar e criar todas os Fragments do sistema, recebendo seus devidos conteudos.
 * E executanto todos os devidos metodos necessarios pra isso*/
public abstract class Fragmento extends Fragment
{
    /**Player de musica do aplicativo*/
    protected MediaPlayer mMediaPlayer;
    /**Gerenciador de audio do aplicativo*/
    protected AudioManager mAudioManager;

    /**Array com o conteudo para o Fragmento*/
    private ArrayList<Palavra> palavras = new ArrayList<>();
    /**Cor padrao da tela no momento*/
    private String corPadrao;

    /**Evento chamado quando o audio e completado*/
    protected MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener()
    {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer)
        {
            releaseMediaPlayer();
        }
    };

    /**Evento responsavel por pausar ou liberar a memoria do telefone usada pelo player.
     * Ele faz isso quando o foco do aplicativo nao e mais no audio, ele pausa e retorna quando o foco do app voltar ao audio
     * pode tambem caso o foco nao seja mais no app limpar a memoria usada pelo audio na memoria do aparelho*/
    protected AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener()
    {
        @Override
        public void onAudioFocusChange(int focusChange)
        {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)
            {
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN)
            {
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS)
            {
                releaseMediaPlayer();
            }
        }
    };

    /**Metodo capaz de finalizar o audio quando o app e interrompido*/
    @Override
    public void onStop()
    {
        super.onStop();
    }

    /**Metodo responsavel por receber o array com o conteudo da pagina e colocar na super classe*/
    protected void mandarArray(ArrayList<Palavra> _array)
    {
        palavras = _array;
    }

    /**Metodo responsavel por receber a cor desejada na tela atual e colocar na super classe*/
    protected void mandarCor(String _corPadrao)
    {
        corPadrao = _corPadrao;
    }

    /**Metodo responsavel por liberar a memoria usada pelo player, quando o app nao solicita mais o audio*/
    protected void releaseMediaPlayer()
    {
        if (mMediaPlayer != null)
        {
            mMediaPlayer.release();
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

    /** Metodo capaz de iniciar a criacao do fragmento no momento, criando o gerenciador de audio
     * Inicia tambem a visao para a lista e tudo que for preciso naquele momento para um bom funcionamento do app
     * */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.lista_palavras, container, false);

        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        PalavraAdapter  intensAdapter;
        if(corPadrao == "numeros")
        {
            intensAdapter = new PalavraAdapter(getActivity(), palavras, R.color.categoria_numeros);
        }
        else if(corPadrao == "cores")
        {
            intensAdapter = new PalavraAdapter(getActivity(), palavras, R.color.categoria_cores);
        }
        else if(corPadrao == "frases")
        {
            intensAdapter = new PalavraAdapter(getActivity(), palavras, R.color.categoria_frases);
        }
        else{
            intensAdapter = new PalavraAdapter(getActivity(), palavras, R.color.categoria_familia);
        }

        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(intensAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                Palavra palavraClicada = palavras.get(position);
                releaseMediaPlayer();

                int resultado = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener
                        , AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (resultado == AudioManager.AUDIOFOCUS_REQUEST_GRANTED)
                {
                    mMediaPlayer = MediaPlayer.create(getActivity(), palavraClicada.getReferenciaAudio());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
        return rootView;
    }
}
