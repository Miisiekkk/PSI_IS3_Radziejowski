from pybrain3 import *
from pybrain3.supervised.trainers import BackpropTrainer
from testinput import inputDataSet


network = FeedForwardNetwork()         #Creating new network
letters = ["A", "B", "C", "D", "I", "F", "G", "H", "K", "U", "M", "E", "L", "O", "P", "R", "T", "W", "X", "Y"]

inLayer = LinearLayer(35)           #Creating input layer
hiddenLayer = SigmoidLayer(20)       #Creating hidden layer
outLayer = LinearLayer(20)           #Creating output layer
bias = BiasUnit()                   #Initializing Bias

network.addInputModule(inLayer)         #Adding in/out and module layers to the network
network.addModule(bias)
network.addModule(hiddenLayer)
network.addOutputModule(outLayer)

bias_to_hidden = FullConnection(bias, hiddenLayer)          #Creating connection between layers
in_to_hidden = FullConnection(inLayer, hiddenLayer)
hidden_to_out = FullConnection(hiddenLayer, outLayer)

network.addConnection(bias_to_hidden)                       #Adding connection to network
network.addConnection(in_to_hidden)
network.addConnection(hidden_to_out)

network.sortModules()                                       #Sorting modules
inp = inputDataSet['input']                                 #Making shortcut to the input section of DataSet

print ("Number of training patterns: ", len(inputDataSet))      #Printing number of training patterns

trainer = BackpropTrainer(network, dataset=inputDataSet, learningrate=0.1, verbose=True, momentum=0.01)    #Initializing trainer with Backpropagation method

trainer.trainEpochs(5000)                                              #Training network for X epochs, verbose = true so printing errors for each epoch


print("\n\n")
for i in range(20):                                                     #Final print
    print("Dla litery",letters[i],"output wynosi:")
    temp = network.activate(inp[i])
    for j in range(20):
        print(temp[j])
    print("\n\n")


print("\n\n Koniec testów")