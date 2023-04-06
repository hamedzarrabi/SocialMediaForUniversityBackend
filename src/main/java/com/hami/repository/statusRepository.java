package com.hami.repository;

import com.hami.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface statusRepository extends JpaRepository<Status, Long> {

     Status save(Status status);
     ArrayList<Status> findAll();
}
