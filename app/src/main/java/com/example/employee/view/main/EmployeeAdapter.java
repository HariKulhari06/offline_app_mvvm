package com.example.employee.view.main;

import android.view.LayoutInflater;
import android.view.View;
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

    private static final int VIEW_TYPE_EMPTY = 0;
    private static final int VIEW_TYPE_LIST_ITEM = 1;

    private EmployeeAdapterListner listner;


    protected EmployeeAdapter() {
        super(Employee.DIFF_CALLBACK);
    }

    public void setListner(EmployeeAdapterListner listner) {
        this.listner = listner;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*if (viewType == VIEW_TYPE_LIST_ITEM) {
            EmployeeViewItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.employee_view_item, parent, false);
            return new EmployeeViewHolder(binding);
        } else {
            EmptyListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.employee_view_item, parent, false);
            return new EmptyViewHolder(binding);
        }*/
        EmployeeViewItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.employee_view_item, parent, false);
        return new EmployeeViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Employee employee = getItem(position);
        if (employee != null) {
            ((EmployeeViewHolder) holder).itemBinding.setEmployee(employee);
            ((EmployeeViewHolder) holder).itemBinding.getRoot().setOnClickListener(v -> listner.onItemClick(getItem(position)));
        }


    }


   /* @Override
    public int getItemViewType(int position) {
        if (getItem(position) == null) {
            return VIEW_TYPE_EMPTY;
        } else {
            return VIEW_TYPE_LIST_ITEM;
        }
    }*/


    public interface EmployeeAdapterListner {
        void onItemClick(Employee employee);
    }
}
