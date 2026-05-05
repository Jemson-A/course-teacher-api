package com.example.controller;

import com.example.projection.CourseTeacherView;
import com.example.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/{courseId}/teacher-view")
    public ResponseEntity<CourseTeacherView> getCourseTeacherView(
            @PathVariable Long courseId) {
        
        return courseService.getCourseWithTeacher(courseId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/teacher-views")
    public ResponseEntity<List<CourseTeacherView>> getAllCoursesTeacherView() {
        List<CourseTeacherView> courses = courseService.getAllCoursesWithTeachers();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/by-teacher")
    public ResponseEntity<List<CourseTeacherView>> getCoursesByTeacher(
            @RequestParam(name = "teacherName", required = true) String teacherName) {
        
        List<CourseTeacherView> courses = courseService.getCoursesByTeacherName(teacherName);
        
        if (courses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        
        return ResponseEntity.ok(courses);
    }
}