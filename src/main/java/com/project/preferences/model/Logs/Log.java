package com.project.preferences.model.Logs;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Log {
    private static final Logger logger = LoggerFactory.getLogger(Log.class);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message")
    private String message;

    @Column(name = "log_level")
    private String logLevel;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Log() {
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - %s", logLevel, createdAt, message);
    }

    public void log() {
        logger.info(toString());
    }

}