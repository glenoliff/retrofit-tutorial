package com.example.glen.githubapi.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.glen.githubapi.R;
import com.example.glen.githubapi.model.Repo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by glen on 8/28/17.
 */
public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RepoViewHolder> {

    private List<Repo> mRepos;

    private LayoutInflater mLayoutInflater;

    public RepoAdapter(Context ctxt, List<Repo> repos) {
        mLayoutInflater = LayoutInflater.from(ctxt);
        mRepos = repos;
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.repo_list_item, parent, false);
        return new RepoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RepoViewHolder rvh, int position) {

        Repo repo = mRepos.get(position);

        rvh.name.setText(repo.getName());
        rvh.language.setText(repo.getLanguage());
        rvh.description.setText(repo.getDescription());
    }

    @Override
    public int getItemCount() {
        return mRepos.size();
    }

    class RepoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        public TextView name;

        @BindView(R.id.language)
        public TextView language;

        @BindView(R.id.description)
        public TextView description;

        public RepoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
