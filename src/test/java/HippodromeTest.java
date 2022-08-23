import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HippodromeTest {

    @Test
    public void testConstructor_nullParameter_throwsIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }


    @Test
    public void testConstructor_nullParameter_haveExpectedMessage(){
        try{
            new Hippodrome(null);
        } catch (IllegalArgumentException e){
            assertEquals("Horses cannot be null.", e.getMessage());
        }
    }


    @Test
    public void testConstructor_emptyParameter_throwsIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }


    @Test
    public void testConstructor_emptyParameter_haveExpectedMessage(){
        try{
            new Hippodrome(new ArrayList<>());
        } catch (IllegalArgumentException e){
            assertEquals("Horses cannot be empty.", e.getMessage());
        }
    }


    @Test
    public void testGetHorses_returnExpectedList(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("HorseTestName " + i, 1.0 + i, 2.5 + i));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }


    @Test
    public void testMove_calledOnAllHorses(){
        List<Horse> horses = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.spy(new Horse("HorseTestName " + i, 1.0 + i, 2.5 + i)));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for(Horse horse : horses){
            Mockito.verify(horse).move();
        }
    }


    @Test
    public void testGetWinner_returnHorseWithMaxDistance(){
        List<Horse> horses = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            int distance = ThreadLocalRandom.current().nextInt(0, 6);
            horses.add(new Horse("HorseTestName " + i, 1.0 + i, 2.5 + distance));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        Horse expectedWinner = horses
                .stream()
                .max(Comparator.comparing(Horse::getDistance))
                .orElseThrow();

        assertEquals(expectedWinner, hippodrome.getWinner());
    }

}
