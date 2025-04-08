package br.com.redeAncora.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import br.com.redeAncora.app.Adapter.PecasAdapter;
import br.com.redeAncora.app.Adapter.CategoryAdapter;
import br.com.redeAncora.app.Domain.PecasDomain;
import br.com.redeAncora.app.Domain.CategoryDomain;
import br.com.redeAncora.app.R;
import br.com.redeAncora.app.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {
    // Responsável por vincular os elementos da interface.
    ActivityMainBinding binding;

    private ArrayList<PecasDomain> allItems = new ArrayList<>();
    private ArrayList<PecasDomain> filteredItems = new ArrayList<>();
    private PecasAdapter adapter;

    /**
     * Configura a view e inicializa as listas de categorias e peças populares.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initCategoryList();
        initPopularList();
        bottomNavigation();

    }


    /**
     * Configura a navegação para a ProfileActivity.
     */
    private void bottomNavigation() {
        binding.profileBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ProfileActivity.class)));
        binding.favoriteIcon.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ActivityFavorite.class)));
    }



    /**
     * Busca dados de peças populares no Firebase e popula a RecyclerView
     */


    private void initPopularList() {
        DatabaseReference myRef = database.getReference("Pecas");
        binding.progressBarPopular.setVisibility(View.VISIBLE);

        adapter = new PecasAdapter(filteredItems);
        binding.recyclerViewPopular.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        binding.recyclerViewPopular.setAdapter(adapter);

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        PecasDomain peca = issue.getValue(PecasDomain.class);
                        peca.setId(issue.getKey());
                        allItems.add(peca);
                    }
                    filteredItems.addAll(allItems);
                    adapter.notifyDataSetChanged();
                }
                binding.progressBarPopular.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        // Listener para o campo de busca
        binding.editTextText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterItemsBySearch(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    /**
     * Filtra os itens populares pela categoria clicada.
     */
    private void filterItemsByCategory(String category) {
        filteredItems.clear();

        // Se a categoria for "Todos", mostrar todas as peças
        if (category.equals("Todos")) {
            filteredItems.addAll(allItems);
        } else {
            for (PecasDomain peca : allItems) {
                if (peca.getCategory().equals(category)) {
                    filteredItems.add(peca);
                }
            }
        }

        adapter.notifyDataSetChanged();
    }

    /**
     * Filtra os itens populares pela busca do usuário.
     */
    private void filterItemsBySearch(String query) {
        filteredItems.clear();
        for (PecasDomain peca : allItems) {
            if (peca.getTitle().toLowerCase().contains(query.toLowerCase())) {
                filteredItems.add(peca);
            }
        }
        adapter.notifyDataSetChanged();
    }


    /**
     *  Busca dados das categorias no Firebase e popula a RecyclerView correspondente.
     */
    private void initCategoryList() {
        DatabaseReference myref = database.getReference("Category");
        binding.progressBarCategory.setVisibility(View.VISIBLE);

        ArrayList<CategoryDomain> items = new ArrayList<>();
        myref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        items.add(issue.getValue(CategoryDomain.class));
                    }
                    if (!items.isEmpty()) {
                        binding.recyclerViewCategory.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));

                        // Configura o adapter e adiciona o listener de clique
                        CategoryAdapter categoryAdapter = new CategoryAdapter(items, MainActivity.this::filterItemsByCategory);
                        binding.recyclerViewCategory.setAdapter(categoryAdapter);
                        binding.recyclerViewCategory.setNestedScrollingEnabled(true);
                    }
                    binding.progressBarCategory.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}