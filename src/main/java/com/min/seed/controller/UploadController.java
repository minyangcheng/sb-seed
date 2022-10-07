package com.min.seed.controller;

import com.min.seed.core.result.Result;
import com.min.seed.core.result.ResultGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController {

    private String FILE_DIR = "./files";

    @PostMapping("/file")
    @ResponseBody
    public Result create(@RequestPart MultipartFile[] files) throws IOException {
        List<String> data = new ArrayList<>();
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            File dest = new File(FILE_DIR, fileName);
            if(dest.exists()){
                dest.delete();
            }
            Files.copy(file.getInputStream(), dest.toPath());
            data.add(fileName);
            log.debug("Upload file success : " + dest.getAbsolutePath());
        }
        return ResultGenerator.genSuccessResult(data);
    }

}

