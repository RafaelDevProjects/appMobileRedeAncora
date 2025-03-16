package br.com.redeAncora.app.Activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

import java.text.NumberFormat;

import br.com.redeAncora.app.Domain.CarDomain;
import br.com.redeAncora.app.R;
import br.com.redeAncora.app.databinding.ActivityDetailBinding;
import br.com.redeAncora.app.databinding.ActivityMainBinding;

public class DetailActivity extends BaseActivity {
    ActivityDetailBinding binding;
    CarDomain object;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentExtra();
        setVariable();
    }

    private void setVariable() {
        binding.backBtn.setOnClickListener(v -> finish());

        binding.titleTxt.setText(object.getTitle());
        binding.descriptionTxt.setText(object.getDescription());
        binding.totalCapacityTxt.setText(object.getTotalCapacity());
        binding.highestSpeedTxt.setText(object.getHighestSpeed());
        binding.engineOutputTxt.setText(object.getEngineOutput());
        binding.priceTxt.setText("$"+ NumberFormat.getNumberInstance().format(object.getPrice()));
        binding.ratingTxt.setText(String.valueOf(object.getRating()));

        Glide.with(DetailActivity.this)
                .load(object.getPicUrl())
                .into(binding.pic);
    }

    private void getIntentExtra() {
        object= (CarDomain) getIntent().getSerializableExtra("object");

    }
}