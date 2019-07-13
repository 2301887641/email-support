package com.email.support.utils;

import com.fasterxml.uuid.Generators;

import java.util.UUID;

/**
 * @author suiguozhen
 * @date 19/07/13
 */
public class UUIDUtils {

    public static String generate(){
        UUID generate = Generators.timeBasedGenerator().generate();
        return generate.toString();
    }
}
