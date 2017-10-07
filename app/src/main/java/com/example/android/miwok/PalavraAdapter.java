package com.example.android.miwok;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;

/**Classe para organizar o conteudo de acordo com a necessidade de cada tela*/
public class PalavraAdapter extends ArrayAdapter<Palavra>
{
    private int mCorFundo;

    /**Contrutor da classe PalavraAdapter, mandando a Activity solicitada, o conteudo em Array e a cor desejada do fragmento*/
    public PalavraAdapter(Activity context, ArrayList<Palavra> palavras, int corFundo)
    {
        super(context, 0, palavras);
        mCorFundo = corFundo;
    }

    /**Metodo responsavel de organizar toda a tela de acordo com suas caracteristicas.
     * Colocando conteudo nas imagens, paravras e tudo que existe em determinada tela*/
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View listItemView = convertView;
        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_lista, parent, false);
        }

        Palavra palavraAtual = getItem(position);

        TextView miwok = (TextView) listItemView.findViewById(R.id.miwok);
        miwok.setText(palavraAtual.getTraducaoMiwok());

        TextView padrao = (TextView) listItemView.findViewById(R.id.padrao);
        padrao.setText(palavraAtual.getTraducaoPadrao());

        ImageView imagem = (ImageView) listItemView.findViewById(R.id.container_imagem);
        if(palavraAtual.hasImagem())
        {
            imagem.setImageResource(palavraAtual.getReferenciaImagem());
            imagem.setVisibility(View.VISIBLE);
        }else
        {
            imagem.setVisibility(View.GONE);
        }

        LinearLayout layout = (LinearLayout) listItemView.findViewById(R.id.container_global);
        layout.setBackgroundResource(mCorFundo);

        return listItemView;
    }
}
