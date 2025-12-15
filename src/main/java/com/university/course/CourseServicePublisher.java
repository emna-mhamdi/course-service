package com.university.course;

import com.university.course.util.HibernateUtil;
import com.university.course.ws.CourseServiceImpl;

import jakarta.xml.ws.Endpoint;

public class CourseServicePublisher {
    public static void main(String[] args) {
        String url = "http://0.0.0.0:8083/ws/courses";
        
        try {
            System.out.println("========================================");
            System.out.println("Starting Course SOAP Service (Hibernate)");
            System.out.println("========================================");
            
            // Initialize Hibernate
            HibernateUtil.getSessionFactory();
            
            // Publish SOAP service
            CourseServiceImpl implementor = new CourseServiceImpl();
            Endpoint endpoint = Endpoint.publish(url, implementor);
            
            System.out.println("✓ Course SOAP Service is running!");
            System.out.println("✓ Endpoint: " + url);
            System.out.println("✓ WSDL: " + url + "?wsdl");
            System.out.println("========================================");
            System.out.println("Press Ctrl+C to stop the service...");
            System.out.println("========================================\n");
            
            // Shutdown hook to close Hibernate properly
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("\n✓ Shutting down Hibernate...");
                HibernateUtil.shutdown();
                System.out.println("✓ Service stopped gracefully");
            }));
            
            // Keep the service running
            Thread.currentThread().join();
            
        } catch (Exception e) {
            System.err.println("✗ Error starting service: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
