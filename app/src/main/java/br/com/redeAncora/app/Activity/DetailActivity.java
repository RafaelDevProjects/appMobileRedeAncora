package br.com.redeAncora.app.Activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

import br.com.redeAncora.app.Domain.PecasDomain;
import br.com.redeAncora.app.R;
import br.com.redeAncora.app.databinding.ActivityDetailBinding;

public class DetailActivity extends BaseActivity {
    final String SECRET_KEY = "ADICIONAR_SECRET_KEY"; //ADICIONAR A SECRET KEY AQUI
    // Responsável por vincular os elementos da interface.
    ActivityDetailBinding binding;
    PecasDomain object;
    private boolean isFavorited = false; // Variável para controlar o estado do favorito
    private DatabaseReference favRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentExtra();
        setVariable();

        setupFavoriteButton();
    }

    private String pecaId;
    /**
     * Preenche os elementos da tela com os dados do objeto PecasDomain e carrega a imagem usando Glide.
     */
    private void setVariable() {
        binding.backBtn.setOnClickListener(v -> finish());

        binding.titleTxt.setText(object.getTitle());
        binding.descriptionTxt.setText(object.getDescription());
        binding.totalCapacityTxt.setText(object.getDetalhes());
        binding.highestSpeedTxt.setText(object.getHighestSpeed());
        binding.engineOutputTxt.setText(object.getMarca());
        binding.priceTxt.setText("$" + NumberFormat.getNumberInstance().format(object.getPrice()));
        binding.ratingTxt.setText(String.valueOf(object.getRating()));

        Glide.with(DetailActivity.this)
                .load(object.getPicUrl())
                .into(binding.pic);
    }

    /**
     * Obtém o objeto PecasDomain passado como parâmetro na Intent.
     */
    private void getIntentExtra() {
        object = (PecasDomain) getIntent().getSerializableExtra("object");
        pecaId = getIntent().getStringExtra("pecaId");
    }

    /**
     * Configura o botão de favoritos, verificando no Firebase e alternando o estado.
     */
    private void setupFavoriteButton() {
        DatabaseReference pecasRef = FirebaseDatabase.getInstance().getReference("Pecas");

        pecasRef.orderByChild("title").equalTo(object.getTitle())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot item : snapshot.getChildren()) {
                                Boolean isFav = item.child("isFavorito").getValue(Boolean.class);
                                if (isFav != null) {
                                    isFavorited = isFav;
                                    updateFavoriteIcon();
                                }

                                binding.coracaoDeFavoritar.setOnClickListener(v -> {
                                    isFavorited = !isFavorited;

                                    Map<String, Object> updates = new HashMap<>();
                                    updates.put("isFavorito", isFavorited);
                                    updates.put("apiKey", SECRET_KEY);

                                    item.getRef().updateChildren(updates, (error, ref) -> {
                                        if (error == null) {
                                            // Remove a chave apiKey após salvar com sucesso
                                            ref.child("apiKey").removeValue();
                                        } else {
                                            Log.e("Firebase", "Erro ao atualizar favorito: " + error.getMessage());
                                        }
                                    });

                                    updateFavoriteIcon();
                                });
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e("Firebase", "Erro ao buscar peça: " + error.getMessage());
                    }
                });
    }

    /**
     * Atualiza o ícone do botão de favorito de acordo com o estado atual.
     */
    private void updateFavoriteIcon() {
        int iconRes = isFavorited ? R.drawable.star : R.drawable.fav_icon;
        binding.coracaoDeFavoritar.setImageResource(iconRes);
    }
}
