package com.example.glen.githubapi.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.glen.githubapi.R;
import com.example.glen.githubapi.model.User;
import com.example.glen.githubapi.rest.APIClient;
import com.example.glen.githubapi.rest.GithubUserEndpoints;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {

    //region Instance Variables and Constants

    public static final String USER_NAME_KEY = "UserNameKey";

    @BindView(R.id.email) TextView mEmail;
    @BindView(R.id.name) TextView mName;
    @BindView(R.id.login) TextView mLogin;
    @BindView(R.id.followers) TextView mFollowers;
    @BindView(R.id.following) TextView mFollowing;

    @BindView(R.id.avatar) SimpleDraweeView mAvatar;

    @BindView(R.id.owned_repos) Button mOwnedRepos;

    private Unbinder mUnbinder;

    private String username;

    //endregion

    //region Lifecycle methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        username = getIntent().getExtras().getString(USER_NAME_KEY);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mUnbinder = ButterKnife.bind(this);

        mOwnedRepos.setOnClickListener(view -> {
            Intent intent = new Intent(this, ReposActivity.class);
            intent.putExtra(USER_NAME_KEY, username);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
    }

    //endregion

    private void loadData() {

        GithubUserEndpoints gue = APIClient.getClient().create(GithubUserEndpoints.class);

        Call<User> call = gue.getUser(username);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();

                runOnUiThread(() -> {

                    mLogin.setText(user.getLogin());

                    if (TextUtils.isEmpty(user.getName())) {
                        mName.setText("No name specified");
                    } else {
                        mName.setText(user.getName());
                    }

                    if (TextUtils.isEmpty(user.getEmail())) {
                        mEmail.setText("No email specified");
                    } else {
                        mEmail.setText(user.getEmail());
                    }

                    mFollowers.setText(user.getFollowers());
                    mFollowing.setText(user.getFollowing());

                    mAvatar.setImageURI(user.getAvatar());
                });

                Log.i("GHA", "onResponse: " + response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("GHA", "onFailure: ", t);
            }
        });
    }
}
