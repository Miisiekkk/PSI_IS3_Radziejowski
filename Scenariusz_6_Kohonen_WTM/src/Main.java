
public class Main {

    public static void main(String[] args) {
        System.out.println("Scenariusz 6 - Budowa i dzialanie sieci Kohonena dla WTM");

        NeuralNetwork network = new NeuralNetwork(30);

        network.learn();
        network.test();
    }
}