package reportapplication.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reportapplication.demo.Model.FileModel;
import reportapplication.demo.Repository.FileRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UploadFileController {

    @Autowired
    FileRepository fileRepository;

    /*
     * MultipartFile Upload
     */
    @PostMapping("/api/file/upload/{userName}")
    public String uploadMultipartFile(@RequestParam("file") MultipartFile file, @PathVariable("userName") String userName) {
        try {
            // save file to PostgreSQL
            FileModel filemode = new FileModel(file.getOriginalFilename(), file.getContentType(), file.getBytes(), userName);
            fileRepository.save(filemode);
            return "File uploaded successfully! -> filename = " + file.getOriginalFilename();
        } catch (	Exception e) {
            return "FAIL! Maybe You had uploaded the file before or the file's size > 500KB";
        }
    }
}
