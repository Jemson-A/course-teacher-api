package com.example.repository;

import com.example.entity.Course;
import com.example.projection.CourseTeacherView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT new com.example.projection.CourseTeacherView(c.title, t.fullName) " +
           "FROM Course c JOIN c.teacher t WHERE c.id = :courseId")
    Optional<CourseTeacherView> findCourseTeacherViewById(@Param("courseId") Long courseId);

    @Query("SELECT new com.example.projection.CourseTeacherView(c.title, t.fullName) " +
           "FROM Course c JOIN c.teacher t")
    List<CourseTeacherView> findAllCoursesWithTeachers();

    @Query("SELECT new com.example.projection.CourseTeacherView(c.title, t.fullName) " +
           "FROM Course c JOIN c.teacher t WHERE t.fullName LIKE %:teacherName%")
    List<CourseTeacherView> findCoursesByTeacherName(@Param("teacherName") String teacherName);
}