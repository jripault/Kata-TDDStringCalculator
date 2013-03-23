import calc.StringCalculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.fest.assertions.api.Assertions.*;

/**
 * User: JRI <julien.ripault@atos.net>
 * Date: 17/03/13
 */
@RunWith(JUnit4.class)
public class CalculatorTest {
    @Test
    public void shouldReturnZeroForEmptyString(){
        StringCalculator calc = new StringCalculator();
        int result = calc.add("");
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void shouldReturnNumberForOneNumber(){
        StringCalculator calc = new StringCalculator();
        int result = calc.add("1");
        assertThat(result).isEqualTo(1);
    }
    @Test
    public void shouldSumTwoNumbers(){
        StringCalculator calc = new StringCalculator();
        int result = calc.add("1,2");
        assertThat(result).isEqualTo(3);
    }
    @Test
    public void shouldSumAnyAmountOfNumbers(){
        StringCalculator calc = new StringCalculator();
        int result = calc.add("1,2,3,4,5");
        assertThat(result).isEqualTo(15);
    }
    @Test
    public void shouldSupportNewLineInInput(){
        StringCalculator calc = new StringCalculator();
        int result = calc.add("1\n2,3");
        assertThat(result).isEqualTo(6);
    }
    @Test
    public void shouldSupportDelimiter(){
        StringCalculator calc = new StringCalculator();
        int result = calc.add("//[;]\n1;2");
        assertThat(result).isEqualTo(3);
    }
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfNegativeNumber(){
        StringCalculator calc = new StringCalculator();
        calc.add("//[;]\n-1;2");
    }
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfNegativeNumbers(){
        StringCalculator calc = new StringCalculator();
        calc.add("//[;]\n-1;-2");
    }
    @Test
    public void shouldNotAddBigNumbers(){
        StringCalculator calc = new StringCalculator();
        int result = calc.add("//[;]\n2;1001");
        assertThat(result).isEqualTo(2);
    }
    @Test
    public void shouldSupportMultipleCharactersDelimiter(){
        StringCalculator calc = new StringCalculator();
        int result = calc.add("//[%%%]\n1%%%2%%%3");
        assertThat(result).isEqualTo(6);
    }
}
