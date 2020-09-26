package com.transfer.encoder.reader.impl;

import com.transfer.encoder.reader.Reader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.List;

@Component
public class ReaderImpl implements Reader {
    @Override
    public byte[] read(File file) {
        if (file.isDirectory()) {
            throw new RuntimeException("文件不可以是一个目录");
        }

        try (FileInputStream fis = new FileInputStream(file)){
            List<byte[]> bytesList = new LinkedList<>();
            int rst;
            do {
                byte[] temp = new byte[1024];
                rst = fis.read(temp);
                bytesList.add(temp);
            } while (rst == 1024);
            int totalLen = (bytesList.size() - 1) * 1024 + rst;
            System.out.println("如果文件大小超过2147483648 byte，将造成溢出，无法正确读取");
            byte[] ret = new byte[totalLen];
            int index = 0;
            for (byte[] splice : bytesList) {
                for (byte b : splice) {
                    if (index < totalLen) {
                        ret[index++] = b;
                    } else {
                        break;
                    }
                }
            }
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
