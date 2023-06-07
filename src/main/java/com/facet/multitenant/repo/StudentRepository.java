package com.facet.multitenant.repo;

import com.facet.multitenant.bean.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<Student, Long> {
}
