package com.lemzeeyyy.countries;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lemzeeyyy.countries.adapter.CountryAdapter;
import com.lemzeeyyy.countries.model.CountryModel;
import com.lemzeeyyy.countries.model.Result;
import com.lemzeeyyy.countries.service.GetCountryDataService;
import com.lemzeeyyy.countries.service.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ArrayList<CountryModel> countries;
    private RecyclerView recyclerView;
    private CountryAdapter countryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getCountries();
    }

    public Object getCountries() {
        GetCountryDataService getCountryDataService = RetrofitInstance.getService();
        Call<Result> call = getCountryDataService.getResult();
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();
                if(result!=null && result.getResult() != null){
                    countries = (ArrayList<CountryModel>) result.getResult();
//                    for (CountryModel c :
//                            countries) {
//                        Log.d("TAG", "onResponse: "+c.getName());
//                    }
                    viewData();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
        return countries;
    }

    private void viewData() {
        recyclerView = findViewById(R.id.recycler_view);
        countryAdapter = new CountryAdapter(countries);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(countryAdapter);
    }
}