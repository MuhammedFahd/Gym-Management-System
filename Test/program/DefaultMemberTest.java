package program;

import org.junit.Test;

import static org.junit.Assert.*;

public class DefaultMemberTest {

    @Test
    public void getMembershipNumber() {
        DefaultMember dMem=new DefaultMember();
        dMem.setMembershipNumber(101);
        int num=dMem.getMembershipNumber();
        assertEquals(101,num);
    }

    @Test
    public void getName() {
        DefaultMember dMem=new DefaultMember();
        dMem.setName("jhon");
        String name=dMem.getName();
        assertEquals("jhon",name);
    }
}