package ru.netology.backendservice.FileInfo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Component
@Data
public class FileInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private Long size;

    @Column(name = "key_file")
    private String key;

    private LocalDate localDate;

    public FileInfo() {
    }


}
