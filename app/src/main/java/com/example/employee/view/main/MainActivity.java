package com.example.employee.view.main;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employee.R;
import com.example.employee.data.local.model.Employee;
import com.example.employee.databinding.ActivityMainBinding;
import com.example.employee.view.base.BaseActivity;
import com.example.employee.view.detial.EmployeeDetailActivity;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements MainNavigator, EmployeeAdapter.EmployeeAdapterListner {
    @Inject
    MainViewModel viewModel;

    @Inject
    LinearLayoutManager layoutManager;

    @Inject
    EmployeeAdapter adapter;

    private ActivityMainBinding binding;
    private SearchView searchView;

    @Override
    public int getBindingVariable() {
        return com.example.employee.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainViewModel getViewModel() {
        return viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = getViewDataBinding();
        viewModel.setNavigator(this);
        initViews();
    }

    private void initViews() {
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.contentMain.recyclerView.setLayoutManager(layoutManager);
        binding.contentMain.recyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.contentMain.recyclerView.setAdapter(adapter);
        adapter.setListner(this);

        viewModel.getListLiveData().observe(this, employees -> adapter.submitList(employees));

        binding.contentMain.swipeRefreshLayout.setOnRefreshListener(() -> viewModel.refresh());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void onItemClick(Employee employee) {
        Intent intent = new Intent(this, EmployeeDetailActivity.class);
        intent.putExtra("id", employee.id);
        startActivity(intent);
    }
}
