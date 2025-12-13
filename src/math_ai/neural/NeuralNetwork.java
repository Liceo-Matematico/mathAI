package math_ai.neural;

import math_ai.core.*;
import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork extends Model implements Trainable {

    private List<Layer> layers = new ArrayList<>();

    public void add(Layer l) {
        layers.add(l);
    }

    @Override
    public double predict(InputValue x) {
        double[] out = x.getVector();
        for (Layer l : layers) {
            out = l.forward(out);
        }
        return out[0]; // output a 1 neurone
    }

    @Override
    public double predictTrain(InputValue x) {
        return predict(x);
    }

    @Override
    public void train(InputValue[] X, double[] Y, double lr, int epochs) {

        for (int e = 0; e < epochs; e++) {

            for (int i = 0; i < X.length; i++) {

                // Forward
                double[] out = X[i].getVector();
                for (Layer l : layers) {
                    out = l.forward(out);
                }

                double pred   = out[0];
                double target = Y[i];

                // gradiente iniziale (MSE: dL/dy = y_pred - y)
                double[] grad = new double[]{ pred - target };

                // Backward
                for (int j = layers.size() - 1; j >= 0; j--) {
                    grad = layers.get(j).backward(grad, lr);
                }
            }

            if (e % 100 == 0) {
                System.out.println("Epoch " + e + " | loss=" + loss(X, Y));
            }
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\nNumero di layer: " + layers.size();
    }
}
