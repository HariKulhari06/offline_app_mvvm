package com.example.employee.view.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employee.R;
import com.example.employee.data.local.model.Employee;
import com.example.employee.databinding.EmployeeViewItemBinding;
import com.example.employee.view.main.viewholder.EmployeeViewHolder;

public class EmployeeAdapter extends ListAdapter<Employee, RecyclerView.ViewHolder> {
    private EmployeeAdapterListener listener;


    EmployeeAdapter() {
        super(Employee.DIFF_CALLBACK);
    }

    void setListener(EmployeeAdapterListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        EmployeeViewItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.employee_view_item, parent, false);
        return new EmployeeViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Employee employee = getItem(position);
        if (employee != null) {
            ((EmployeeViewHolder) holder).itemBinding.setEmployee(employee);
            ((EmployeeViewHolder) holder).itemBinding.getRoot().setOnClickListener(v -> listener.onItemClick(getItem(position)));
        }

    }


    public interface EmployeeAdapterListener {
        void onItemClick(Employee employee);
    }
}
