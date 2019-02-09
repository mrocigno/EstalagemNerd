package br.com.mrocigno.estalagemnerd.details;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.mrocigno.estalagemnerd.R;
import br.com.mrocigno.estalagemnerd.config.AbstractActivity;

public class DetailsActivity extends AbstractActivity {

    ImageView imgThumb;
    TextView txtTitle;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_details;
    }

    @Override
    public void customCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        if(intent.hasExtra("title")){
            Picasso.get().load(intent.getStringExtra("thumb")).into(imgThumb);
            txtTitle.setText(intent.getStringExtra("title"));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        showBNV(false);
    }

    @Override
    public void initVars() {
        imgThumb = findViewById(R.id.imgThumb);
        txtTitle = findViewById(R.id.txtTitle);
    }
}
