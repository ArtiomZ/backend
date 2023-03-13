package ru.netology.backendservice.Controllers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.backendservice.FileInfo.FileInfo;
import ru.netology.backendservice.Response.Response;
import ru.netology.backendservice.Service.FileService;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> download(@RequestParam String fileName) throws IOException {
        Resource resource = fileService.download(fileName);
        byte[] fileBytes = StreamUtils.copyToByteArray(resource.getInputStream());

        Map<String, Object> response = new HashMap<>();
        response.put("binary", Base64.getEncoder().encodeToString(fileBytes));

        return ResponseEntity.ok().body(response);
    }

   /* @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map> download(@RequestParam String fileName) throws IOException {
        Resource resource = fileService.download(fileName);
        Map<String, Object> map = new HashMap<>();
    //   map.put("file", FileCopyUtils.copyToByteArray(resource.getFile()));
      //  map.put("file", resource.getFile());
        return ResponseEntity.ok()
                .body(map);
    }*/

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
