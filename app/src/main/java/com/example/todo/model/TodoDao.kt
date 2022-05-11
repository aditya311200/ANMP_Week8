package com.example.todo.model

import android.service.quicksettings.Tile
import androidx.room.*

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll (vararg todo: Todo)

    @Query("SELECT * FROM todo WHERE is_done= 0 ORDER BY priority DESC")
    suspend fun selectAllTodo(): List<Todo>

    @Query("SELECT * FROM todo WHERE uuid= :id")
    suspend fun selectTodo(id: Int): Todo

    @Query("UPDATE todo SET title= :title, notes= :notes, priority= :priority WHERE uuid= :uuid")
    suspend fun update(title: String, notes: String, priority: Int, uuid: Int)

    @Query("UPDATE todo SET is_done= 1 WHERE uuid= :uuid")
    suspend fun todoDone(uuid: Int)

    @Delete
    suspend fun deleteTodo(todo: Todo)
}