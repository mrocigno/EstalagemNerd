package br.com.mrocigno.estalagemnerd.details;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.mrocigno.estalagemnerd.R;
import br.com.mrocigno.estalagemnerd.config.AbstractActivity;
import br.com.mrocigno.estalagemnerd.main.data.PodcastsModelVO;

public class DetailsActivity extends AbstractActivity {

    ImageView imgThumb;
    TextView txtTitle;
    TextView txtDescription;
    Button btnPlayButton;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_details;
    }

    @Override
    public void customCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        showBNV(false);
        if(intent.hasExtra("data")){
            final PodcastsModelVO data = (PodcastsModelVO) intent.getSerializableExtra("data");
            Picasso.get().load(data.getThumb()).into(imgThumb);
            txtTitle.setText(data.getTitle());
            txtDescription.setText(data.getDescription());

            btnPlayButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playerService.prepareSong(data.getDownloadLink(), data.getTitle());
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void initVars() {
        imgThumb = findViewById(R.id.imgThumb);
        txtTitle = findViewById(R.id.txtTitle);
        txtDescription = findViewById(R.id.txtDescription);
        btnPlayButton = findViewById(R.id.btnPlayButton);

    }
}
