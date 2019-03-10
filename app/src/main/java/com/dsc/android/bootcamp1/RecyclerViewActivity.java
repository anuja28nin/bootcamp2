package com.dsc.android.bootcamp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<RecyclerViewData>userList =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);

        recyclerView = findViewById(R.id.recycler_view);
  //      createMockList();
        apicall();
         setRecyclerView();
     }
    private void apicall(){
        ApiServices apiServices = AppClient.getInstance().createService(ApiServices.class);
        Call<UserWrapper> call= apiServices.getUserList();
                call.enqueue(new Callback<UserWrapper>() {
                    @Override
                    public void onResponse(Call<UserWrapper> call, Response<UserWrapper> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null)
                                userList.addAll(response.body().getRecyclerViewData());
                        }
                    }

                    @Override
                    public void onFailure(Call<UserWrapper> call, Throwable t) {

                    }
                });
     }
   /*
    private void createMockList() {
    RecyclerViewData data;
    data = new RecyclerViewData("https://bit.ly/2NT7svr","Anuja ","7000962338");
    mockDataList.add(data);
    data = new RecyclerViewData("https://bit.ly/2NT7svr","ankita","9685970879");
    mockDataList.add(data);
    data = new RecyclerViewData("https://bit.ly/2NT7svr","shristi","79999589521");
    mockDataList.add(data);
    data = new RecyclerViewData("https://bit.ly/2NT7svr","shivani","7931962338");
    mockDataList.add(data);
    data = new RecyclerViewData("https://bit.ly/2NT7svr","shweta ","7000589521");
    mockDataList.add(data);
    }
    */
   private void setRecyclerView(){
        recyclerViewAdapter = new RecyclerViewAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.setRecyclerViewDataList(userList);
        recyclerViewAdapter.notifyDataSetChanged();
    }
}
