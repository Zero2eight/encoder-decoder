package com.transfer.decoder.writer.impl;

import com.transfer.decoder.writer.Writer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;

@Component
public class WriterImpl implements Writer {
    @Value("${writeDir}")
    private String writeDir;

    @Override
    public void write(byte[] bytes, String fileName) {
        String write2File;
        if (writeDir.charAt(writeDir.length() - 1) == '/') {
            write2File = writeDir + fileName;
        } else {
            write2File = writeDir + "/" + fileName;
        }
        File file = new File(write2File);
        try (FileOutputStream fos = new FileOutputStream(file);) {
            fos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }

    }
}
