package com.min.seed.core.tool;

import java.util.UUID;

public class Tools {

    public static String generateUUID() {
        UUID uuid = UUID.randomUUID();
        String s = uuid.toString();
        s = s.replaceAll("-", "");
        return s;
    }

}
