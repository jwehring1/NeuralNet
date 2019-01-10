
import java.util.ArrayList;
import java.util.Collections;

public class Learning
{

    private int numNeurons = 26;
    private int choice=0;
    private Net network;
    private ArrayList<NeuronPair> neuronInOut = new ArrayList<>();

    public Learning(double learningRatio,int choice)	//Creates the layers and starts learning
    {													
        this.network = new Net(learningRatio);
        this.network.setNeurons(numNeurons);
        this.choice=choice;
        getNeuronPairs();
        if(choice==0||choice==3)
        {
        	learn();
        }
        else if(choice==1)
        {
        	plotLearn();
        }
    }

    public void learn()									//Runs through the alphabet multiple times to make sure the weights are set correctly
    {
        for (int i = 0; i < 260; i++)
        {
            NeuronPair neuronIO = neuronInOut.get(i%26);
            network.setUpNetwork(neuronIO.getInputs());
            network.activateLayers(neuronIO.getOutputs());
        }
    }
    
    public void plotLearn()								//Runs through the alphabet multiple times and plots learning by printing out confidence at every pass
    {
    	for (int i = 0; i < 260; i++)
        {
    		
            NeuronPair neuronIO = neuronInOut.get(i%26);
            
            network.setUpNetwork(neuronIO.getInputs());
            ArrayList<Double> output= network.run(neuronIO.getInputs());
            System.out.println("Learning Stage " +(int)(i/26+1)+": "+(char)((i%26)+65)+" Confidence: "+output.get(i%26));
            network.activateLayers(neuronIO.getOutputs());
        }
    }
    
    public boolean noiseRun(ArrayList<Integer> input, char letter)
    {
    	ArrayList<Double> output= network.run(input);
    	double max=-99999999;int save=0;
    	for(int i=0; i<output.size();i++)			//Gets the letter it thinks it is and then is compared with the letter we want	
    	{
    		if(output.get(i)>max)
    		{
    			max=output.get(i);
    			save = i;
    		}
    	}

    	if((char)(save+65)!=letter)
    	{
    		return false;
    	}
    	return true;
    }
    
    public void run(ArrayList<Integer> input)		//Runs through the network and gets the output, finds the max letter to output
    {
    	ArrayList<Double> output= network.run(input);
    	double max=-99999999;int save=0;
    	for(int i=0; i<output.size();i++)
    	{
    		if(output.get(i)>max)
    		{
    			max=output.get(i);
    			save = i;
    		}
    	}
    	System.out.println("max: "+max+" at: "+(char)(save+65));
    }
    
