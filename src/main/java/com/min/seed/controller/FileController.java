package com.min.seed.controller;

import com.min.seed.core.annotation.NeedLogin;
import com.min.seed.core.result.Result;
import com.min.seed.core.result.ResultGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    private String FILE_DIR = "./files";

    /**
     * 文件上传
     *
     * @param files
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    @ResponseBody
    public Result upload(@RequestPart MultipartFile[] files) throws IOException {
        List<String> data = new ArrayList<>();
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            File des = new File(FILE_DIR, fileName);
            if (des.exists()) {
                des.delete();
            }
            Files.copy(file.getInputStream(), des.toPath());
            data.add("files/" + fileName);
            log.debug("Upload file success : " + des.getAbsolutePath());
        }
        return ResultGenerator.genSuccessResult(data);
    }

    /**
     * 文件下载
     *
     * @param fileName
     * @param response
     * @throws IOException
     */
    @RequestMapping("/{fileName}")
    @NeedLogin(value = false)
    public void download(@PathVariable String fileName, HttpServletResponse response) throws IOException {
        File file = new File(FILE_DIR, fileName);
        if (!file.exists()) {
            response.getOutputStream().close();
        }

        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Content-type", "application/octet-stream");

        String name = new String(fileName.getBytes("utf-8"), "iso-8859-1");
        response.setHeader("Content-Disposition", "attachment; filename=" + name);

        long length = file.length();
        response.addHeader("Content-Length", String.valueOf(length));

        OutputStream outputStream = response.getOutputStream();
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        InputStream inputStream = new FileInputStream(file);
        bis = new BufferedInputStream(inputStream);
        int i = bis.read(buff);
        while (i != -1) {
            outputStream.write(buff, 0, buff.length);
            outputStream.flush();
            i = bis.read(buff);
        }
        bis.close();
        outputStream.close();
    }

}

