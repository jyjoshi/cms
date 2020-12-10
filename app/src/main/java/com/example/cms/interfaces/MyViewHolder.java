package com.example.cms.interfaces;

import android.content.Intent;

import androidx.annotation.Nullable;

public interface MyViewHolder {
    void onActivityResult(int requestCode, int resultCode, @Nullable Intent data);
}
