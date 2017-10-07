package com.example.android.miwok;

/**Classe capaz de armazenar as palavras traduzidas e na linguagem Miwok.
 * Tambem capaz de armezenar as referencias das imagens e dos audios do sistema
 * */
public class Palavra {

    private String mPalavraPadrao;
    private String mPalavraMiwok;
    private int mReferenciaImagem = SEM_IMAGEM_FORNECIDA;
    private int mReferenciaAudio;
    private static final int SEM_IMAGEM_FORNECIDA = -1;

    /**Contrutor da classe palavra, para armezenar a palavra padrao, a traducao para miwork e a referencia do audio.*/
    public Palavra (String traducaoPadrao, String traducaoMiwok,
                    int referenciaAudio)
    {
        mPalavraMiwok = traducaoMiwok;
        mPalavraPadrao = traducaoPadrao;
        mReferenciaAudio = referenciaAudio;
    }

    /**Construtor da classe palavra, para armezenar a palavra padrao, traducao para miwork, referencia de imagem
     * e a referencia do audio.
     * */
    public Palavra (String traducaoPadrao, String traducaoMiwok,
                    int referenciaImagem, int referenciaAudio)
    {
        mPalavraMiwok = traducaoMiwok;
        mPalavraPadrao = traducaoPadrao;
        mReferenciaImagem = referenciaImagem;
        mReferenciaAudio = referenciaAudio;
    }

    /**Metodo capaz de verificar se possui uma imagem*/
    public boolean hasImagem() {
        return mReferenciaImagem != SEM_IMAGEM_FORNECIDA;
    }

    /**Metodo capaz de retornar a traducao padrao da palavra*/
    public String getTraducaoPadrao(){
        return mPalavraPadrao;
    }

    /**Metodo capaz de retornar a traducao miwok da palavra*/
    public String getTraducaoMiwok() {
        return mPalavraMiwok;
    }

    /**Metodo capaz de retornar a referencia da imagem*/
    public int getReferenciaImagem() {return mReferenciaImagem;}

    /**Metodo capaz de retornar a referencia do audio*/
    public int getReferenciaAudio() {return mReferenciaAudio;}
}