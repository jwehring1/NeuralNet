import java.util.ArrayList;

public class NeuronPair {

    private ArrayList<Integer> inputs;
    private ArrayList<Double> outputs;

    public NeuronPair(ArrayList<Integer> inputs, ArrayList<Double> outputs)
    {													//Created own container to keep inputs and outputs for a neuron
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public ArrayList<Integer> getInputs()
    {
        return inputs;
    }

    public ArrayList<Double> getOutputs()
    {
        return outputs;
    }
}