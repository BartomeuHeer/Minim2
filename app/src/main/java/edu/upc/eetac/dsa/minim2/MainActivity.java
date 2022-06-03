package edu.upc.eetac.dsa.minim2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    //private List<Repo> userRepos;
    private Retrofit retrofit;
    //private User user;
    private APIInterface api;
    private RecyclerView recyclerView;
    private TextView followers,following, tvName;
    private ImageView imgUser;

    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        followers = (TextView) findViewById(R.id.tbFollowers);
        following = (TextView) findViewById(R.id.tbFollowing);
        tvName = (TextView) findViewById(R.id.tvName);
        imgUser = (ImageView) findViewById(R.id.imageView);
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/users/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        sp = getSharedPreferences("UserData",MODE_PRIVATE);
        String username = sp.getString("userName", "");
        api = retrofit.create(APIInterface.class);
        getUser(username);
        getUserRepos(username);



    }

    private void setUser(User user){
        Log.d("bartoUser",user.getUserName());
        Picasso.get().load(user.getAvatarUrl()).into(imgUser);
        tvName.setText(user.getName());
        Log.d("bartoUser",user.getFollowers());
        following.setText(user.getFollowing());
        followers.setText(user.getFollowers());
    }

    private void setAdapter(List<Repo> userRepos) {
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(userRepos);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void getUser(String name){

        api.getUser(name).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    setUser(response.body());
                }

                else{
                    Log.d("dsaMal", Integer.toString(response.code()));
                    Toast.makeText(getApplicationContext(), "User not found", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("dsaFatal", t.getMessage());
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void getUserRepos(String name){
        api.getUserRepos(name).enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                if (response.isSuccessful())
                    setAdapter(response.body());
                else
                    Toast.makeText(getApplicationContext(), "No existing repos", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}