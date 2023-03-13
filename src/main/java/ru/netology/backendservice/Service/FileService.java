package ru.netology.backendservice.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.backendservice.FileInfo.FileInfo;
import ru.netology.backendservice.Repository.FileRepository;
import ru.netology.backendservice.Response.Response;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@org.springframework.stereotype.Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;
    private final String DIRECTORY_PATH = "./src/main/resources/storage";


    public FileInfo upload(MultipartFile file) throws IOException {
        String key = DigestUtils.md5DigestAsHex((file.getName() + LocalDateTime.now()).getBytes());
        FileInfo createFile = new FileInfo();
        createFile.setName(file.getOriginalFilename());
        createFile.setSize(file.getSize());
        createFile.setKey(key);
        createFile.setLocalDate(LocalDate.now());
        fileRepository.saveAndFlush(createFile);

        Path path = Paths.get(DIRECTORY_PATH, key);
        Path resFile = Files.createFile(path);
        try (FileOutputStream stream = new FileOutputStream(resFile.toString())) {
            stream.write(file.getBytes());
        }
        return createFile;
    }

    public Resource download(String fileName) throws IOException {
        Optional<FileInfo> opt = fileRepository.findFileInfosByName(fileName);
        if (opt.isEmpty()) {
            throw new IOException();
        }
        Path path = Paths.get(DIRECTORY_PATH + "/" + opt.get().getKey());
        Resource resource = new ByteArrayResource(Files.readAllBytes(path));
        if (resource.exists() || resource.isReadable()) {
            return resource;
        } else {
            throw new IOException();
        }
    }

    public Response delete(String fileName) throws IOException {
        Optional<FileInfo> opt = fileRepository.findFileInfosByName(fileName);
        if (opt.isEmpty()) {
            throw new IOException();
        }
        fileRepository.delete(opt.get());
        Path path = Paths.get(DIRECTORY_PATH + "/" + opt.get().getKey());
        Files.delete(path);
        return new Response(200, "Success deleted");
    }
}


