package com.project.preferences.repository.logs;

import com.project.preferences.model.Logs.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
    //можно добавить запросы для сортировки логов
}