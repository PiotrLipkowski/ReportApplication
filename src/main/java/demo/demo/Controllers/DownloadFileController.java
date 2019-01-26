package demo.demo.Controllers;

import com.fasterxml.jackson.annotation.JsonView;
import demo.demo.Model.FileModel;
import demo.demo.Model.View;
import demo.demo.Repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class DownloadFileController {

    @Autowired
    FileRepository fileRepository;

    /*
     * List All Files
     */
    @JsonView(View.FileInfo.class)
    @GetMapping("/api/file/all")
    public List<FileModel> getListFiles() {
        return fileRepository.findAll();
    }

    /*
     * Download Files
     */
    @GetMapping("/api/file/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        Optional<FileModel> fileOptional = fileRepository.findById(id);

        if(fileOptional.isPresent()) {
            FileModel file = fileOptional.get();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                    .body(file.getPic());
        }

        return ResponseEntity.status(404).body(null);
    }
}