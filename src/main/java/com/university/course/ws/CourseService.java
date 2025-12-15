// ==================== CourseService.java (INTERFACE CORRIGÉE) ====================
package com.university.course.ws;

import com.university.course.model.Course;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.util.List;

@WebService(serviceName = "CourseService")
public interface CourseService {
    
    @WebMethod
    Course createCourse(@WebParam(name = "course") Course course);
    
    @WebMethod
    Course getCourseById(@WebParam(name = "id") Long id);
    
    @WebMethod
    Course getCourseByCourseCode(@WebParam(name = "courseCode") String courseCode);
    
    @WebMethod
    List<Course> getAllCourses();
    
    @WebMethod
    Course updateCourse(@WebParam(name = "course") Course course);
    
    @WebMethod
    boolean deleteCourse(@WebParam(name = "id") Long id);
    
    @WebMethod
    List<Course> getCoursesBySemester(@WebParam(name = "semester") String semester);
}