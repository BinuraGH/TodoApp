package com.example.todoapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

class TaskViewModel(private val repository: TaskItemRepository) : ViewModel(){

    var taskItems:LiveData<List<TaskItem>> = repository.allTaskItem.asLiveData()

    fun addTaskItem(newTask: TaskItem) = viewModelScope.launch {
        repository.insertTaskItem(newTask)
    }
    fun updateTaskItem(newTask: TaskItem) = viewModelScope.launch {
        repository.updateTaskItem(newTask)
    }
    fun deleteTaskItem(newTask: TaskItem) = viewModelScope.launch {
        repository.deleteTaskItem(newTask)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun setCompleted(taskItem: TaskItem)= viewModelScope.launch {
        if(!taskItem.isCompleted())
            taskItem.completeDateString = TaskItem.dateFormatter.format(LocalDate.now())
        repository.updateTaskItem(taskItem)
    }

}

class TaskItemModelFactory(private val repository:TaskItemRepository):ViewModelProvider.Factory
{
    override fun<T:ViewModel> create(modelClass: Class<T>):T{
        if(modelClass.isAssignableFrom(TaskViewModel::class.java))
            return TaskViewModel(repository)as T

        throw  IllegalArgumentException("Unknown class for view Model")
    }
}