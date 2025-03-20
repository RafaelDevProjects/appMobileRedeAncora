package br.com.redeAncora.app.Activity;

import android.content.Intent;
import android.os.Bundle;
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
import br.com.redeAncora.app.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {
    // Responsável por vincular os elementos da interface.
    ActivityMainBinding binding;

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
    }


    /**
     * Busca dados de peças populares no Firebase e popula a RecyclerView
     */
    private void initPopularList() {
        DatabaseReference myref = database.getReference("Pecas");
        binding.progressBarPopular.setVisibility(View.VISIBLE);
        ArrayList<PecasDomain> items = new ArrayList<>();

        myref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot isuue:snapshot.getChildren()){
                        items.add(isuue.getValue(PecasDomain.class));
                    }
                    if(!items.isEmpty()){
                        binding.recyclerViewPopular.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                        binding.recyclerViewPopular.setAdapter(new PecasAdapter(items));
                        binding.recyclerViewPopular.setNestedScrollingEnabled(true);
                    }
                    binding.progressBarPopular.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    /**
     *  Busca dados das categorias no Firebase e popula a RecyclerView correspondente.
     */
    private void initCategoryList() {
        DatabaseReference myref=database.getReference("Category");
        binding.progressBarCategory.setVisibility(View.VISIBLE);

        ArrayList<CategoryDomain> items = new ArrayList<>();
        myref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot issue : snapshot.getChildren()){
                        items.add(issue.getValue(CategoryDomain.class));
                    }
                    if(!items.isEmpty()){
                        binding.recyclerViewCategory.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                        binding.recyclerViewCategory.setAdapter(new CategoryAdapter(items));
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