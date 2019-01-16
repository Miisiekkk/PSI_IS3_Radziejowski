/**
 * Created by misiek on 2017-10-13.
 */

/**
 * SOURCE OF MAIN CODE: http://www.codebytes.in/2015/07/perceptron-learning-algorithm-java.html
 * Code below was edited specifically for our AI Classes. Original implementation credits are going to Dr. Noureddin Sadawi
 */

import java.util.Random;



public class perceptron {

    static int MAX_ITER = 500;
    static double LEARNING_RATE = 0.01;
    static int NUM_INSTANCES = 4;
    static int theta = 0;

    public static void main(String[] args){
        Random randGenerator = new Random();

        //three variables (features)
        int[] x = new int[NUM_INSTANCES];
        int[] y = new int[NUM_INSTANCES];
        int[] outputs = new int[NUM_INSTANCES];

        x[0] = 0; y[0] = 0;
        x[1] = 1; y[1] = 0;
        x[2] = 0; y[2] = 1;
        x[3] = 1; y[3] = 1;

        outputs[0] = 1;
        outputs[1] = 0;
        outputs[2] = 0;
        outputs[3] = 0;

        double[] weights = new double[3]; //2 for input variables and one for bias
        double localError, globalError;
        int p, iteration, output;

        weights[0] = randomNumber(-1, 1);
        weights[1] = randomNumber(-1, 1);
        weights[2] = randomNumber(-1, 1);

        int test = 1;
        iteration = 0;
        do{
            iteration++;
            globalError = 0;
            //loop through all instances (complete one epoch)
            for(p = 0;p<NUM_INSTANCES;p++){

                //calculate predicted class
                output = calculateOutput(theta, weights, x[p], y[p]);
                //difference between predicted and actual class values
                localError = outputs[p] - output;
                //update weights
                weights[0] += LEARNING_RATE*localError*x[p];
                weights[1] += LEARNING_RATE*localError*y[p];

                //update bias
                weights[2] += LEARNING_RATE*localError;

                globalError += (localError*localError);
            }
            /*Root Mean Squared Error*/ if(test == 1 && globalError == 0){test = 0;
                System.out.println("----------------------------NAUCZONY--------------------------------");
                iteration = MAX_ITER -20;}
            System.out.println("Iteration "+iteration+" : RMSE = "+Math.sqrt(globalError/NUM_INSTANCES));
            int x1 = randGenerator.nextInt(2);
            int y1 = randGenerator.nextInt(2);

            output = calculateOutput(theta, weights, x1, y1);
            System.out.println("\n=======\nNew Random Point:");
            System.out.println("x = "+x1+",y = "+y1);
            System.out.println("Works? = "+output);

        }while(iteration < MAX_ITER);

        System.out.println("\n========\nDecision boundary equation:");
        System.out.println(weights[0]+"*x "+weights[1]+"*y + "+weights[2]+" = 0");

    }

    public static double randomNumber(double min, double max){
        double d = min+Math.random()*(max-min);
        return d;
    }

    static int calculateOutput(int theta, double weights[], double x, double y){
        double sum  = x*weights[0] + y*weights[1] + weights[2];
        return sum>=theta ? 1:0;
    }
}