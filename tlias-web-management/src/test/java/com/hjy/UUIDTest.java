package com.hjy;

import org.junit.jupiter.api.Test;

import java.util.UUID;

public class UUIDTest {

    @Test
    public void testUuid(){
        for (int i = 0; i < 100; i++) {
            System.out.println(UUID.randomUUID());
            System.out.println(UUID.randomUUID());
            System.out.println(UUID.randomUUID());
            System.out.println(UUID.randomUUID());
            System.out.println(UUID.randomUUID());
            System.out.println(UUID.randomUUID());
        }


    }
}
