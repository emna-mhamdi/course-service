package com.university.course.ws;

import com.university.course.dao.CourseDAO;
import com.university.course.model.Course;
import jakarta.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "com.university.course.ws.CourseService")
public class CourseServiceImpl implements CourseService {

    private final CourseDAO courseDAO = new CourseDAO();

    @Override
    public Course createCourse(String courseCode, String courseName, String description,
                               int credits, String instructor, int capacity, String semester) {
        Course course = new Course(courseCode, courseName, description,
                credits, instructor, capacity, semester);
        return courseDAO.save(course);
    }

    @Override
    public Course getCourse(Long id) {
        return courseDAO.findById(id);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseDAO.findAll();
    }

    @Override
    public Course updateCourse(Long id, String courseCode, String courseName,
                               String description, int credits, String instructor,
                               int capacity, String semester) {
        Course course = courseDAO.findById(id);
        if (course != null) {
            course.setCourseCode(courseCode);
            course.setCourseName(courseName);
            course.setDescription(description);
            course.setCredits(credits);
            course.setInstructor(instructor);
            course.setCapacity(capacity);
            course.setSemester(semester);
            return courseDAO.update(course);
        }
        return null;
    }

    @Override
    public String deleteCourse(Long id) {
        courseDAO.delete(id);
        return "Course deleted successfully";
    }
}