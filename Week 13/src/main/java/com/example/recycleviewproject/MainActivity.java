package com.example.recycleviewproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.recycleviewproject.Adapter.MyRecycleViewAdapter;
import com.example.recycleviewproject.Models.Note;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private int getCorrectImage(int i) {

        int[] imageArray = {
          R.drawable.image0,
          R.drawable.image1,
          R.drawable.image2,
          R.drawable.image3,
          R.drawable.image4,
          R.drawable.image5,
          R.drawable.image6,
          R.drawable.image7,
          R.drawable.image8,
          R.drawable.image9
        };
        return imageArray[i];
    }

    private List<Note> getList() {
        List<Note> list = new ArrayList<>();

        for(int i = 0; i<5;i++) {
            Note note = new Note();
            note.setButtonText("Like");
            note.setText("Note " + (i + 1));
            note.setPicture(getCorrectImage(i));
            list.add(note);
        }
        return list;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycleView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new MyRecycleViewAdapter(getList()));
    }
}
