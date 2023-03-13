package ru.netology.backendservice.Controllers;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.backendservice.FileInfo.FileInfo;
import ru.netology.backendservice.Response.Response;
import ru.netology.backendservice.Service.FileService;

import java.io.IOException;

@RestController
@RequestMapping("file")
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }



    @PostMapping()
    public ResponseEntity<FileInfo> upLoad(@RequestParam MultipartFile file) throws IOException {
        return new ResponseEntity<>(fileService.upload(file), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Resource> download(@RequestParam String fileName) throws IOException {
        Resource resource = fileService.download(fileName);
        return ResponseEntity.ok()
                .body(resource);
    }

    @DeleteMapping
    public ResponseEntity<Response> delete(@RequestParam String fileName) throws IOException {
        Response response = fileService.delete(fileName);
        return ResponseEntity.ok()
                .body(response);
    }

    @PutMapping
    public ResponseEntity<Response> renameFile(@RequestParam String fileName) {
        return null;
    }



}
