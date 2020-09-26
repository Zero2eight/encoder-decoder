package com.transfer.encoder;

import com.transfer.encoder.encoder.Encoder;
import com.transfer.encoder.reader.Reader;
import com.transfer.encoder.writer.Writer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;

@Component
public class EncoderApplication {
    @Autowired
    private Encoder encoder;

    @Autowired
    private Reader reader;

    @Autowired
    private Writer writer;

    public void doWork(String srcFile, @Nullable String destLoc) {
        File file = new File(srcFile);
        byte[] bytes = reader.read(file);
        String content = encoder.encoder(bytes);
        if (destLoc == null) {
            System.out.println("没有指定目标文件地址，将在源文件" + srcFile + "所在目录中创建文件");
            File parentFile = file.getParentFile();
            destLoc = parentFile.getAbsolutePath();
        }
        writer.writeTo(destLoc, content);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.transfer.encoder");
        EncoderApplication app = context.getBean(EncoderApplication.class);
        if (args.length == 2) {
            app.doWork(args[0], args[1]);
        } else if (args.length == 1) {
            app.doWork(args[0], null);
        } else {
            System.out.println("需指定源文件");
        }
    }

}
