package com.andreamw96.moviecatalogue.adapter;

import android.view.View;

class ItemClickListener implements View.OnClickListener {
    // handle item clicked

    private final OnItemClickCallback onItemClickCallback;

    ItemClickListener(int position, OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }
    @Override
    public void onClick(View view) {
        onItemClickCallback.onItemClicked();
    }
    public interface OnItemClickCallback {
        void onItemClicked();
    }
}