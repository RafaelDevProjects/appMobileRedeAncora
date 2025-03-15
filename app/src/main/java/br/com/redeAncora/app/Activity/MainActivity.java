package br.com.redeAncora.app.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import br.com.redeAncora.app.Adapter.CarsAdapter;
import br.com.redeAncora.app.Adapter.CategoryAdapter;
import br.com.redeAncora.app.Domain.CarDomain;
import br.com.redeAncora.app.Domain.CategoryDomain;
import br.com.redeAncora.app.R;
import br.com.redeAncora.app.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initCategoryList();
        initPopularList();

    }

    private void initPopularList() {
        DatabaseReference myref = database.getReference("Cars");
        binding.progressBarPopular.setVisibility(View.VISIBLE);
        ArrayList<CarDomain> items = new ArrayList<>();

        myref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot isuue:snapshot.getChildren()){
                        items.add(isuue.getValue(CarDomain.class));
                    }
                    if(!items.isEmpty()){
                        binding.recyclerViewPopular.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                        binding.recyclerViewPopular.setAdapter(new CarsAdapter(items));
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