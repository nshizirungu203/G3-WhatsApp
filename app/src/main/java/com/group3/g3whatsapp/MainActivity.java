package com.group3.g3whatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.group3.g3whatsapp.Adapters.FragmentsAdapter;
import com.group3.g3whatsapp.databinding.ActivityMainBinding;
import com.group3.g3whatsapp.databinding.ActivitySignInBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

       ListView listView = findViewById(R.id.listView);

        auth = FirebaseAuth.getInstance();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<Messages>> call = api.getMessages();

        call.enqueue(new Callback<List<Messages>>() {
            @Override
            public void onResponse(Call<List<Messages>> call, Response<List<Messages>> response) {
                List<Messages> messages = response.body();

                String [] messageDesc = new String[messages.size()];

                for (int i = 0; i <messages.size(); i++){
                    messageDesc[i] = messages.get(i).getMessage();
                }

                listView.setAdapter(
                        new ArrayAdapter<String>(
                                getApplicationContext(),
                                android.R.layout.simple_list_item_1,
                                messageDesc
                        )
                );

               /* for (Messages m: messages){
                    Log.d("message", m.getMessage());
                    Log.d("timestamp", m.getTimestamp());
                    Log.d("uId", m.getuId());
                } */

            }

            @Override
            public void onFailure(Call<List<Messages>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


        binding.viewPager.setAdapter(new FragmentsAdapter(getSupportFragmentManager()));
        binding.tablayout.setupWithViewPager(binding.viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.setting:
                Intent i = new Intent(MainActivity.this , SettingActivity.class);
                startActivity(i);
                break;

            case R.id.logout:
                auth.signOut();
                Intent intent = new Intent(MainActivity.this , SignInActivity.class);
                startActivity(intent);
                break;

            case R.id.groupChat :
                Intent intentt = new Intent(MainActivity.this , GroupChatActivity.class);
                startActivity(intentt);

                break;

        }
        return super.onOptionsItemSelected(item);
    }
}