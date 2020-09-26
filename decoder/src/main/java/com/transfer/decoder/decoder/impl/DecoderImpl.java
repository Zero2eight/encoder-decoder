package com.transfer.decoder.decoder.impl;

import com.transfer.decoder.decoder.Decoder;
import org.springframework.stereotype.Component;

@Component
public class DecoderImpl implements Decoder {
    @Override
    public byte[] decode(String content) {
        byte[] ret = new byte[content.length()];
        for (int i = 0; i < content.length(); i++) {
            int unicode = content.codePointAt(i);
            int usi = unicode2UnsignedInt(unicode);
            ret[i] = unsignedInt2byte(usi);
        }
        return ret;
    }

    private int unicode2UnsignedInt(int unicode) {
        return unicode - 0x100;
    }

    private byte unsignedInt2byte(int usi) {
        if (usi < Math.pow(2, 8) && usi >= 0) {
            return (byte) usi;
        } else {
            throw new RuntimeException("无法解码的字符");
        }

    }
}
