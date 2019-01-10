import java.util.*;
public class Main {
	ArrayList<ArrayList<Integer>> alphabet;
	
	public static void main(String[] args)
	{
		Main net2 = new Main();		
		net2.getInformation();
	}
	
	public void getInformation()
	{
		Scanner sc = new Scanner(System.in);						//Asks and recieves the double for learning ratio and the 5x7 letter from the user
		System.out.println("What do you want to do? (Examples: Input, PlotAccuracy, NoiseTest)");
		String choice = sc.next();
		setUpInputs();
		if(choice.equals("Input"))
		{
			System.out.println("Please specify a learning ratio.");
			String learningRatio = sc.next();
			System.out.println();
			
			Learning t = new Learning(Double.parseDouble(learningRatio),0);
			
			System.out.println("Please enter a 26 alphabet digit in the form of 5x7 0 and 1s");
			System.out.println("For example: \"0,0,1,0,0,0,1,0,1,0,1,0,0,0,1,1,0,0,0,1,1,1,1,1,1,1,0,0,0,1,1,0,0,0,1\" for the letter A or \"all\" for all the letters");
			
			Scanner newsc = new Scanner(System.in);
			String line = newsc.nextLine();
			System.out.println();
			if(line.equals("all"))
				runAll(t);
			
			String[] lineArr = line.split(",");	
			ArrayList<Integer> input = new ArrayList<Integer>();	//Splits the String into an ArrayList and then runs the letter by calling learning.run
			for(int i=0; i<35;i++)
				input.add(Integer.parseInt(lineArr[i]));
			
			t.run(input);
		}
		else if(choice.equals("PlotAccuracy"))
		{
			System.out.println("Please specify a learning ratio.");
			String learningRatio = sc.next();
			System.out.println();
			
			Learning t = new Learning(Double.parseDouble(learningRatio),1);
		}
		else if(choice.equals("NoiseTest"))
		{
			noiseTest(sc);
		}
		else
		{
			System.out.println("Please input one of the choices listed above next time.");
		}
	}
	
	public void runAll(Learning t)									//Gives user another option to test all the letters to see learning rate impact
	{
		for(int i=0; i<alphabet.size();i++)
		{
			t.run(alphabet.get(i));
		}
		System.exit(0);
	}
	
	public void noiseTest(Scanner sc)
	{
		System.out.println("Please specify a learning ratio.");		//Gets Input from user and then learns the alphabet
		String learningRatio = sc.next();
		System.out.println();
		
		Learning t = new Learning(Double.parseDouble(learningRatio),3);
		ArrayList<ArrayList<Integer>> maxOfMaxNoise = new ArrayList<>();
		for(int k=0; k<5;k++)										//Then run 5 times to get the max of the max possible noise the letter can take
		{
			ArrayList<Integer> maxNoise= new ArrayList<>();
			for (int i = 0; i < 26; i++) {
			    maxNoise.add(0);
			}
			boolean flag = true;
			for(int i=0; i<alphabet.size();i++)						//Run through the 26 letters and switch a 0 or 1 in the 5x7 until the letter isnt recognized
			{
				while(flag)
				{
					int random = (int) (Math.random()*26);
					if(alphabet.get(i).get(random)==0)
					{
						alphabet.get(i).set(random, 1);
					}
					else
					{
						alphabet.get(i).set(random, 0);
					}
					Integer currentValue = maxNoise.get(i);
					currentValue++;
		    		maxNoise.set(i,currentValue);
					flag = t.noiseRun(alphabet.get(i),(char)(i+65));
				}
				flag=true;
			}
			maxOfMaxNoise.add(maxNoise);
		}
		for(int i=0; i<maxOfMaxNoise.get(0).size();i++)			//Find the maximum noise tolerated for each letter
		{
			int max=-999999;
			for(int j=0; j<maxOfMaxNoise.size();j++)
			{
				if(maxOfMaxNoise.get(j).get(i)>max)
				{
					max = maxOfMaxNoise.get(j).get(i);
				}
			}
			maxOfMaxNoise.get(0).set(i, max);
		}
		
		for(int i=0; i<maxOfMaxNoise.get(0).size();i++)			//Prints that number out
		{
			System.out.println((char)(i+65)+": max noise "+maxOfMaxNoise.get(0).get(i)+" random bit flips");
		}
	}
	
	public void setUpInputs()
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
        ArrayList<ArrayList<Integer>> alphabet = new ArrayList<>();
        alphabet.add(a_in);
        alphabet.add(b_in);
        alphabet.add(c_in);
        alphabet.add(d_in);
        alphabet.add(e_in);
        alphabet.add(f_in);
        alphabet.add(g_in);
        alphabet.add(h_in);
        alphabet.add(i_in);
        alphabet.add(j_in);
        alphabet.add(k_in);
        alphabet.add(l_in);
        alphabet.add(m_in);
        alphabet.add(n_in);
        alphabet.add(o_in);
        alphabet.add(p_in);
        alphabet.add(q_in);
        alphabet.add(r_in);
        alphabet.add(s_in);
        alphabet.add(t_in);
        alphabet.add(u_in);
        alphabet.add(v_in);
        alphabet.add(w_in);
        alphabet.add(x_in);
        alphabet.add(y_in);
        alphabet.add(z_in);
        this.alphabet=alphabet;						//Creates an arraylist of arraylists that allows for us to save about 100 lines of code
	}

}
