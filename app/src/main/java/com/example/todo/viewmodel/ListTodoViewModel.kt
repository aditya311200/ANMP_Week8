package com.example.todo.viewmodel

import android.app.Application
import android.widget.RadioButton
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.todo.model.Todo
import com.example.todo.model.TodoDatabase
import com.example.todo.util.buildDB
import kotlinx.android.synthetic.main.fragment_create_todo.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListTodoViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val todoLD = MutableLiveData<List<Todo>>()
    val todoLoadingErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    private var job = Job()

    override val  coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun refresh() {
        loadingLD.value = true
        todoLoadingErrorLD.value = false

        launch {
            val db = buildDB(getApplication())

            todoLD.value = db.todoDao().selectAllTodo()
        }
    }

    fun clearTask(todo: Todo) {
        launch {
            val db = buildDB(getApplication())

            db.todoDao().deleteTodo(todo)

            todoLD.value = db.todoDao().selectAllTodo()
        }
    }

    fun taskDone(uuid: Int) {
        launch {
            val db = buildDB(getApplication())

            db.todoDao().todoDone(uuid)

            todoLD.value = db.todoDao().selectAllTodo()
        }
    }
}