package com.example.employee.view.main.viewholder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employee.databinding.EmployeeViewItemBinding;
import com.example.employee.databinding.EmptyListItemBinding;

public class EmptyViewHolder extends RecyclerView.ViewHolder {

    EmptyListItemBinding itemBinding;

    public EmptyViewHolder(@NonNull EmptyListItemBinding itemBinding) {
        super(itemBinding.getRoot());
        this.itemBinding=itemBinding;
    }

}
