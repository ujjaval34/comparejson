package com.ujjaval.comparejson.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.ujjaval.comparejson.R;
import com.ujjaval.comparejson.adapter.Adapter;
import com.ujjaval.comparejson.interfaces.FlowCallBack;
import com.ujjaval.comparejson.model.FinalList;
import com.ujjaval.comparejson.model.PostDetails;
import com.ujjaval.comparejson.model.UserDetails;
import com.ujjaval.comparejson.viewmodel.UserdeatilsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FlowCallBack {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<UserDetails> userList=new ArrayList<>();
    private List<PostDetails> postList=new ArrayList<>();
    private List<FinalList> finalLists=new ArrayList<>();
    private Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        init();

    }

    private void init() {

        recyclerView = findViewById(R.id.rv_userList);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);

        LoadUser();

    }

    private void LoadUser() {
        UserdeatilsViewModel userdeatilsViewModel=new UserdeatilsViewModel(this);
        userdeatilsViewModel.firapi();
    }

    @Override
    public void onSuccess(List<UserDetails> response) {

        userList=response;
        LoadPost();
    }



    @Override
    public void onFailed(Throwable t) {
        Toast.makeText(this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

    }

    private void LoadPost() {
        UserdeatilsViewModel userdeatilsViewModel=new UserdeatilsViewModel(this);
        userdeatilsViewModel.firPostapi();
    }

    @Override
    public void onPostSuccess(List<PostDetails> response) {
            postList=response;

            if (userList.size()>0 && postList.size()>0){
                compareList();
            }
    }

    private void compareList() {
      //  ArrayList<String> results = new ArrayList<>();

        for (UserDetails userDetails:userList){
            String title="";
            String post="";
            boolean found = false;
            for (PostDetails postDetails : postList) {
                title=postDetails.getTitle();
                post=postDetails.getBody();

                if (postDetails.getId() == userDetails.getId()) {
                    found = true;
                }
            }
            if (!found) {

                FinalList finalList=new FinalList();
                finalList.setCompanyName(userDetails.getCompany().getName());
                finalList.setTitle(title);
                finalList.setPost(post);
                finalLists.add(finalList);
            }
        }

        adapter = new Adapter(finalLists, MainActivity.this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onPostFailed(Throwable t) {
        Toast.makeText(this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

    }
}