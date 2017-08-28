package com.example.glen.githubapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class UserActivity extends AppCompatActivity {

    public static final String USER_NAME_KEY = "UserNameKey";

    @BindView(R.id.username) TextView mUsername;
    @BindView(R.id.email) TextView mEmail;
    @BindView(R.id.followers) TextView mFollowers;
    @BindView(R.id.following) TextView mFollowing;

    @BindView(R.id.avatar) ImageView mAvatar;

    @BindView(R.id.owned_repos) Button mOwnedRepos;

    private Unbinder mUnbinder;

    private String username;

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

        mUsername.setText(username);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
    }
}
