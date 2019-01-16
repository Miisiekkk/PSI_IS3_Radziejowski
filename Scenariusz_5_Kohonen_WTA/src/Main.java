ublic class Main {

    public static void main(String[] args) {
        NeuralNetwork network = new NeuralNetwork(30);
        network.learn();
        network.test();
    }
}