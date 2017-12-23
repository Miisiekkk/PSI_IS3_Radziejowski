
import java.util.concurrent.ThreadLocalRandom;

public class Neuron {
    private double[] weight;

    Neuron()
    {
        weight = new double[NeuralNetwork.record_vectorsize];
        for(int i = 0; i < NeuralNetwork.record_vectorsize; i++) {
            weight[i] = ThreadLocalRandom.current().nextDouble(0.01, 0.1);
        }
        normalizeWeights();
    }

    public double compute(Data data){

        double signal = signalF(data);
        return activationFunction(signal);
    }

    private double activationFunction(double signal){
        double result;

        result = 1.0 / (Math.pow(signal, 0.5));

        return result;
    }

    public void modifyWeights(Data data, double alfa, double theta) {
        for (int i = 0; i < NeuralNetwork.record_vectorsize; i++) {
            weight[i] = weight[i] + alfa * theta *  (data.getXi(i) - weight[i]);
        }
        normalizeWeights();
    }

    private double signalF(Data data){
        double signal = 0;
        for(int i = 0; i < NeuralNetwork.record_vectorsize; i++) {
            signal += data.getXi(i) * weight[i];
        }

        return signal;
    }

    private void normalizeWeights(){
        double lenghtSquared = 0.0;
        double lenght;

        for(int i = 0; i < weight.length; i++) {
            lenghtSquared += Math.pow(weight[i], 2);
        }

        lenght = Math.sqrt(lenghtSquared);

        for(int i=0; i<weight.length; i++) {
            weight[i] /= lenght;
        }
    }

    public double getWeightI(int i) {
        return weight[i];
    }
}