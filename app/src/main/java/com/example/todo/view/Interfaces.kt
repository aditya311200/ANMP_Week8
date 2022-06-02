package com.example.todo.view

import android.view.View
import android.widget.CompoundButton
import com.example.todo.model.Todo

interface TodoCheckedChangeListener {
    fun onCheckChanged(cb: CompoundButton, isChecked: Boolean, obj: Todo)
}

interface TodoEditClickListener {
    fun onTodoEditClick(v: View)
}

interface TodoRadioClickListener {
    fun onRadioClick(v: View, priority: Int, obj: Todo)
}

interface TodoAddClickListener {
    fun onAddClick(v: View, obj: Todo)
}