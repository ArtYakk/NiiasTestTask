package com.artemyakkonen.server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class ServerApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void concatTest(){
        String string1 = "Hello, ";
        String string2 = "world";
        String result = string1 + string2;

        assertEquals(result, "Hello, world");
    }

}
