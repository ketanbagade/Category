package com.aad.category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    EditText serviceInput;
    Button add,product;
    RecyclerView mRecyclerview;
    RecyclerView.Adapter mAdapter;
    List<ServicePojo> servicePojos;
    ArrayList<HashMap<String, String>> arrayListServise;
    RecyclerView.LayoutManager mLayoutManager;
    public static ServiceApi serviceApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    product=findViewById(R.id.product);
    product.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(MainActivity.this,Add_Product.class);
            startActivity(intent);
        }
    });



        serviceApi = RetrofitClient.getApiClient(Constant.baseUrl.BASE_URL).create(ServiceApi.class);
        mRecyclerview = findViewById(R.id.mRecyclerview);
        mLayoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerview.setLayoutManager(mLayoutManager);



        serviceInput = findViewById(R.id.serviceInput);
        add =findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = serviceInput.getText().toString().toUpperCase();
                if (validate_input(name)){
                   adding_service(name);
                }
            }
        });


        refresh_services();


    }

    private void refresh_services() {
        Call<List<ServicePojo>> userCall = MainActivity.serviceApi.get_services();
        userCall.enqueue(new Callback<List<ServicePojo>>() {
            @Override
            public void onResponse(Call<List<ServicePojo>> call, Response<List<ServicePojo>> response) {
                servicePojos = response.body();
                mAdapter = new ServiseAdepter(MainActivity.this,servicePojos);
                mRecyclerview.setAdapter(mAdapter);
                refresh_services();
            }

            @Override
            public void onFailure(Call<List<ServicePojo>> call, Throwable t) {

            }
        });

    }

    private void adding_service(String name) {
        Call<User> userCall = MainActivity.serviceApi.add_Service(name);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(MainActivity.this, "Success" + response.message(), Toast.LENGTH_SHORT).show();
                serviceInput.setText("");

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                serviceInput.setText("");

            }
        });


    }

    private boolean validate_input(String name) {
        if (name.equals("")){
            Toast.makeText(this, "Enter valid Service", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }




}
