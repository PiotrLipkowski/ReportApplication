package reportapplication.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reportapplication.demo.Model.FileModel;
import reportapplication.demo.Repository.FileRepository;

import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import reportapplication.demo.Message.request.LoginForm;
import reportapplication.demo.Message.response.JwtResponse;
import reportapplication.demo.Model.FileModel;
import reportapplication.demo.Model.View;
import reportapplication.demo.Repository.FileRepository;
import reportapplication.demo.Security.jwt.JwtProvider;

import javax.validation.Valid;
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

    @GetMapping("/api/file/all")
    public List<FileModel> getListFiles() {
        return fileRepository.findAll();
    }


    @GetMapping("/api/file/{id}/all")
    public List<FileModel> getListFilesById(@PathVariable Long id) {
        return fileRepository.filesById(id);
    }


    /*
     * Download Files
     */
    @GetMapping("/api/file/{id}")
    @PreAuthorize("hasRole('ADMIN')")
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
