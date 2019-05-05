import org.moeaj.core.solution.MBinarySolution;
import org.moeaj.core.solution.MIntegerSolution;
import org.moeaj.core.solution.MSolution;
import org.moeaj.util.ConverterUtils;

public class Launcher {

    public static void main(String[] args) {
        System.out.println("oi");


        MSolution<?> sol = new MIntegerSolution(5,10);

        System.out.println(ConverterUtils.toJson(sol, MSolution.class));
        
        String content = "{\"type\":\"org.moeaj.core.solution.MIntegerSolution\",\"lowerBounds\":[0,0,0,0,0,0,0,0,0,0],\"upperBounds\":[10,10,10,10,10,10,10,10,10,10],\"objectives\":[0.0,0.0],\"variables\":[0,4,10,10,6,0,5,2,5,10],\"attributes\":{}}";
        
        MSolution s = ConverterUtils.fromJson(content, MSolution.class);
        
        System.out.println(s);
    }
}