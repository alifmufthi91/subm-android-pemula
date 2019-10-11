package com.example.rickmortykarakter.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rickmortykarakter.R;
import com.example.rickmortykarakter.model.Karakter;

public class DetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setTitle("Detail Karakter");
        ImageView gambarKarakter = findViewById(R.id.img_character);
        TextView namaKarakter = findViewById(R.id.detail_name);
        TextView statusKarakter = findViewById(R.id.detail_status);
        TextView spesiesKarakter = findViewById(R.id.detail_species);
        TextView jenisKelaminKarakter = findViewById(R.id.detail_gender);
        TextView asalKarakter = findViewById(R.id.detail_origin);
        TextView lokasiKarakter = findViewById(R.id.detail_location);

        Karakter karakter = getIntent().getParcelableExtra("karakter");
        Glide.with(this)
                .load(karakter.getGambar())
                .into(gambarKarakter);
        namaKarakter.setText(karakter.getNama());
        statusKarakter.setText(karakter.getStatus());
        spesiesKarakter.setText(karakter.getSpesies());
        jenisKelaminKarakter.setText(karakter.getGender());
        asalKarakter.setText(karakter.getOrigin());
        lokasiKarakter.setText(karakter.getLokasi());

    }
}
