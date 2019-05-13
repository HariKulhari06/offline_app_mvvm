package com.example.employee.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employee.R;
import com.example.employee.data.local.model.Employee;
import com.example.employee.databinding.ActivityMainBinding;
import com.example.employee.view.base.BaseActivity;
import com.example.employee.view.detial.EmployeeDetailActivity;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements MainNavigator, EmployeeAdapter.EmployeeAdapterListener {
    @Inject
    MainViewModel viewModel;

    @Inject
    LinearLayoutManager layoutManager;

    @Inject
    EmployeeAdapter adapter;

    private ActivityMainBinding binding;

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
        setSupportActionBar(binding.toolbar);

        viewModel.setNavigator(this);
        initViews();
    }

    private void initViews() {

        binding.setLifecycleOwner(this);

        layoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.contentMain.recyclerView.setLayoutManager(layoutManager);
        binding.contentMain.recyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.contentMain.recyclerView.setAdapter(adapter);
        adapter.setListener(this);

        startListening();

        viewModel.setUpSearchObservable(this, MainActivity.this, binding.searchView);

        binding.contentMain.swipeRefreshLayout.setOnRefreshListener(() -> viewModel.refresh());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu_main, menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void startListening() {
        viewModel.getListLiveData().observe(this, employees -> adapter.submitList(employees));
    }

    @Override
    public void setRefresh(boolean refresh) {
        binding.contentMain.swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onItemClick(Employee employee) {
        Intent intent = new Intent(this, EmployeeDetailActivity.class);
        intent.putExtra("emp", employee);
        startActivity(intent);
    }
}
