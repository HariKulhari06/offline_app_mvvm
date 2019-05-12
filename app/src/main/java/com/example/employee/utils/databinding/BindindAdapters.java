package com.example.employee.utils.databinding;

import android.text.TextUtils;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.example.employee.R;

import de.hdodenhof.circleimageview.CircleImageView;

public final class BindindAdapters {

    @BindingAdapter("imageUrl")
    public static void loadImage(CircleImageView imageView, String url) {
        if (TextUtils.isEmpty(url)) {
            imageView.setImageResource(R.drawable.user_place_holder);
        }
        Glide.with(imageView.getContext()).load(url).placeholder(R.drawable.user_place_holder).into(imageView);
    }
}
