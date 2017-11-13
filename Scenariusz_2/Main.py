from pybrain3 import *
from pybrain3.supervised.trainers import BackpropTrainer
from testinput import inputDataSet

network = FeedForwardNetwork()         #Creating new network
letters = ["A", "B", "C", "D", "I", "F", "G", "H", "K", "U", "a", "b", "c", "d", "f", "h", "m", "o", "w", "z"]

inLayer = LinearLayer(35)           #Creating input layer
hiddenLayer = SigmoidLayer(5)       #Creating hidden layer
outLayer = LinearLayer(1)           #Creating output layer
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
errorCompare = 0.975                                        #Comparator

print ("Number of training patterns: ", len(inputDataSet))      #Printing number of training patterns

trainer = BackpropTrainer(network, dataset=inputDataSet, learningrate=0.1, verbose=True)    #Initializing trainer with Backpropagation method

trainer.trainEpochs(1000)                                              #Training network for X epochs, verbose = true so printing errors for each epoch


print("\n\n")
for i in range(20):                                                     #Final print
    print("Dla literki", letters[i],"dokładność wynosi ")
    temp = network.activate(inp[i])
    print(temp[0])
    if errorCompare < temp:
        print("Literka", letters[i],"jest dużą literą\n")
    else:
        print("Literka", letters[i],"jest małą literą \n")

print("\n\n Koniec testów")