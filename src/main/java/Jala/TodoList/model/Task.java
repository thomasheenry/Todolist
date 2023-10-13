package Jala.TodoList.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.time.LocalDateTime;

@Entity
@Document(collection = "Tasks")
@Setter
@Getter
@ToString

public class Task {

    @Id
    private String _id;
    @Field("user_id")
    private String userId;

    private String title;
    private String description;
    private Boolean status;
    private LocalDateTime deadLine;

}
