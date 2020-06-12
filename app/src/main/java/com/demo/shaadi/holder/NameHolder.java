package com.demo.shaadi.holder;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NameHolder {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("first")
    @Expose
    private String first;
    @SerializedName("last")
    @Expose
    private String last;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    @BindingAdapter("userName")
    public static void getUName(TextView textView, NameHolder nameHolder) {
        textView.setText(String.format("%s %s", nameHolder.getFirst(), nameHolder.getLast()));
    }
}
