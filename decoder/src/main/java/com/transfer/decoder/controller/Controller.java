package com.transfer.decoder.controller;

import com.transfer.decoder.domain.UploadDto;
import com.transfer.decoder.service.Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

@RestController
@Api("编码文件字符串处理接口")
public class Controller {
    @Autowired
    private Service service;

    @GetMapping("downLoadEncoder")
    @ApiOperation("下载编码器")
    public ResponseEntity downLoadEncoder(HttpServletResponse response) {
        try (BufferedInputStream bis = service.downLoadEncoder()) {
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + "encoder-0.0.1-SNAPSHOT.jar");
            OutputStream ops = response.getOutputStream();
            byte[] bytes = new byte[1024];
            int i;
            while ((i = bis.read(bytes)) != -1) {
                ops.write(bytes, 0, i);
            }
            return ResponseEntity.ok("下载编码器成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("下载编码器失败");
        }
    }

    @PostMapping("/uploadString")
    @ApiOperation("处理编码文件字符串")
    public ResponseEntity uploadString(@RequestBody UploadDto uploadDto) {
        try {
            service.dealUpload(uploadDto);
            return ResponseEntity.ok("解码传入字符串成功！");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("解码传入字符串失败");
        }
    }
}
