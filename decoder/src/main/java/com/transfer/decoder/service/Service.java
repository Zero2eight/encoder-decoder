package com.transfer.decoder.service;

import com.transfer.decoder.domain.UploadDto;

import java.io.BufferedInputStream;

public interface Service {
    void dealUpload(UploadDto uploadDto);

    BufferedInputStream downLoadEncoder();
}
