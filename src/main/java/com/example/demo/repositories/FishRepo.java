package com.example.demo.repositories;
import com.example.demo.entities.Fish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FishRepo extends JpaRepository<Fish, Long> {
}
