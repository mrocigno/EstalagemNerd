package br.com.mrocigno.estalagemnerd.main.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PodcastsModelVO implements Serializable {

    String title;
    String thumb;
    String description;

    @SerializedName("download_link")
    String downloadLink;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }
}
