package com.transfer.decoder.service.impl;

import com.transfer.decoder.decoder.Decoder;
import com.transfer.decoder.domain.UploadDto;
import com.transfer.decoder.service.Service;
import com.transfer.decoder.writer.Writer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {
    @Autowired
    private Decoder decoder;

    @Autowired
    private Writer writer;

    @Override
    public void dealUpload(UploadDto uploadDto) {
        String fileName = uploadDto.getFileName();
        String content = uploadDto.getContent();
        byte[] bytes = decoder.decode(content);
        writer.write(bytes, fileName);
    }

    @Override
    public BufferedInputStream downLoadEncoder() {
        try {
            File file = new ClassPathResource("static/encoder-0.0.1-SNAPSHOT.jar").getFile();
            FileInputStream fis = new FileInputStream(file);
            return new BufferedInputStream(fis);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }

    }
}
