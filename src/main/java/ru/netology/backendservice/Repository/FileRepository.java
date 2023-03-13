package ru.netology.backendservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.netology.backendservice.FileInfo.FileInfo;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<FileInfo, Long> {
    Optional<FileInfo> findFileInfosByName(String name);

}
