package com.example.demo.repositories;
import com.example.demo.entities.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepo extends JpaRepository<Level, Long> {
}
