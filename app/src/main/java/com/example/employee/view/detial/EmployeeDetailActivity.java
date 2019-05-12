package com.example.employee.view.detial;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.employee.BR;
import com.example.employee.R;
import com.example.employee.databinding.ActivityEmployeeDetialBinding;
import com.example.employee.view.base.BaseActivity;

import javax.inject.Inject;

public class EmployeeDetailActivity extends BaseActivity<ActivityEmployeeDetialBinding, EmployeeDetailViewModel> implements EmployeDetailNavigator {

    @Inject
    EmployeeDetailViewModel viewModel;

    private ActivityEmployeeDetialBinding binding;


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_employee_detial;
    }

    @Override
    public EmployeeDetailViewModel getViewModel() {
        viewModel.loadEmployeeDetails(getIntent().getExtras().getString("id"));
        return viewModel;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.setNavigator(this);
        binding = getViewDataBinding();
        binding.setLifecycleOwner(this);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Hari");
        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

    }


}
