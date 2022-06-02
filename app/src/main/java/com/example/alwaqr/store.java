package com.example.alwaqr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.service.controls.actions.FloatAction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class store extends AppCompatActivity {
    private RecyclerView store_clothes;
    FloatingActionButton btn_f;
    ArrayList<item> items = new ArrayList<>();
    private static final String TAG = "store";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        store_clothes=findViewById(R.id.store_clothes);
        btn_f=findViewById(R.id.btn_f);
        btn_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(store.this,addItem.class);
                startActivity(i);
            }
        });
        store_clothes.setHasFixedSize(true);
        store_clothes.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.VERTICAL , false));

    }

    @Override
    protected void onResume() {
        super.onResume();
        readData();
    }

    private void readData() {
        items.clear();
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("items");
        Task<DataSnapshot> task = ref.get();
        task.addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
               Iterable<DataSnapshot> data = task.getResult().getChildren();
               for (DataSnapshot snap:data){
                   item i =snap.getValue(item.class);
                   items.add(i);

               }
                    clothesAdapter adapter=new clothesAdapter(items);
                    store_clothes.setAdapter(adapter);
                    store_clothes.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                }else{
                    String error = task.getException().getMessage();
                    Toast.makeText(store.this, "failed " +error, Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}