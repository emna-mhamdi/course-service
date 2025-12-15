package com.university.course.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "courses")
public class Course implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "course_code", unique = true, nullable = false, length = 50)
    private String courseCode;
    
    @Column(name = "course_name", nullable = false, length = 200)
    private String courseName;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "credits", nullable = false)
    private int credits;
    
    @Column(name = "semester", length = 50)
    private String semester;
    
    @Column(name = "max_students")
    private int maxStudents;
    
    @Column(name = "instructor", length = 100)
    private String instructor;
    
    @Column(name = "schedule", length = 100)
    private String schedule;
    
    @Column(name = "room", length = 50)
    private String room;
    
    // Constructors
    public Course() {}
    
    public Course(Long id, String courseCode, String courseName, String description, 
                  int credits, String semester, int maxStudents, String instructor,
                  String schedule, String room) {
        this.id = id;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.description = description;
        this.credits = credits;
        this.semester = semester;
        this.maxStudents = maxStudents;
        this.instructor = instructor;
        this.schedule = schedule;
        this.room = room;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }
    
    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }
    
    public int getMaxStudents() { return maxStudents; }
    public void setMaxStudents(int maxStudents) { this.maxStudents = maxStudents; }
    
    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }
    
    public String getSchedule() { return schedule; }
    public void setSchedule(String schedule) { this.schedule = schedule; }
    
    public String getRoom() { return room; }
    public void setRoom(String room) { this.room = room; }
    
    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseCode='" + courseCode + '\'' +
                ", courseName='" + courseName + '\'' +
                ", credits=" + credits +
                ", semester='" + semester + '\'' +
                '}';
    }
}