package br.com.redeAncora.app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import br.com.redeAncora.app.Activity.DetailActivity;
import br.com.redeAncora.app.Domain.PecasDomain;
import br.com.redeAncora.app.databinding.ViewholderPecasBinding;

/**
 * Adaptador para exibir uma lista de pecas em um RecyclerView.
 */
public class PecasAdapter extends RecyclerView.Adapter<PecasAdapter.Viewholder> {
    ArrayList<PecasDomain> items;
    Context context;

    /**
     * Construtor que recebe a lista de carros.
     * @param items Lista de objetos CarDomain
     */
    public PecasAdapter(ArrayList<PecasDomain> items) {
        this.items = items;
    }


    /**
     * Método chamado para criar novas instâncias do ViewHolder.
     * @param parent O ViewGroup no qual a nova View será adicionada
     * @param viewType O tipo de view (não utilizado aqui)
     * @return Um novo ViewHolder
     */
    @NonNull
    @Override
    public PecasAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context= parent.getContext();
        // Inflando o layout do ViewHolder utilizando View Binding
        ViewholderPecasBinding binding = ViewholderPecasBinding.inflate(LayoutInflater.from(context), parent, false);
        return new Viewholder(binding);
    }


    /**
     * Método chamado para exibir os dados na posição especificada.
     * @param holder O ViewHolder que deve ser atualizado
     * @param position A posição do item na lista
     */
    @Override
    public void onBindViewHolder(@NonNull PecasAdapter.Viewholder holder, int position) {
        // Definindo os valores dos elementos da interface
        holder.binding.titleTxt.setText(items.get(position).getTitle());
        holder.binding.priceTxt.setText("$"+items.get(position).getPrice());

        Glide.with(context)
                .load(items.get(position).getPicUrl())
                .apply(new RequestOptions().transform(new CenterCrop()))
                .into(holder.binding.pic);

        // Define o clique para abrir a tela de detalhes
        holder.itemView.setOnClickListener(v -> {
            Intent intent= new Intent(context, DetailActivity.class);
            intent.putExtra("pecaId", items.get(position).getId());
            intent.putExtra("object", items.get(position));
            context.startActivity(intent);
        });
    }


    /**
     * Método que retorna a quantidade de itens na lista.
     * @return Número total de itens
     */
    @Override
    public int getItemCount() {
        return items.size(); // Retorna a quantidade de itens na lista
    }


    /**
     * Classe interna que representa o ViewHolder para cada item da lista.
     */
    public class Viewholder extends RecyclerView.ViewHolder {
        ViewholderPecasBinding binding;

        /**
         * Construtor do ViewHolder.
         * @param binding Ligação com os elementos da interface
         */
        public Viewholder(ViewholderPecasBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
