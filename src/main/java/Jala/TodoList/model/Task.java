package Jala.TodoList.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Entity
@Document(collection = "Tasks")
@Setter
@Getter
@ToString

public class Task {

    @Id
    private String _id;

    private String user_id;

    private String title;
    private String description;
    private String status;
    private LocalDateTime deadLine;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
