
import java.util.ArrayList;

public class Net {

    private ArrayList<Neuron> neurons;
    private double learningRatio;

    public Net(double learningRatio)			//Used only the first instance to set the learningRatio
    {
    	this.learningRatio=learningRatio;
        neurons = new ArrayList<>();
    }
    
    public Net()
    {
        neurons = new ArrayList<>();
    }


    public void setNeurons(int num)				//creates the hidden layer of neurons
    {
        for (int i=0; i<num; i++)
        {
            neurons.add(new Neuron(learningRatio));
        }
    }

    public void setUpNetwork(ArrayList<Integer> inputs)
    {											//runs through each neuron to add new inputs or add on
        for (Neuron n : neurons)
        {
            n.setUpNeuron(inputs);
        }
    }
    
    public ArrayList<Double> run(ArrayList<Integer> input)
    {											//Same as getOutputs but has a specific input rather than the 26 training inputs
    	ArrayList<Double> outputs = new ArrayList<>();
    	for(Neuron n : neurons)
    	{
    		outputs.add(n.run(input));
    	}
    	return outputs;
    }

    public ArrayList<Double> getOutputs()		//runs through all the neurons to get their output in an arraylist
    {
        ArrayList<Double> outputs = new ArrayList<>();
        for(Neuron n : neurons)
        {
            outputs.add(n.getOutput());
        }
        return outputs;
    }

    
    public void activateLayers(ArrayList<Double> correctOutput)
    {
        for (int i = 0; i < neurons.size(); i++)
        {										//gets the net error from the neuron weight and
        										//what was supposed to be reached, calls backprop
            double delta = correctOutput.get(i)-neurons.get(i).getOutput();
            neurons.get(i).backprop(delta);
        }
    }

}