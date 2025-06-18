package com.artemyakkonen.server.repository;

import com.artemyakkonen.server.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

}
