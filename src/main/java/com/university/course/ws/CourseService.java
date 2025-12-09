package com.university.course.ws;

import com.university.course.model.Course;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.util.List;

@WebService
public interface CourseService {

    @WebMethod
    Course createCourse(
            @WebParam(name = "courseCode") String courseCode,
            @WebParam(name = "courseName") String courseName,
            @WebParam(name = "description") String description,
            @WebParam(name = "credits") int credits,
            @WebParam(name = "instructor") String instructor,
            @WebParam(name = "capacity") int capacity,
            @WebParam(name = "semester") String semester
    );

    @WebMethod
    Course getCourse(@WebParam(name = "id") Long id);

    @WebMethod
    List<Course> getAllCourses();

    @WebMethod
    Course updateCourse(
            @WebParam(name = "id") Long id,
            @WebParam(name = "courseCode") String courseCode,
            @WebParam(name = "courseName") String courseName,
            @WebParam(name = "description") String description,
            @WebParam(name = "credits") int credits,
            @WebParam(name = "instructor") String instructor,
            @WebParam(name = "capacity") int capacity,
            @WebParam(name = "semester") String semester
    );

    @WebMethod
    String deleteCourse(@WebParam(name = "id") Long id);
}