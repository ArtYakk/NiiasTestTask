package com.artemyakkonen.spring.boot.ttmicroservice2.repository;

import com.artemyakkonen.spring.boot.ttmicroservice2.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

}
