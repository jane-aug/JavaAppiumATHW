package HW1.exercise1;

import org.junit.Test;

public class MainClassTest {
    MainClass MainC = new MainClass();

    @Test
    public void testGetLocalNumber () {
        int a = MainC.getLocalNumber();
        if (a == 14) {
            System.out.println("Number from function getLocalNumber = 14");

        } else {
            System.out.println("Number from function getLocalNumber != 14");

        }
    }
    @Test
    public void testGetClassNumber () {
        int b = MainC.getClass_number();
        if (b > 45) {
            System.out.println("Number from function GetClassNumber > 45");
        } else {
            System.out.println("Number from function GetClassNumber < 45");
        }
    }

}
