package math_ai;

import math_ai.core.*;
import math_ai.regression.*;
import math_ai.neural.*;


public class Main {
    public static void main(String[] args) {
        
        // ====== Regressione lineare 1D: y = 2x ======
        InputValue[] X = {
            new InputValue(1),
            new InputValue(2),
            new InputValue(3),
            new InputValue(4)
        };
        double[] Y = {2, 4, 6, 8};

        LinearRegression1D lr = new LinearRegression1D();
        lr.train(X, Y, 0.01, 3000);

        System.out.println("\nRegressione:");
        System.out.println(lr);
        System.out.println("x = 5 -> y ≈ " + lr.predict(new InputValue(5)));

        // ====== XOR con rete neurale ======
        InputValue[] Xxor = {
            new InputValue(0,0),
            new InputValue(0,1),
            new InputValue(1,0),
            new InputValue(1,1)
        };
        double[] Yxor = {0, 1, 1, 0};

        NeuralNetwork nn = new NeuralNetwork();
        nn.add(new NeuralLayer(2, 3, Activation.SIGMOID));
        nn.add(new NeuralLayer(3, 1, Activation.SIGMOID));

        nn.train(Xxor, Yxor, 0.1, 10000);

        System.out.println("\nXOR:");
        System.out.println("0 XOR 0 -> " + nn.predict(new InputValue(0,0)));
        System.out.println("0 XOR 1 -> " + nn.predict(new InputValue(0,1)));
        System.out.println("1 XOR 0 -> " + nn.predict(new InputValue(1,0)));
        System.out.println("1 XOR 1 -> " + nn.predict(new InputValue(1,1)));
    }
}