    public void getNeuronPairs()					//Adds the alphabet and the output to the arraylist of NeuronPairs
    {
    	Integer[] a_1 = {0,0,1,0,0,0,1,0,1,0,1,0,0,0,1,1,0,0,0,1,1,1,1,1,1,1,0,0,0,1,1,0,0,0,1};
	    Integer[] b_1 = {1,1,1,1,0,0,1,0,0,1,0,1,0,0,1,0,1,1,1,0,0,1,0,0,1,0,1,0,0,1,1,1,1,1,0};
	    Integer[] c_1 = {0,1,1,1,0,1,0,0,0,1,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,1,0,1,1,1,0};
	    Integer[] d_1 = {1,1,1,1,0,0,1,0,0,1,0,1,0,0,1,0,1,0,0,1,0,1,0,0,1,0,1,0,0,1,1,1,1,1,0};
	    Integer[] e_1 = {1,1,1,1,1,1,0,0,0,0,1,0,0,0,0,1,1,1,1,0,1,0,0,0,0,1,0,0,0,0,1,1,1,1,1};
	    Integer[] f_1 = {1,1,1,1,1,1,0,0,0,0,1,0,0,0,0,1,1,1,1,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0};
	    Integer[] g_1 = {0,1,1,1,0,1,0,0,0,1,1,0,0,0,0,1,0,0,1,1,1,0,0,0,1,1,0,0,0,1,0,1,1,1,1};
	    Integer[] h_1 = {1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,1,1,1,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1};
	    Integer[] i_1 = {0,1,1,1,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,1,1,1,0};
	    Integer[] j_1 = {0,0,1,1,1,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,1,0,0,1,0,0,1,1,0,0};
	    Integer[] k_1 = {1,0,0,0,1,1,0,0,1,0,1,0,1,0,0,1,1,0,0,0,1,0,1,0,0,1,0,0,1,0,1,0,0,0,1};
	    Integer[] l_1 = {1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,1,1,1,1};
	    Integer[] m_1 = {1,0,0,0,1,1,1,0,1,1,1,0,1,0,1,1,0,1,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1};
	    Integer[] n_1 = {1,0,0,0,1,1,0,0,0,1,1,1,0,0,1,1,0,1,0,1,1,0,0,1,1,1,0,0,0,1,1,0,0,0,1};
	    Integer[] o_1 = {0,1,1,1,0,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,0,1,1,1,0};
	    Integer[] p_1 = {1,1,1,1,0,1,0,0,0,1,1,0,0,0,1,1,1,1,1,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0};
	    Integer[] q_1 = {0,1,1,1,0,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,1,0,1,1,0,0,1,0,0,1,1,0,1};
	    Integer[] r_1 = {1,1,1,1,0,1,0,0,0,1,1,0,0,0,1,1,1,1,1,0,1,0,1,0,0,1,0,0,1,0,1,0,0,0,1};
	    Integer[] s_1 = {0,1,1,1,0,1,0,0,0,1,1,0,0,0,0,0,1,1,1,0,0,0,0,0,1,1,0,0,0,1,0,1,1,1,0};
	    Integer[] t_1 = {1,1,1,1,1,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0};
	    Integer[] u_1 = {1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,0,1,1,1,0};
	    Integer[] v_1 = {1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,0,1,0,1,0,0,0,1,0,0};
	    Integer[] w_1 = {1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,1,0,1,1,0,1,0,1,1,0,1,0,1,0,1,0,1,0};
	    Integer[] x_1 = {1,0,0,0,1,1,0,0,0,1,0,1,0,1,0,0,0,1,0,0,0,1,0,1,0,1,0,0,0,1,1,0,0,0,1};
	    Integer[] y_1 = {1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,0,1,0,1,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0};
	    Integer[] z_1 = {1,1,1,1,1,0,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,0,1,1,1,1,1};
	    
	    Double[] a_2 = {1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
        Double[] b_2 = {0.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
        Double[] c_2 = {0.0,0.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
        Double[] d_2 = {0.0,0.0,0.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
        Double[] e_2 = {0.0,0.0,0.0,0.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
        Double[] f_2 = {0.0,0.0,0.0,0.0,0.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
        Double[] g_2 = {0.0,0.0,0.0,0.0,0.0,0.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
        Double[] h_2 = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
        Double[] i_2 = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
        Double[] j_2 = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
        Double[] k_2 = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
        Double[] l_2 = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
        Double[] m_2 = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
        Double[] n_2 = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
        Double[] o_2 = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
        Double[] p_2 = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
        Double[] q_2 = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
        Double[] r_2 = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
        Double[] s_2 = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
        Double[] t_2 = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0};
        Double[] u_2 = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0,0.0,0.0,0.0,0.0,0.0};
        Double[] v_2 = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0,0.0,0.0,0.0,0.0};
        Double[] w_2 = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0,0.0,0.0,0.0};
        Double[] x_2 = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0,0.0,0.0};
        Double[] y_2 = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0,0.0};
        Double[] z_2 = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0};
        
        ArrayList<Integer> a_in = new ArrayList<>();	//Was already committed to using ArrayLists in the code
        Collections.addAll(a_in, a_1);					//There is no declaring an ArrayList with an already set array
        ArrayList<Integer> b_in = new ArrayList<>();	//Therefore I had to create arrays and use addAll to copy them over
        Collections.addAll(b_in, b_1);					//This was the most efficient route in the predicament I was in.
        ArrayList<Integer> c_in = new ArrayList<>();
        Collections.addAll(c_in, c_1);
        ArrayList<Integer> d_in = new ArrayList<>();
        Collections.addAll(d_in, d_1);
        ArrayList<Integer> e_in = new ArrayList<>();
        Collections.addAll(e_in, e_1);
        ArrayList<Integer> f_in = new ArrayList<>();
        Collections.addAll(f_in, f_1);
        ArrayList<Integer> g_in = new ArrayList<>();
        Collections.addAll(g_in, g_1);
        ArrayList<Integer> h_in = new ArrayList<>();
        Collections.addAll(h_in, h_1);
        ArrayList<Integer> i_in = new ArrayList<>();
        Collections.addAll(i_in, i_1);
        ArrayList<Integer> j_in = new ArrayList<>();
        Collections.addAll(j_in, j_1);
        ArrayList<Integer> k_in = new ArrayList<>();
        Collections.addAll(k_in, k_1);
        ArrayList<Integer> l_in = new ArrayList<>();
        Collections.addAll(l_in, l_1);
        ArrayList<Integer> m_in = new ArrayList<>();
        Collections.addAll(m_in, m_1);
        ArrayList<Integer> n_in = new ArrayList<>();
        Collections.addAll(n_in, n_1);
        ArrayList<Integer> o_in = new ArrayList<>();
        Collections.addAll(o_in, o_1);
        ArrayList<Integer> p_in = new ArrayList<>();
        Collections.addAll(p_in, p_1);
        ArrayList<Integer> q_in = new ArrayList<>();
        Collections.addAll(q_in, q_1);
        ArrayList<Integer> r_in = new ArrayList<>();
        Collections.addAll(r_in, r_1);
        ArrayList<Integer> s_in = new ArrayList<>();
        Collections.addAll(s_in, s_1);
        ArrayList<Integer> t_in = new ArrayList<>();
        Collections.addAll(t_in, t_1);
        ArrayList<Integer> u_in = new ArrayList<>();
        Collections.addAll(u_in, u_1);
        ArrayList<Integer> v_in = new ArrayList<>();
        Collections.addAll(v_in, v_1);
        ArrayList<Integer> w_in = new ArrayList<>();
        Collections.addAll(w_in, w_1);
        ArrayList<Integer> x_in = new ArrayList<>();
        Collections.addAll(x_in, x_1);
        ArrayList<Integer> y_in = new ArrayList<>();
        Collections.addAll(y_in, y_1);
        ArrayList<Integer> z_in = new ArrayList<>();
        Collections.addAll(z_in, z_1);
        ArrayList<Double> a_out = new ArrayList<>();
        Collections.addAll(a_out, a_2);
        ArrayList<Double> b_out = new ArrayList<>();
        Collections.addAll(b_out, b_2);
        ArrayList<Double> c_out = new ArrayList<>();
        Collections.addAll(c_out, c_2);
        ArrayList<Double> d_out = new ArrayList<>();
        Collections.addAll(d_out, d_2);
        ArrayList<Double> e_out = new ArrayList<>();
        Collections.addAll(e_out, e_2);
        ArrayList<Double> f_out = new ArrayList<>();
        Collections.addAll(f_out, f_2);
        ArrayList<Double> g_out = new ArrayList<>();
        Collections.addAll(g_out, g_2);
        ArrayList<Double> h_out = new ArrayList<>();
        Collections.addAll(h_out, h_2);
        ArrayList<Double> i_out = new ArrayList<>();
        Collections.addAll(i_out, i_2);
        ArrayList<Double> j_out = new ArrayList<>();
        Collections.addAll(j_out, j_2);
        ArrayList<Double> k_out = new ArrayList<>();
        Collections.addAll(k_out, k_2);
        ArrayList<Double> l_out = new ArrayList<>();
        Collections.addAll(l_out, l_2);
        ArrayList<Double> m_out = new ArrayList<>();
        Collections.addAll(m_out, m_2);
        ArrayList<Double> n_out = new ArrayList<>();
        Collections.addAll(n_out, n_2);
        ArrayList<Double> o_out = new ArrayList<>();
        Collections.addAll(o_out, o_2);
        ArrayList<Double> p_out = new ArrayList<>();
        Collections.addAll(p_out, p_2);
        ArrayList<Double> q_out = new ArrayList<>();
        Collections.addAll(q_out, q_2);
        ArrayList<Double> r_out = new ArrayList<>();
        Collections.addAll(r_out, r_2);
        ArrayList<Double> s_out = new ArrayList<>();
        Collections.addAll(s_out, s_2);
        ArrayList<Double> t_out = new ArrayList<>();
        Collections.addAll(t_out, t_2);
        ArrayList<Double> u_out = new ArrayList<>();
        Collections.addAll(u_out, u_2);
        ArrayList<Double> v_out = new ArrayList<>();
        Collections.addAll(v_out, v_2);
        ArrayList<Double> w_out = new ArrayList<>();
        Collections.addAll(w_out, w_2);
        ArrayList<Double> x_out = new ArrayList<>();
        Collections.addAll(x_out, x_2);
        ArrayList<Double> y_out = new ArrayList<>();
        Collections.addAll(y_out, y_2);
        ArrayList<Double> z_out = new ArrayList<>();
        Collections.addAll(z_out, z_2);
        
        NeuronPair a = new NeuronPair(a_in,a_out);		//Creates a new neuronPair and adds it to the training
        NeuronPair b = new NeuronPair(b_in,b_out);
        NeuronPair c = new NeuronPair(c_in,c_out);
        NeuronPair d = new NeuronPair(d_in,d_out);
        NeuronPair e = new NeuronPair(e_in,e_out);
        NeuronPair f = new NeuronPair(f_in,f_out);
        NeuronPair g = new NeuronPair(g_in,g_out);
        NeuronPair h = new NeuronPair(h_in,h_out);
        NeuronPair i = new NeuronPair(i_in,i_out);
        NeuronPair j = new NeuronPair(j_in,j_out);
        NeuronPair k = new NeuronPair(k_in,k_out);
        NeuronPair l = new NeuronPair(l_in,l_out);
        NeuronPair m = new NeuronPair(m_in,m_out);
        NeuronPair n = new NeuronPair(n_in,n_out);
        NeuronPair o = new NeuronPair(o_in,o_out);
        NeuronPair p = new NeuronPair(p_in,p_out);
        NeuronPair q = new NeuronPair(q_in,q_out);
        NeuronPair r = new NeuronPair(r_in,r_out);
        NeuronPair s = new NeuronPair(s_in,s_out);
        NeuronPair t = new NeuronPair(t_in,t_out);
        NeuronPair u = new NeuronPair(u_in,u_out);
        NeuronPair v = new NeuronPair(v_in,v_out);
        NeuronPair w = new NeuronPair(w_in,w_out);
        NeuronPair x = new NeuronPair(x_in,x_out);
        NeuronPair y = new NeuronPair(y_in,y_out);
        NeuronPair z = new NeuronPair(z_in,z_out);
        
        neuronInOut.add(a);
        neuronInOut.add(b);
        neuronInOut.add(c);
        neuronInOut.add(d);
        neuronInOut.add(e);
        neuronInOut.add(f);
        neuronInOut.add(g);
        neuronInOut.add(h);
        neuronInOut.add(i);
        neuronInOut.add(j);
        neuronInOut.add(k);
        neuronInOut.add(l);
        neuronInOut.add(m);
        neuronInOut.add(n);
        neuronInOut.add(o);
        neuronInOut.add(p);
        neuronInOut.add(q);
        neuronInOut.add(r);
        neuronInOut.add(s);
        neuronInOut.add(t);
        neuronInOut.add(u);
        neuronInOut.add(v);
        neuronInOut.add(w);
        neuronInOut.add(x);
        neuronInOut.add(y);
        neuronInOut.add(z);
    }

}