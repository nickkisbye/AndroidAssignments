package com.example.recycleviewproject.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleviewproject.Models.Note;
import com.example.recycleviewproject.R;
import com.example.recycleviewproject.View.ViewHolder;
import java.util.List;

/**
 * Binds the fields to the view
 */
public class MyRecycleViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Note> list;

    public MyRecycleViewAdapter(List<Note> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout linearLayout =
            (LinearLayout) LayoutInflater.from(parent.getContext())
            .inflate(R.layout.customrow, parent, false);
        return new ViewHolder(linearLayout);
    }

    /**
     * This method binds the ViewHolder.java elements to the view, so that you can see the values.
     * We append Note objects to the list in MainActivity.java with the properties, and loop through them in this method
     * with the current "position" in the list.
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        holder.textView.setText(list.get(position).getText());
        holder.imageView.setImageResource(list.get(position).getPicture());
        holder.button.setText(list.get(position).getButtonText());

        holder.button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                holder.button.setBackgroundResource(R.color.colorPrimary);
                holder.button.setText("Liked!");
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
