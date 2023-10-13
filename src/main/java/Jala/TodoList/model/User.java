package Jala.TodoList.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@Document(collection = "User")
@Setter
@Getter
@ToString
public class User {

    private String name;
    private String email;
    private String password;
    @Id
    private String _id;

}
