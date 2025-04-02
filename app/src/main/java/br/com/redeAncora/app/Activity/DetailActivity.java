package br.com.redeAncora.app.Activity;

import android.os.Bundle;

import com.bumptech.glide.Glide;

import java.text.NumberFormat;

import br.com.redeAncora.app.Domain.PecasDomain;
import br.com.redeAncora.app.R;
import br.com.redeAncora.app.databinding.ActivityDetailBinding;

public class DetailActivity extends BaseActivity {
    // Responsável por vincular os elementos da interface.
    ActivityDetailBinding binding;

    // Objeto que representa a peca selecionado.
    PecasDomain object;

    private boolean isFavorited = false; // Variável para controlar o estado do favorito


    /**
     * Configura a view e chama os métodos getIntentExtra() e setVariable()
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentExtra();
        setVariable();
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
        binding.priceTxt.setText("$"+ NumberFormat.getNumberInstance().format(object.getPrice()));
        binding.ratingTxt.setText(String.valueOf(object.getRating()));

        Glide.with(DetailActivity.this)
                .load(object.getPicUrl())
                .into(binding.pic);
    }

    /**
     * Obtém o objeto PecasDomain passado como parâmetro na Intent.
     */
    private void getIntentExtra() {
        object= (PecasDomain) getIntent().getSerializableExtra("object");

    }

    private void setupFavoriteButton() {
        binding.coracaoDeFavoritar.setOnClickListener(v -> {
            isFavorited = !isFavorited; // Alterna entre favoritado e não favoritado
            updateFavoriteIcon(); // Atualiza o ícone de favorito
        });

        updateFavoriteIcon(); // Inicializa o ícone corretamente
    }

    private void updateFavoriteIcon() {
        int iconRes = isFavorited ? R.drawable.star : R.drawable.fav_icon;
        binding.coracaoDeFavoritar.setImageResource(iconRes);
    }
}