package com.assignmenttwo.models;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findBySize(int size);
    List<User> findByHeight(int height);
    List<User> findByHairColor(String hairColor);
    List<User> findByGpa(double gpa);
    List<User> findByNameAndPassword(String name, String password);
    // void deleteById(int id);
}
