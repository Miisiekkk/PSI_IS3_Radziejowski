
import java.util.concurrent.ThreadLocalRandom;

public class Neuron {
    private double[] weight;

    Neuron() {
        weight = new double[NeuralNetwork.input_tabSize];
        for (int i = 0; i < NeuralNetwork.input_tabSize; i++) {
            weight[i] = ThreadLocalRandom.current().nextDouble(0.01, 0.1);
        }
        normalizeWeights();
    }

    public double compute(Data data) {

        double signal = signalF(data);
        return activationFunction(signal);
    }

    private double activationFunction(double signal) {
        return signal;
    }

    public void modifyWeights(Data data) {
        for (int i = 0; i < NeuralNetwork.input_tabSize; i++) {
            weight[i] = weight[i] + NeuralNetwork.learningRate * (data.getXi(i) - weight[i]);
        }
        normalizeWeights();
    }

    private double signalF(Data data) {
        double signal = 0;
        for (int i = 0; i < NeuralNetwork.input_tabSize; i++) {
            signal += data.getXi(i) * weight[i];
        }

        return signal;
    }

    private void normalizeWeights() {
        double lenghtSquared = weight[0] * weight[0] + weight[1] * weight[1] + weight[2] * weight[2] + weight[3] * weight[3];
        double lenght = Math.sqrt(lenghtSquared);

        weight[0] /= lenght;
        weight[1] /= lenght;
        weight[2] /= lenght;
        weight[3] /= lenght;
    }
}