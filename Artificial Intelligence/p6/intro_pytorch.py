import numpy as np
import torch
import torch.nn as nn
import torch.nn.functional as F
import torch.optim as optim
from torchvision import datasets, transforms

# Feel free to import other packages, if needed.
# As long as they are supported by CSL machines.


def get_data_loader(training = True):    
    custom_transform=transforms.Compose([
        transforms.ToTensor(),
        transforms.Normalize((0.1307,), (0.3081,))
        ])
    train_set = datasets.MNIST('./data', train=True, download=True, transform=custom_transform)
    test_set = datasets.MNIST('./data', train=False, transform = custom_transform)
    if training:
        loader = torch.utils.data.DataLoader(train_set, batch_size = 50)
    else:
        loader = torch.utils.data.DataLoader(test_set, batch_size = 50)
    return loader


def build_model():
    model = nn.Sequential(
        nn.Flatten(),
        nn.Linear(784, 128),
        nn.ReLU(),
        nn.Linear(128, 64),
        nn.ReLU(),
        nn.Linear(64, 10),
    )
    return model


def train_model(model, train_loader, criterion, T):
    opt = optim.SGD(model.parameters(), lr=0.001, momentum=0.9)
    model.train()
    for epoch in range(T):
        correct = 0
        total = 0
        running_loss = 0
        for data in train_loader:
            inputs, labels = data
            opt.zero_grad()
            outputs = model(inputs)
            loss = criterion(outputs, labels)
            loss.backward()
            opt.step()
            running_loss += loss.item()
            _, predictions = torch.max(outputs, 1)
            correct += (predictions == labels).sum().item()
            total += len(outputs)
        print("Train Epoch: {:d}   Accuracy: {:d}/{:d}({:.2f}%) Loss: {:.3f}".format(epoch, correct, total, 100*correct/total, running_loss/len(train_loader)))


def evaluate_model(model, test_loader, criterion, show_loss = True):
    model.eval()
    with torch.no_grad():
        correct = 0
        total = 0
        running_loss = 0
        for data in test_loader:
            images, labels = data
            outputs = model(images)
            loss = criterion(outputs, labels)
            running_loss += loss.item()
            _, predictions = torch.max(outputs, 1)
            correct += (predictions == labels).sum().item()
            total += labels.size(0)           
        if show_loss: 
            print("Average loss: {:.4f}\nAccuracy: {:.2f}%".format(running_loss/len(test_loader), 100*correct/total))
        else: 
            print("Accuracy: {}%".format(100*correct/total))
                

def predict_label(model, test_images, index):
    class_names = ['zero', 'one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight', 'nine']
    image,_ = test_images[index]
    logits = model(image)
    prob = F.softmax(logits,dim=1)
    
    prob_array = np.transpose(prob.detach().numpy())    
    combined = np.column_stack((prob_array, class_names))
    list1 = combined.tolist()
    list1.sort(key=lambda list1: list1[0], reverse=True)
    for i in range(3):
        print(list1[i][1] + ": {:.2f}%".format(float(list1[i][0])*100))
    


if __name__ == '__main__':
    '''
    Feel free to write your own test code here to exaime the correctness of your functions. 
    Note that this part will not be graded.
    '''
    criterion = nn.CrossEntropyLoss()
