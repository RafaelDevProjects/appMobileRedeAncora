package br.com.redeAncora.app.Activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import br.com.redeAncora.app.Adapter.CategoryAdapter;
import br.com.redeAncora.app.Adapter.PecasAdapter;
import br.com.redeAncora.app.Domain.CategoryDomain;
import br.com.redeAncora.app.Domain.PecasDomain;
import br.com.redeAncora.app.R;
import br.com.redeAncora.app.databinding.ActivityFavoriteBinding;
import br.com.redeAncora.app.databinding.ActivityMainBinding;


public class ActivityFavorite extends BaseActivity {
    // Responsável por vincular os elementos da interface.
    ActivityFavoriteBinding binding;

    private ArrayList<PecasDomain> allFavoriteItems = new ArrayList<>();
    private PecasAdapter adapter;

    /**
     * Configura a view e inicializa as listas de categorias e peças populares.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFavoriteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initFavoriteList();
        binding.backBtn.setOnClickListener(v -> finish());
    }


    /**
     * Busca dados de peças favoritas no Firebase e popula a RecyclerView
     */
    private void initFavoriteList() {
        DatabaseReference myRef = database.getReference("Pecas");
        binding.progressBarFavorite.setVisibility(View.VISIBLE);;
        adapter = new PecasAdapter(allFavoriteItems);
        binding.favoriteRecyclerView.setLayoutManager(new GridLayoutManager(ActivityFavorite.this, 2));
        binding.favoriteRecyclerView.setAdapter(adapter);

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    allFavoriteItems.clear(); // sempre limpe a lista antes de adicionar
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        PecasDomain peca = issue.getValue(PecasDomain.class);
                        if (peca != null && peca.getisFavorito()) {
                            peca.setId(issue.getKey());
                            allFavoriteItems.add(peca);
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
                binding.progressBarFavorite.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}