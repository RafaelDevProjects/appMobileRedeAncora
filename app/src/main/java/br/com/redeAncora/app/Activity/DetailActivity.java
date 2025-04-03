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
import br.com.redeAncora.app.Domain.PecasDomain;
import br.com.redeAncora.app.R;
import br.com.redeAncora.app.databinding.ActivityDetailBinding;

public class DetailActivity extends BaseActivity {
    // Responsável por vincular os elementos da interface.
    ActivityDetailBinding binding;
    PecasDomain object;
    private boolean isFavorited = false; // Variável para controlar o estado do favorito
    private DatabaseReference favRef;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentExtra();
        setVariable();

        // Simula um usuário fixo
        userId = "userId_1";
        setupFavoriteButton();
    }

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
    }

    /**
     * Configura o botão de favoritos, verificando no Firebase e alternando o estado.
     */
    private void setupFavoriteButton() {
        // Define um ID fixo para simular um usuário autenticado
        userId = "userId_1";

        favRef = FirebaseDatabase.getInstance().getReference("Favoritos").child(userId);

        favRef.child(object.getTitle()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    isFavorited = true;
                } else {
                    isFavorited = false;
                }
                updateFavoriteIcon();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        binding.coracaoDeFavoritar.setOnClickListener(v -> {
            isFavorited = !isFavorited;
            updateFavoriteIcon();

            if (isFavorited) {
                favRef.child(object.getTitle()).setValue(object);
            } else {
                favRef.child(object.getTitle()).removeValue();
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
