package com.university.course.ws;

import com.university.course.dao.CourseDAO;
import com.university.course.model.Course;
import jakarta.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "com.university.course.ws.CourseService")
public class CourseServiceImpl implements CourseService {
    
    private final CourseDAO courseDAO;
    
    public CourseServiceImpl() {
        this.courseDAO = new CourseDAO();
        System.out.println("✓ CourseServiceImpl initialized with Hibernate");
    }
    
    @Override
    public Course createCourse(Course course) {
        try {
            System.out.println("→ Creating course: " + course.getCourseCode());
            return courseDAO.save(course);
        } catch (Exception e) {
            System.err.println("✗ Error in createCourse: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error creating course: " + e.getMessage(), e);
        }
    }
    
    @Override
    public Course getCourseById(Long id) {
        try {
            System.out.println("→ Getting course by ID: " + id);
            Course course = courseDAO.findById(id);
            if (course == null) {
                throw new RuntimeException("Course not found with ID: " + id);
            }
            return course;
        } catch (Exception e) {
            System.err.println("✗ Error in getCourseById: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error getting course: " + e.getMessage(), e);
        }
    }
    
    @Override
    public Course getCourseByCourseCode(String courseCode) {
        try {
            System.out.println("→ Getting course by code: " + courseCode);
            Course course = courseDAO.findByCourseCode(courseCode);
            if (course == null) {
                throw new RuntimeException("Course not found with code: " + courseCode);
            }
            return course;
        } catch (Exception e) {
            System.err.println("✗ Error in getCourseByCourseCode: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error getting course: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<Course> getAllCourses() {
        try {
            System.out.println("→ Getting all courses");
            List<Course> courses = courseDAO.findAll();
            System.out.println("✓ Returning " + courses.size() + " courses");
            return courses;
        } catch (Exception e) {
            System.err.println("✗ Error in getAllCourses: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error getting courses: " + e.getMessage(), e);
        }
    }
    
    @Override
    public Course updateCourse(Course course) {
        try {
            System.out.println("→ Updating course: " + course.getId());
            return courseDAO.update(course);
        } catch (Exception e) {
            System.err.println("✗ Error in updateCourse: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error updating course: " + e.getMessage(), e);
        }
    }
    
    @Override
    public boolean deleteCourse(Long id) {
        try {
            System.out.println("→ Deleting course: " + id);
            return courseDAO.delete(id);
        } catch (Exception e) {
            System.err.println("✗ Error in deleteCourse: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error deleting course: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<Course> getCoursesBySemester(String semester) {
        try {
            System.out.println("→ Getting courses for semester: " + semester);
            return courseDAO.findBySemester(semester);
        } catch (Exception e) {
            System.err.println("✗ Error in getCoursesBySemester: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error getting courses: " + e.getMessage(), e);
        }
    }
}
