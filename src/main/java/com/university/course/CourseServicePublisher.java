package com.university.course;

import com.university.course.ws.CourseServiceImpl;
import jakarta.xml.ws.Endpoint;

public class CourseServicePublisher {
    public static void main(String[] args) {
        String url = "http://localhost:8083/ws/courses";
        Endpoint.publish(url, new CourseServiceImpl());
        System.out.println("Course SOAP Service running at: " + url);
        System.out.println("WSDL available at: " + url + "?wsdl");
    }
}