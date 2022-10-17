package Alkemy.Disney.services.implementations;


import Alkemy.Disney.services.FilesStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class FilesStorageServiceImp implements FilesStorageService {

    @Override
    public String saveFile(MultipartFile file, String name, String directory) throws Exception {

        if(!Objects.requireNonNull(file.getContentType()).contains("image")) {
            return null;
        }

        Path rootFolder = Paths.get("src/main/resources/static/uploads/" + directory);

        String serverUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

        String fileName = name + "." + Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[1];

        Files.copy(file.getInputStream(), rootFolder.resolve(fileName));

        return serverUrl + "/images/" + directory + "/" + fileName;
    }


    @Override
    public String updateFile(MultipartFile file, String name, String directory) throws Exception {

        String fileName = name + "." + Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[1];

        Path rootFolder = Paths.get("src/main/resources/static/uploads/" + directory);

        Files.deleteIfExists(rootFolder.resolve(fileName));

        return this.saveFile(file, name, directory);
    }

}
