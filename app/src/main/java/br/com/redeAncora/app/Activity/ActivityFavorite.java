package br.com.redeAncora.app.Activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.com.redeAncora.app.Adapter.PecasAdapter;
import br.com.redeAncora.app.Domain.PecasDomain;
import br.com.redeAncora.app.R;

public class ActivityFavorite extends BaseActivity {
    private RecyclerView recyclerView;
    private PecasAdapter adapter;
    private List<PecasDomain> favoriteList = new ArrayList<>();
    private DatabaseReference favRef;
    private String userId = "userId_1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        recyclerView = findViewById(R.id.favoriteRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PecasAdapter((ArrayList<PecasDomain>) favoriteList);
        recyclerView.setAdapter(adapter);

        favRef = FirebaseDatabase.getInstance().getReference("Favoritos").child(userId);
        favRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                favoriteList.clear();
                for (DataSnapshot data : snapshot.getChildren()) {
                    PecasDomain peca = data.getValue(PecasDomain.class);
                    favoriteList.add(peca);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }
}