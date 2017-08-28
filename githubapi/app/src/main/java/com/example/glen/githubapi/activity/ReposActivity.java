package com.example.glen.githubapi.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.glen.githubapi.R;
import com.example.glen.githubapi.adapter.RepoAdapter;
import com.example.glen.githubapi.model.Repo;
import com.example.glen.githubapi.rest.APIClient;
import com.example.glen.githubapi.rest.GithubUserEndpoints;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReposActivity extends AppCompatActivity {

    //region Instance Variables

    @BindView(R.id.repos) RecyclerView mRepos;

    @BindView(R.id.login) TextView mUsername;

    private String mLogin;

    private Unbinder mUnbinder;

    //endregion

    //region Lifecycle events

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos);
        mLogin = getIntent().getExtras().getString(UserActivity.USER_NAME_KEY);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mUnbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadRepos();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    //endregion

    private void loadRepos() {

        mUsername.setText(mLogin);

        GithubUserEndpoints gue = APIClient.getClient().create(GithubUserEndpoints.class);

        Call<List<Repo>> call = gue.getUserRepos(mLogin);

        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                mRepos.setLayoutManager(new LinearLayoutManager(ReposActivity.this, LinearLayoutManager.VERTICAL, false));
                mRepos.setAdapter(new RepoAdapter(ReposActivity.this, response.body()));
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Log.e("GHA", "onFailure: ", t);
            }
        });
    }
}
