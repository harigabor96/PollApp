package com.example.PollApp.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

@Service
public class HashService {
    public String hashString (String plainString) {
        return DigestUtils.sha256Hex(plainString);
    }
}