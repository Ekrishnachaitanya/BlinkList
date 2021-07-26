package com.zemoso.blinklist;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BlinklistApplicationTests {

    @Test
    void main(){
        BlinklistApplication.main(new String[] {});
        assertNotNull(BlinklistApplication.class);
    }

}
