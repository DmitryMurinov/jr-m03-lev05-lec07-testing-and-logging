import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.internal.MockedStaticImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HorseTest {

    @Test
    public void testConstructorArguments_passNull_throwsIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1.0));
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1.0, 1.0));
    }


    @Test
    public void testConstructor_passNull_haveExpectedMessage(){
        try{
            new Horse(null, 1.0);
        } catch (IllegalArgumentException e){
            assertEquals("Name cannot be null.", e.getMessage());
        }

        try{
            new Horse(null, 1.0, 1.0);
        } catch (IllegalArgumentException e){
            assertEquals("Name cannot be null.", e.getMessage());
        }
    }


    @ParameterizedTest
    @ValueSource(strings = {" ", "", "\t", "\n", "\r", "\r\n"})
    public void testConstructorArguments_passEmpty_throwsIllegalArgumentException(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1.0));
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1.0, 1.0));
    }


    @ParameterizedTest
    @ValueSource(strings = {" ", "", "\t", "\n", "\r", "\r\n"})
    public void testConstructorT_passEmpty_haveExpectedMessage(String name){
        try{
            new Horse(name, 1.0);
        } catch (IllegalArgumentException e){
            assertEquals("Name cannot be blank.", e.getMessage());
        }

        try{
            new Horse(name, 1.0, 1.0);
        } catch (IllegalArgumentException e){
            assertEquals("Name cannot be blank.", e.getMessage());
        }
    }


    @Test
    public void testConstructorArguments_passSecondWrong_throwsIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> new Horse("Fastie",   -1.0));
        assertThrows(IllegalArgumentException.class, () -> new Horse("Beckie", -1.0, 1.0));
    }


    @Test
    public void testConstructorArguments_passSecondWrong_haveExpectedMessage(){
        try{
            new Horse("Fastie",   -1.0);
        } catch (IllegalArgumentException e){
            assertEquals("Speed cannot be negative.", e.getMessage());
        }

        try{
            new Horse("Beckie", -1.0, 1.0);
        } catch (IllegalArgumentException e){
            assertEquals("Speed cannot be negative.", e.getMessage());
        }
    }


    @Test
    public void testConstructorArguments_passThirdWrong_throwsIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> new Horse("Beckie", 1.0, -1.0));
    }


    @Test
    public void testConstructorArguments_passThirdWrong_haveExpectedMessage(){
        try{
            new Horse("Beckie", 1.0, -1.0);
        } catch (IllegalArgumentException e){
            assertEquals("Distance cannot be negative.", e.getMessage());
        }
    }


    @Test
    public void testGetName_returnsExpected(){
        Horse horse = new Horse("Beckie", 1.0);
        assertEquals("Beckie", horse.getName());
    }


    @Test
    public void testGetSpeed_returnsExpected(){
        Horse horse = new Horse("Beckie", 1.0);
        assertEquals(1.0, horse.getSpeed());
    }


    @Test
    public void testGetDistance_horseTwoParams_returnsExpected(){
        Horse horse = new Horse("Beckie", 1.0);
        assertEquals(0.0, horse.getDistance());
    }


    @Test
    public void testGetDistance_horseThreeParams_returnsExpected(){
        Horse horse = new Horse("Beckie", 1.0, 2.5);
        assertEquals(2.5, horse.getDistance());
    }


    @Test
    public void testMove_callsGetRandomDouble_withExpectedValues(){
        try(MockedStatic<Horse> mockedHorse = Mockito.mockStatic(Horse.class)){

            Horse horse = new Horse("Apple", 1.0, 3.5);
            horse.move();

            mockedHorse.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }


    @Test
    public void testMove_calculatesDistance_withExpectedValue(){
        try(MockedStatic<Horse> mockedHorse = Mockito.mockStatic(Horse.class)){
            mockedHorse.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);

            double speed = 1.5;
            double distance = 3.5;

            Horse horse = new Horse("Apple", speed, distance);
            horse.move();

            double distanceExpected = distance + speed * 0.5;

            assertEquals(distanceExpected, horse.getDistance());
        }
    }



}