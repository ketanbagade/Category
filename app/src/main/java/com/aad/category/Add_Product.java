package com.aad.category;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Add_Product extends AppCompatActivity {

    EditText productInput,PriceInput;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__product);

        productInput=findViewById(R.id.productInput);
        PriceInput =findViewById(R.id.PriceInput);
        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = productInput.getText().toString().toUpperCase();
                String price = PriceInput.getText().toString();
                if (validate_input(name,price)){
                    adding_product(name,price);
                }
            }
        });


    }

    private void adding_product(String name,String price) {

        Call<User> userCall = MainActivity.serviceApi.add_product(name,price);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(Add_Product.this, "Success" + response.message(), Toast.LENGTH_SHORT).show();
                productInput.setText("");

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(Add_Product.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                productInput.setText("");

            }
        });


    }
    private boolean validate_input(String name, String price) {
        if (name.equals("")){
            Toast.makeText(this, "Enter valid Service", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
