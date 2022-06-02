package com.example.alwaqr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addItem extends AppCompatActivity {
Button btn_create;
    Uri uri;
    ImageView create_image;
EditText clothes_code,price;
FloatingActionButton btn_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        initViews();
        btn_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,1);
            }
        });
        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItemToDatabase();
            }

            private void addItemToDatabase() {
                String code = clothes_code.getText().toString();
                String price_item = price.getText().toString();
                if (code.isEmpty()) {
                    Toast.makeText(getBaseContext(), "Invalid Book Name", Toast.LENGTH_LONG).show();
                } else if (price_item.isEmpty()) {
                    Toast.makeText(getBaseContext(), "Invalid Author Name", Toast.LENGTH_LONG).show();

                }else{
                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference ref = db.getReference("items");
                    String id = ref.push().getKey();
                    item i = new item(id,code,price_item,"");

                    ref.child(id).setValue(i).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(addItem.this, "success", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }else{
                    String error = task.getException().getMessage();
                    Toast.makeText(addItem.this, "failed " +error, Toast.LENGTH_SHORT).show();

                }

                        }
                    });
                }
            }

        });


    }
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==1

                && resultCode==RESULT_OK){

            uri=data.getData();
            create_image.setImageURI(uri);
        }
    }









    private void initViews() {
        clothes_code = findViewById(R.id.clothes_code);
        price = findViewById(R.id.price);
        btn_img = findViewById(R.id.btn_img);
        btn_create = findViewById(R.id.btn_create);

    }
}