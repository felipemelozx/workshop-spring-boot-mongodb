package com.felipemelozx.workshopmongo.resources.Util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class URL {

    public static String decodePAram(String text) {
        return URLDecoder.decode(text, StandardCharsets.UTF_8);
    }
}
