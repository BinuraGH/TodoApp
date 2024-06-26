package com.example.todoapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), TaskItemClickListener{

    private lateinit var binding: ActivityMainBinding
    private val taskViewModel: TaskViewModel by viewModels{
        TaskItemModelFactory((application as TodoApplication).repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.newTaskButton.setOnClickListener{
            NewTaskSheet(null).show(supportFragmentManager,"newTaskTag")
        }
        setRecyclerView()
    }

    private fun setRecyclerView()
    {
        val mainActivity = this
        taskViewModel.taskItems.observe((this)){
            binding.todoListRecyclerView.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = TaskItemAdapter(it,mainActivity)
            }
        }
    }

    override fun editTaskItem(taskItem: TaskItem)
    {
        NewTaskSheet(taskItem).show(supportFragmentManager,"newTaskTag")
    }


    override fun completeTaskItem(taskItem: TaskItem)
    {
        taskViewModel.setCompleted(taskItem)
    }

    override fun deleteTaskItem(taskItem: TaskItem) {
        taskViewModel.deleteTaskItem(taskItem)

    }

}