package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.JoinColumn;

@Getter
@Setter
@Entity
@Table(name = "student")
public class Student extends BaseEntity {
    
    @Column(name = "name",nullable = false,columnDefinition = "varchar(100)")
    private String name;
    
    @Column(name = "address",nullable = false,columnDefinition = "varchar(100)")
    private String address;

    @Column(name = "age",nullable = false,columnDefinition = "varchar(100)")
    private String age;

    @Column(name = "phonenumber",nullable = true,columnDefinition = "varchar(100)")
    private String phonenumber;
    @Lob
    @Column(name = "image", columnDefinition = "LONGBLOB")
    private String image;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE) 
    @JoinTable(name = "student_course" ,joinColumns = @JoinColumn(name = "student_id"),inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course>coursename;

    @OneToOne(cascade =CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private User user;

    public Student() {}

    public Student(String name, String address, String age, String phonenumber, String image, List<Course> coursename, User user) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.phonenumber = phonenumber;
        this.image = image;
        this.coursename = coursename;
        this.user = user;
    }
    

    
}
