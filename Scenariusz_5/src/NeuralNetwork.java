/**
 * Created by miisiekkk on 2017-12-20.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class NeuralNetwork
{
    final static double learningRate = 0.05; // wspolczynnik uczenia
    private final static int Epochs = 10000;

    private final static int inputNumber = 150;
    private final static int testNumber = 45;
    final static int input_tabSize = 4;

    private Data[] learning_tabData;
    private Data[] testing_tabData;

    private Layer layer;

    NeuralNetwork(int neuronsCount)
    {
        try {
            Scanner scanner = new Scanner(new File("input.txt"));
            Scanner scanner2 = new Scanner(new File("testinput.txt"));

            layer = new Layer(neuronsCount);
            learning_tabData = new Data[inputNumber];
            testing_tabData = new Data[testNumber];
            loadData(scanner, false);
            loadData(scanner2, true);
            scanner.close();
            showData();

        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono plikow");
        }
    }

    private void loadData(Scanner s, boolean isTest) {
        int inputsCount = isTest ? testNumber : inputNumber;
        Data[] loader = new Data[inputsCount];
        for(int i = 0; i < inputsCount; i++) {
            loader[i] = new Data(input_tabSize);
            for(int j = 0; j < input_tabSize; j++) {
                loader[i].setXi(j, Double.parseDouble(s.next()));
            }
            loader[i].setY(s.next());
        }
        if (isTest) {
            testing_tabData = loader;
        } else {
            learning_tabData = loader;
        }
    }


    private void showData() {
        System.out.println("Dane zaladowano pomyslnie");
        for (int i = 0; i < inputNumber; i++) {
            for(int j = 0; j < input_tabSize; j++) {
                System.out.print(learning_tabData[i].getXi(j) + " ");
            }
            System.out.println(learning_tabData[i].getY());
        }

    }

    public void learn() {
        normalize(learning_tabData);
        normalize(testing_tabData);

        int iterations = 0;
        ArrayList<Double> result;

        while (iterations < Epochs) {
            for (int i = 0; i < inputNumber; i++) {
                result = layer.compute(learning_tabData[i]);
                layer.modify(result.indexOf(Collections.max(result)));
                result.clear();
            }
            ++iterations;
        }

        System.out.println("Uczenie zakonczone. \nLiczba epok uczenia = " + iterations + "\n\n");
    }

    public void test(){
        ArrayList<Double> result;
        ArrayList<Integer> group = new ArrayList<>();
        int winner;
        for(int i = 0; i < testNumber; i++) {

            result = layer.compute(testing_tabData[i]);
            winner = result.indexOf(Collections.max(result));

            if(!group.contains(winner)){
                group.add(result.indexOf(Collections.max(result)));
            }
            System.out.println("ID #" + i + ". Rodzaj " + testing_tabData[i].getY() + ", grupa " + winner);

        }

        System.out.println("\nLista zwycieskich grup");
        for(Integer el: group)
            System.out.println(el.toString());
    }

    private void normalize(Data[] data){
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
}