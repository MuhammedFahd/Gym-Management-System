package program;

import org.junit.Test;

import static org.junit.Assert.*;

public class Over60MemberTest {

    @Test
    public void getAge() {
        Over60Member oMem=new Over60Member();
        oMem.setAge(70);
        int age=oMem.getAge();
        assertEquals(70,age);
    }
}