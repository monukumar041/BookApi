package com.Rest.API.helper;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Component
public class FileUploaderHelper {
//    public final String UPLOAD_DIR="/Users/Monu/Downloads/RestApiBooks/src/main/resources/static/image";
    public final String UPLOAD_DIR=new ClassPathResource("static/image/").getFile().getAbsolutePath();

    public FileUploaderHelper() throws IOException
    {}
    public boolean uploadFile(MultipartFile multipartFile)
    {
        boolean f=false;
        try
        {
            Files.copy(multipartFile.getInputStream(), Paths.get(UPLOAD_DIR+File.separator+multipartFile.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            f=true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return  f;
    }
}
