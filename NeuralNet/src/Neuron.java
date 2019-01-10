import java.util.*;
import java.util.ArrayList;

public class Neuron {
    
    private double learningRatio;
    private ArrayList<Double> weights;
    private ArrayList<Integer> inputs;
    private double bias;
    private double output;

    public Neuron(double learningRatio)				//Used only the first instance to set the learningRatio
    {
    	this.weights = new ArrayList<>();
        this.inputs = new ArrayList<>();
        this.learningRatio = learningRatio;
        this.bias = Math.random();
    }
    
    public Neuron(){}

    public void setUpNeuron(ArrayList<Integer> inputs)
    {
        if(this.inputs.size()==0)				//Makes sure weights are set to random only the first time
        {											//Trained weights should not be overridden with random weights
            this.inputs = new ArrayList<>(inputs);
            randomWeights();
        }
        else
        {
        	this.inputs = new ArrayList<>(inputs);
        }
    }

    private void randomWeights()
    {												//For the first instance we set a random weight
        for(int i=0; i<inputs.size(); i++)
        {
            weights.add(Math.random());
        }
    }

    public void calculateOutput()
    {
        double sum=0;								//Calculates the output by the formula sigmoid(input*weight+bias)
        for(int i=0; i<inputs.size(); i++)
        {
            sum += inputs.get(i)*weights.get(i);
        }
        sum+=bias;
        output=sigmoid(sum);
    }
    
    public double run(ArrayList<Integer> input)
    {
        double sum=0;								//Same as calculateOutput but finding the final answer using one input rather than											
        											//The 26 used to train
        for(int i=0; i<input.size(); i++)
        {
            sum+=input.get(i)*weights.get(i);
        }
        sum+=bias;
        return sigmoid(sum);
    }
    
    public double sigmoid(double z)
    {
    	return (1/(1+Math.exp(-z)));
    }
    
    public double tanh(double z)
    {
    	return (2*sigmoid(2*z))-1;
    }

    public void backprop(double delta)
    {
        for (int i=0; i<inputs.size(); i++)		//Backwards propagation, net error or delta is found through Network.changeWeights
        {
            double temp = weights.get(i)+learningRatio * delta * inputs.get(i);
            weights.set(i, temp);					//weights and bias' are corrected using the net error
        }
        bias+=learningRatio*delta;
    }

    public double getOutput()
    {
        calculateOutput();
        return output;
    }

}