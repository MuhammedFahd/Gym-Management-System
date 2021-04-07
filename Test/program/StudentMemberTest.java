package program;

import org.junit.Test;

import static org.junit.Assert.*;

public class StudentMemberTest {

    @Test
    public void getSchoolName() {
        StudentMember sMem=new StudentMember();
        sMem.setSchoolName("anthonys");
        String sName=sMem.getSchoolName();
        assertEquals("anthonys",sName);
    }
}