package br.com.redeAncora.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import br.com.redeAncora.app.databinding.ActivityIntroBinding;

public class IntroActivity extends BaseActivity {
    // Responsável por vincular os elementos da interface.
    ActivityIntroBinding binding;

    /**
     * Configura a view e define a ação do botão "Start" para iniciar
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroActivity.this, MainActivity.class));
            }
        });

    }
}