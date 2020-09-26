package com.transfer.encoder.encoder.impl;

import com.transfer.encoder.encoder.Encoder;
import org.springframework.stereotype.Component;

@Component
public class EncoderImpl implements Encoder {
    @Override
    public String encoder(byte[] bytes) {
        StringBuffer sb = new StringBuffer(bytes.length);
        for (byte b : bytes) {
            sb.append((char) byte2Unicode(b));
        }
        return sb.toString();
    }

    private int byte2Unicode(byte b) {
        int usi = byte2UnsignedInt(b);
        return map2Unicode(usi);
    }


    private int byte2UnsignedInt(byte b) {
        return Byte.toUnsignedInt(b);
    }

    private int map2Unicode(int usi) {
        // 这里加上一个偏置
        return usi + 0x100;
    }
}
