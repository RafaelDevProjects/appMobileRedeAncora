package br.com.redeAncora.app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;

import br.com.redeAncora.app.Domain.CategoryDomain;
import br.com.redeAncora.app.databinding.ViewholderCategoryBinding;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.Viewholder> {
    private ArrayList<CategoryDomain> items;
    private Context context;
    public CategoryAdapter(ArrayList<CategoryDomain> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CategoryAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ViewholderCategoryBinding binding = ViewholderCategoryBinding.inflate(LayoutInflater.from(context), parent, false);
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.Viewholder holder, int position) {
        holder.binding.titleTxt.setText(items.get(position).getTitle());

        Glide.with(context)
                .load(items.get(position).getPicUrl())
                .into(holder.binding.pic);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ViewholderCategoryBinding binding;
        public Viewholder(ViewholderCategoryBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
