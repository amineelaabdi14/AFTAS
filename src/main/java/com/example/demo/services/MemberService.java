package com.example.demo.services;
import com.example.demo.entities.Member;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberService {
    Member save(Member entity);

    Member findById(Long id);

    Member findByNum(Integer num);

    void deleteById(Long id);

    List<Member> findAll(Pageable pageable);

    List<Member> search(String query);
}
