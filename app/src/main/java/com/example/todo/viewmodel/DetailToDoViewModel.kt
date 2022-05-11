package com.example.todo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.todo.model.Todo
import com.example.todo.model.TodoDatabase
import com.example.todo.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*
import kotlin.coroutines.CoroutineContext

class DetailToDoViewModel(application: Application)
    :AndroidViewModel(application), CoroutineScope {
    private val job = Job()
    val todoLD = MutableLiveData<Todo>()

    fun fetch(uuid: Int) {
        launch {
            val db = buildDB(getApplication())

            todoLD.value = db.todoDao().selectTodo(uuid)
        }
    }

    fun addTodo(list: List<Todo>) {
        launch {
            val db = buildDB(getApplication())

            db.todoDao().insertAll(*list.toTypedArray())
        }
    }

    fun update(title: String, notes: String, priority: Int, uuid: Int) {
        launch {
            val db = buildDB(getApplication())

            db.todoDao().update(title, notes, priority, uuid)
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}