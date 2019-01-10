# NeuralNet

To run, download the folder and open it in eclipse or any other java editor. Next run the main java file in /src/.

You will be presented with three choices: Input, PlotAccuracy, and NoiseTest

Input: Allows user to manually input a learning ratio and a 5*7 alphabet digit in the form of zeros and ones. 
This will output the programs confidence rating of what it thinks that letter is.
The user also has the option to reply "all" to run through the alphabet.

PlotAccuracy: Allows user to manually input a learning ratio then shows the neural net learning over 10 stages.

NoiseTest: Allows user to manually input a learning ratio. Then the program will run through each letter in the alphabet randomly 
flipping a bit either from 1 to 0 or from 0 to 1. The program will stop flipping when it guesses the wrong letter. Therefore the 
NoiseTest shows how many random flips it can do before it becomes noise and guesses the wrong letter.
