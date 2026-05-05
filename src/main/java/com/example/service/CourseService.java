package com.example.service;

import com.example.repository.CourseRepository;
import com.example.projection.CourseTeacherView;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Optional<CourseTeacherView> getCourseWithTeacher(Long courseId) {
        return courseRepository.findCourseTeacherViewById(courseId);
    }

    public List<CourseTeacherView> getAllCoursesWithTeachers() {
        return courseRepository.findAllCoursesWithTeachers();
    }

    public List<CourseTeacherView> getCoursesByTeacherName(String teacherName) {
        return courseRepository.findCoursesByTeacherName(teacherName);
    }
}