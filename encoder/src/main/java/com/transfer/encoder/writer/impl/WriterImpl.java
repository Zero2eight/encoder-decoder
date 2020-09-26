package com.transfer.encoder.writer.impl;

import com.transfer.encoder.writer.Writer;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

@Component
public class WriterImpl implements Writer {
    @Override
    public void writeTo(String location, String content) {
        char endCharOfLocation = location.charAt(location.length() - 1);
        if (endCharOfLocation == '/' || endCharOfLocation == '\\') {
            location = location + "outputFile.txt";
        } else {
            location = location + "/outputFile.txt";
        }
        File file = new File(location);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(content.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
