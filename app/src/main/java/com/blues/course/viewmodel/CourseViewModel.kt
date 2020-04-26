package com.blues.course.viewmodel

import com.blues.base.BaseViewModel
import com.blues.course.database.CourseManager
import com.blues.course.model.Course
import com.blues.util.Bus
import com.blues.util.COURSE_UPDATE

class CourseViewModel : BaseViewModel() {

    fun insertCourse(course: Course) {
        launch(
                block = {
                    CourseManager.INSTANCE.insertCourse(course)
                    Bus.post(COURSE_UPDATE, true)
                }
        )
    }
}