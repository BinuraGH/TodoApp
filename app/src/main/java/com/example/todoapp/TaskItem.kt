package com.example.todoapp

import android.content.Context
import androidx.core.content.ContextCompat
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

class TaskItem (
    var name : String,
    var desc: String,
    var dueTime:LocalTime?,//?-can be null
    var completeDate:LocalDate?,
    var id:UUID = UUID.randomUUID()
    )
{
    fun isCompleted() = completeDate != null
    fun imageResource() : Int = if(isCompleted()) R.drawable.checked_24 else R.drawable.unchecked_24
    fun imageColor(context: Context) :Int  = if(isCompleted()) purple(context) else black(context)

    private fun purple(context: Context) =  ContextCompat.getColor(context,R.color.system_on_primary_fixed)
    private fun black(context: Context) =  ContextCompat.getColor(context,R.color.black)

}
