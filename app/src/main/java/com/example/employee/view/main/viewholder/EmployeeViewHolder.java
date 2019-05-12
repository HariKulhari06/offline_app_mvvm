package com.example.employee.view.main.viewholder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employee.databinding.EmployeeViewItemBinding;

public class EmployeeViewHolder extends RecyclerView.ViewHolder {

   public EmployeeViewItemBinding itemBinding;

    public EmployeeViewHolder(@NonNull EmployeeViewItemBinding itemBinding) {
        super(itemBinding.getRoot());
        this.itemBinding=itemBinding;
    }

}
