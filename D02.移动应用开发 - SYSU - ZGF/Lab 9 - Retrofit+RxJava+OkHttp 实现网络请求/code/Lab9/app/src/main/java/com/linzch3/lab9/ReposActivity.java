package com.linzch3.lab9;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.linzch3.lab9.factory.ServiceFactory;
import com.linzch3.lab9.model.Repos;
import com.linzch3.lab9.service.GithubService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ReposActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private ListView reposListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos);

        init();
        setupListView();
    }

    private void setupListViewData(List<Repos> reposList) {
        List<Map<String, String>> repoListData = new ArrayList<>();
        for (Repos repo : reposList) {
            Map<String, String> temp = new LinkedHashMap<>();
            temp.put("name", repo.getName());
            temp.put("language", repo.getLanguage());
            temp.put("intro", repo.getDescription());
            repoListData.add(temp);
        }
        SimpleAdapter mSimpleAdapter = new SimpleAdapter(this, repoListData,
                R.layout.repos_item,
                new String[]{"name", "language", "intro"},
                new int[]{R.id.repo_name, R.id.repo_language, R.id.repo_intro});
        reposListView.setAdapter(mSimpleAdapter);
    }


    private void setupListView() {
        String userName = getIntent().getStringExtra(MainActivity.KEY_EXTRA);
        ServiceFactory.createRetrofit("https://api.github.com")
                .create(GithubService.class)
                .getRepos(userName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Repos>>() {
                    public void onCompleted() {
                        progressBar.setVisibility(View.GONE);
                        reposListView.setVisibility(View.VISIBLE);
                    }

                    public void onError(Throwable throwable) {
                        Toast.makeText(ReposActivity.this, "貌似出了点问题(O_O)?", Toast.LENGTH_SHORT).show();

                        progressBar.setVisibility(View.GONE);
                        reposListView.setVisibility(View.VISIBLE);

                        throwable.printStackTrace();
                    }

                    public void onNext(List<Repos> list) {
                        setupListViewData(list);
                    }
                });
    }

    private void init() {
        reposListView = findViewById(R.id.list_view);
        progressBar = findViewById(R.id.progress_bar2);
    }
}
