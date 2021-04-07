package program;

import org.junit.Test;

import static org.junit.Assert.*;

public class DateTest {

    @Test
    public void stringDate() {
        Date date=new Date(1,2,2020);
        String sDate=date.stringDate();
        assertEquals("01-02-2020",sDate);
    }

    @Test
    public void isValidDate() {
        Date date=new Date(1,2,2020);
        boolean result=date.isValidDate();
        assertEquals(true,result);
    }
}