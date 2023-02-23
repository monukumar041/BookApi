package com.Rest.API.Controller;
import com.Rest.API.helper.FileUploaderHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
@RestController
public class fileUploaderController {
    @Autowired
    private FileUploaderHelper fileUploaderHelper;

    public fileUploaderController(FileUploaderHelper fileUploaderHelper) {
        this.fileUploaderHelper = fileUploaderHelper;
    }

    @PostMapping("/upload-file")
    public ResponseEntity<String>uploadFile(@RequestParam("file") MultipartFile file)
    {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain file");
            }
            boolean f=fileUploaderHelper.uploadFile(file);
            if(f)
            {
                return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(file.getOriginalFilename()).toUriString());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("SomeThing went work!! please try again..");
    }
}
