
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class NeuralNetwork {
    final static double learning_rate = 0.1; // wspolczynnik uczenia
    final static double promien_sigma = 30; // poczatkowy promien sasiedztwa
    private final static int epochs = 10000;

    private int neuron_number;
    private final static int input_number = 20;
    private final static int testinput_number = 20;
    final static int record_vectorsize = 35;

    private Data[] learningtab_data;
    private Data[] testingtab_data;

    private final char letter_tab[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T'};

    private Layer layer;

    NeuralNetwork(int neuron_number) {
        this.neuron_number = neuron_number;
        try {
            Scanner scanner = new Scanner(new File("input.txt"));
            Scanner scanner2 = new Scanner(new File("testinput.txt"));

            layer = new Layer(neuron_number);
            learningtab_data = new Data[input_number];
            testingtab_data = new Data[testinput_number];
            loadData(scanner, false);
            loadData(scanner2, true);
            scanner.close();
            showData();

        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono plików");
        }
    }

    private void loadData(Scanner s, boolean isTest) {
        int inputsCount = isTest ? testinput_number : input_number;
        Data[] cyrk = new Data[inputsCount];
        for (int i = 0; i < inputsCount; i++) {
            cyrk[i] = new Data(record_vectorsize);
            cyrk[i].setY(s.next());
            for (int j = 0; j < record_vectorsize; j++) {
                cyrk[i].setXi(j, Double.parseDouble(s.next()));
            }
        }
        if (isTest) {
            testingtab_data = cyrk;
        } else {
            learningtab_data = cyrk;
        }
    }


    private void showData() {
        System.out.println("Wczytano 20 liter");
        System.out.println();
        for (int i = 0; i < 20; i++) {
            System.out.println(letter_tab[i]);
            for (int j = 1; j <= 35; j++) {
                if ((int) learningtab_data[i].getXi(j-1) > 0) {
                    System.out.print(1);
                } else {
                    System.out.print(0);
                }
                if (j % 5 == 0) {
                    System.out.println();
                }
            }
            System.out.println();
        }
    }

    public void learn() {
        normalize(learningtab_data);

        int iterations = 0;
        int winner;

        double sigma, theta, alfa = 0;
        int sigmaRounded;

        ArrayList<Double> result;

        do {
            alfa = alfaFunction(iterations);
            sigma = sigmaFunction(iterations);
            sigmaRounded = (int) Math.round(sigma);

            for (int i = 0; i < input_number; i++) {
                result = layer.computeLayer(learningtab_data[i]);
                winner = result.indexOf(Collections.min(result));

                for (int j = (-sigmaRounded); j <= sigmaRounded; ++j) {
                    if (((winner + j) >= 0) && ((winner + j) < neuron_number)) {
                        theta = thetaFunction(sigma, j);
                        layer.modify((winner + j), alfa, theta);
                    }
                }
            }
            ++iterations;
        } while (iterations < epochs);

        System.out.println("Uczenie zakończone. \nLiczba epok = " + iterations + "\n" +
                "Promień  = " + sigmaRounded + "\n" +
                "Współczynnik uczenia po zakończeniu = " +alfa + "\n\n");
    }

    public void test() {
        double globalError = 0.0;
        normalize(testingtab_data);
        ArrayList<Double> result;
        ArrayList<Double> errors = new ArrayList<Double>();
        ArrayList<LetterMap> groups = new ArrayList<LetterMap>();
        int winner;
        for (int i = 0; i < testinput_number; i++) {

            result = layer.computeLayer(testingtab_data[i]);

            winner = result.indexOf(Collections.min( result ));
            errors.add(Collections.min( result ));

            boolean flag = true;
            for(LetterMap el : groups){
                if(el.id == winner) {
                    el.letters += testingtab_data[i].getY() + ", ";
                    flag = false;
                }
            }

            if(flag){
                groups.add(new LetterMap(winner, (testingtab_data[i].getY() + ", " )));
            }

            System.out.println(" Litera: " + testingtab_data[i].getY() + " Numer neuronu " + winner);

        }

        System.out.print("\n\nWyszczególnione grupy\n");

        for(LetterMap el: groups)
            System.out.println("Nr " + el.id + " Litery: " + el.letters);

        for(Double el: errors)
            globalError += el;

        globalError = (1.0 / (double) testinput_number) * globalError;

        System.out.println("\nGlobal Error = " +globalError);

    }

    private void normalize(Data[] data) {
        for (Data aData : data) {
            double lenght = aData.getXi(0) * aData.getXi(0) +
                    aData.getXi(1) * aData.getXi(1) +
                    aData.getXi(2) * aData.getXi(2) +
                    aData.getXi(3) * aData.getXi(3);
            lenght = Math.sqrt(lenght);

            aData.setXi(0, aData.getXi(0) / lenght);
            aData.setXi(1, aData.getXi(1) / lenght);
            aData.setXi(2, aData.getXi(2) / lenght);
            aData.setXi(3, aData.getXi(3) / lenght);
        }
    }

    private double alfaFunction(int counter) {
        return (learning_rate * Math.exp(-((double) counter) / ((double) epochs)));
    }

    private double sigmaFunction(int counter) {
        return (promien_sigma * Math.exp(-((double) counter) / ((double) epochs)));
    }

    private double thetaFunction(double sigma, int r) {
        return Math.exp(-(Math.pow(r, 2)) / (2 * Math.pow(sigma, 2)));
    }

    private class LetterMap{
        public int id;
        public String letters;
        public LetterMap(int id, String letters){this.id = id; this.letters = letters;}
    }
}