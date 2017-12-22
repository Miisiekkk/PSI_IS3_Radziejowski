/**
 * Created by miisiekkk on 2017-12-20.
 */
public class Main {

    public static void main(String[] args) {
        NeuralNetwork network = new NeuralNetwork(30);
        network.learn();
        network.test();
    }
}