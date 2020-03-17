package com.mobile.qosin.Tools;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.mobile.qosin.Adapter.SliderFotoDetail;
import com.mobile.qosin.R;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class DetailFoto extends AppCompatActivity {
    SliderView sld;
    ArrayList<String> foto_detail = new ArrayList<>();
    Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_foto);
        Intent i = getIntent();
        foto_detail = i.getStringArrayListExtra("foto");
        sld = findViewById(R.id.slider_detail_foto);
        getSliderFotoDetail();
        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    private void getSliderFotoDetail() {
        sld.setSliderAdapter(new SliderFotoDetail(getApplicationContext(), foto_detail));
        sld.startAutoCycle();
        sld.setScrollTimeInSec(6);
        sld.setIndicatorAnimation(IndicatorAnimations.WORM);
        sld.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
    }
}
