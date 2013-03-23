package calc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: JRI <julien.ripault@atos.net>
 * Date: 17/03/13
 */
public class StringCalculator {
    private String delimiter = ",";
    public StringCalculator() {

    }

    public int add(String params) {
        if ("".equals(params)) {
            return 0;
        }
        List<String> values = extractValues(params);
        return computeValues(values);
    }

    private List<String> extractValues(String param) {
        List<String> valuesWithoutDelimiter = extractDelimiter(param);
        List<String> values = new ArrayList<>();
        for (String tempValue : valuesWithoutDelimiter) {
            String[] tempArray = tempValue.split(delimiter);
            for (String s : tempArray) {
                values.add(s);
            }
        }

        return values;
    }

    private List<String> extractDelimiter(String param) {
        List<String> values = new ArrayList<>();
        String[] split = param.split("\\n");
        for (String s : split) {
            int index = s.indexOf("//[");
            if (index > -1) {
                int endIndex = s.indexOf("]");
                delimiter = s.substring(3, endIndex);
            } else {
                values.add(s);
            }
        }
        return values;
    }

    private int computeValues(List<String> param) {
        int result = 0;
        List<Integer> errors = new ArrayList<>();
        for (String value : param) {
            int num = Integer.parseInt(value);
            if(num < 0){
                errors.add(num);
            }
            if(num <= 1000){
                result += num;
            }
        }
        if(errors.size() > 0){
            throw new IllegalArgumentException("negatives not allowed " + errors.toString());
        }
        return result;
    }

    public static void main(String[] args){
        StringCalculator calc = new StringCalculator();
        int result = calc.add("//[%%%]\n1%%%2%%%3");
    }
}
