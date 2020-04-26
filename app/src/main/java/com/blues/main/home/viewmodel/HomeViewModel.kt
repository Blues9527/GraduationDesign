package com.blues.main.home.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.blues.base.BaseViewModel
import com.blues.course.database.CourseManager
import com.blues.course.model.Course

class HomeViewModel : BaseViewModel() {

    var courses = MutableLiveData<List<Course>>()

    fun queryCourse() {

        launch(
                block = {
                    courses.value = CourseManager.INSTANCE.allCourse
                },
                error = {
                    Log.i("Blues", "bus update error")
                }
        )
    }
}