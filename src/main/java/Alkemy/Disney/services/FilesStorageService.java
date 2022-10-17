package Alkemy.Disney.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public interface FilesStorageService {

    String saveFile(MultipartFile file, String name, String directory) throws Exception;

    String updateFile(MultipartFile file, String name, String directory) throws Exception;



}
