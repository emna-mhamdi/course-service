package com.university.course.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.university.course.model.Course;
import com.university.course.util.HibernateUtil;

public class CourseDAO {
    
    public Course save(Course course) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(course);
            transaction.commit();
            System.out.println("✓ Course saved: " + course.getCourseCode());
            return course;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("✗ Error saving course: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error saving course: " + e.getMessage(), e);
        }
    }
    
    public Course findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Course course = session.get(Course.class, id);
            if (course != null) {
                System.out.println("✓ Found course by ID: " + id);
            } else {
                System.out.println("✗ Course not found with ID: " + id);
            }
            return course;
        } catch (Exception e) {
            System.err.println("✗ Error finding course by ID: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error finding course: " + e.getMessage(), e);
        }
    }
    
    public Course findByCourseCode(String courseCode) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            
            Query<Course> query = session.createQuery(
                "FROM Course WHERE courseCode = :code", Course.class);
            query.setParameter("code", courseCode);
            
            Course course = query.uniqueResult();
            transaction.commit();
            
            if (course != null) {
                System.out.println("✓ Found course by code: " + courseCode);
            } else {
                System.out.println("✗ Course not found with code: " + courseCode);
            }
            return course;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("✗ Error finding course by code: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error finding course: " + e.getMessage(), e);
        }
    }
    
    public List<Course> findAll() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<Course> courses = session.createQuery("FROM Course ORDER BY id", Course.class).list();
            transaction.commit();
            System.out.println("✓ Retrieved " + courses.size() + " courses");
            return courses;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("✗ Error retrieving all courses: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error retrieving courses: " + e.getMessage(), e);
        }
    }
    
    public Course update(Course course) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Course updated = (Course) session.merge(course);
            transaction.commit();
            System.out.println("✓ Course updated: " + course.getCourseCode());
            return updated;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("✗ Error updating course: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error updating course: " + e.getMessage(), e);
        }
    }
    
    public boolean delete(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Course course = session.get(Course.class, id);
            if (course != null) {
                session.remove(course);
                transaction.commit();
                System.out.println("✓ Course deleted: " + id);
                return true;
            } else {
                transaction.commit();
                System.out.println("✗ Course not found for deletion: " + id);
                return false;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("✗ Error deleting course: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error deleting course: " + e.getMessage(), e);
        }
    }
    
    public List<Course> findBySemester(String semester) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            
            Query<Course> query = session.createQuery(
                "FROM Course WHERE semester = :semester ORDER BY id", Course.class);
            query.setParameter("semester", semester);
            
            List<Course> courses = query.list();
            transaction.commit();
            
            System.out.println("✓ Retrieved " + courses.size() + " courses for semester: " + semester);
            return courses;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("✗ Error finding courses by semester: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error finding courses: " + e.getMessage(), e);
        }
    }
}