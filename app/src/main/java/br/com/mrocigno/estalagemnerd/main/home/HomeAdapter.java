package br.com.mrocigno.estalagemnerd.main.home;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import br.com.mrocigno.estalagemnerd.PlayerService;
import br.com.mrocigno.estalagemnerd.R;
import br.com.mrocigno.estalagemnerd.details.DetailsActivity;
import br.com.mrocigno.estalagemnerd.main.MainActivity;
import br.com.mrocigno.estalagemnerd.main.data.PodcastsModelVO;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    List<PodcastsModelVO> list;
    Context context;
    HomeAdapterCallback callback;

    public HomeAdapter(List<PodcastsModelVO> list, Context context, HomeAdapterCallback callback) {
        this.list = list;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.cel_thumb, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder viewHolder, int i) {
        viewHolder.setData(list.get(i));
        if(i == (list.size() - 1)){
            viewHolder.frmGap.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgPlay_Cellthumb;
        ImageView imgThumb_Cellthumb;
        ImageView imgSave_Cellthumb;
        CardView cardThumb_Cellthumb;
        TextView txtTitle_Cellthumb;
        TextView txtDescription_Cellthumb;
        ProgressBar pgrBar_Cellthumb;
        FrameLayout frmGap;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPlay_Cellthumb = itemView.findViewById(R.id.imgPlay_Cellthumb);
            imgThumb_Cellthumb = itemView.findViewById(R.id.imgThumb_Cellthumb);
            imgSave_Cellthumb = itemView.findViewById(R.id.imgSave_Cellthumb);
            cardThumb_Cellthumb = itemView.findViewById(R.id.cardThumb_Cellthumb);
            txtTitle_Cellthumb = itemView.findViewById(R.id.txtTitle_Cellthumb);
            txtDescription_Cellthumb = itemView.findViewById(R.id.txtDescription_Cellthumb);
            pgrBar_Cellthumb = itemView.findViewById(R.id.pgrBar_Cellthumb);
            frmGap = itemView.findViewById(R.id.frmGap);
        }

        public void setData(final PodcastsModelVO data) {

            txtTitle_Cellthumb.setText(data.getTitle());
            txtDescription_Cellthumb.setText(data.getDescription());
            Picasso.get().load(data.getThumb()).into(imgThumb_Cellthumb, new Callback() {
                @Override
                public void onSuccess() {
                    pgrBar_Cellthumb.setVisibility(View.GONE);
                }

                @Override
                public void onError(Exception e) {

                }
            });

            imgPlay_Cellthumb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onClickPlayButton(data.getDownloadLink(), data.getTitle());
                }
            });

            cardThumb_Cellthumb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context,
                            Pair.create((View)imgThumb_Cellthumb, "thumb"),
                            Pair.create((View)imgSave_Cellthumb, "save"),
                            Pair.create((View)imgPlay_Cellthumb, "playbtn"),
                            Pair.create((View) ((MainActivity) context).cardPlayer, "cardplayer"),
                            Pair.create((View)txtTitle_Cellthumb, "title"));
                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra("data", data);
                    callback.onCardClick(options, intent);
                }
            });
        }

    }

    public interface HomeAdapterCallback{
        void onClickPlayButton(String url, String title);
        void onCardClick(ActivityOptions options, Intent intent);
    }

}
