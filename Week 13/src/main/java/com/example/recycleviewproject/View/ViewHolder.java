package com.example.recycleviewproject.View;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleviewproject.R;

/**
 * Instanciates the fields to the ViewHolder, so that we can bind them in the ViewAdapter and get the properties shown on the screen.
 */
public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;
    public ImageView imageView;
    public Button button;

    /**
     * This method instanciates the views and buttons to the viewholder.
     * @param itemView
     */
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        LinearLayout linearLayout = (LinearLayout) itemView;
        textView = linearLayout.findViewById(R.id.textView1);
        imageView = linearLayout.findViewById(R.id.imageView);
        button = linearLayout.findViewById(R.id.button);
    }
}
