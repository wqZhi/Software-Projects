import csv
import math 
import numpy as np
from matplotlib import pyplot as plt


# Feel free to import other packages, if needed.
# As long as they are supported by CSL machines.


def get_dataset(filename):
    with open(filename, newline = '') as csvfile:
        dataset = list(csv.reader(csvfile))
        dataset = np.delete(dataset, 0, 0)
        dataset = np.delete(dataset, 0, 1)
        dataset = np.array(dataset, dtype='float')

    return dataset


def print_stats(dataset, col):
    num_data = 0
    mean_data = 0
    sum_data = 0
    std_data = 0
    a = 0
    
    data = dataset[:,col]
    
    for val in data:
        num_data += 1
        sum_data += val
    
    mean_data = round(sum_data/num_data, 2)
    
    for val in data:
        a = a + (val - mean_data)**2
        
    std_data = round(math.sqrt((a/num_data)), 2)

    return num_data,mean_data,std_data


def regression(dataset, cols, betas):
    mse = None
    num_data = 0
    y = dataset[:,0]
    modified_betas = np.delete(betas,0)
    
    for val in dataset[:,cols]:
        num_data += 1
                
    x = np.dot(dataset[:,cols], modified_betas)
    mse = np.sum((1/num_data) * pow((betas[0] + x - y),2))
    
    return mse


def gradient_descent(dataset, cols, betas):
    list = []
    grads = None
    modified_betas = np.delete(betas,0)
    y = dataset[:,0]
    x = np.dot(dataset[:,cols], modified_betas)
    
    num_data = 0
    for val in dataset[:,cols]:
        num_data += 1

    val = np.sum((2/num_data) * (betas[0] + x - y))
    list.append(val)
    for i in range(len(cols)):
        val = np.sum((2/num_data) * (betas[0] + x - y) * dataset[:,cols[i]])
        list.append(val)
    
    grads = np.array(list)
    
    return grads


def iterate_gradient(dataset, cols, betas, T, eta):
    betas_list = []
    
    for t in range(1,T+1):
        for i in range(len(betas)):
            result = betas[i] - eta*(gradient_descent(dataset, cols, betas)[i])
            betas_list.append(result)
            
        mse = regression(dataset, cols, betas_list)
        print("%d %.2f" % (t, mse), end = ' ')
                  
        for j in range(len(betas)):
            temp_result = betas[j] - eta*(gradient_descent(dataset, cols, betas)[j])
            print("%.2f" % temp_result, end = ' ')
        
        print()
        betas = betas_list
        betas_list = []


def compute_betas(dataset, cols):
    betas = None
    mse = None
    y = dataset[:,0]

    data = dataset[:,cols]
    data = np.insert(data, 0,1, axis=1)
    
    transposed = np.transpose(data)
    inversed = np.linalg.inv(np.dot(transposed,data))
    a = np.dot(inversed, transposed)
    betas = np.dot(a, y)
    
    mse = regression(dataset, cols, betas)
    
    return (mse, *betas)


def predict(dataset, cols, features):
    result = None
    beta = compute_betas(dataset, cols)
    
    modified_betas = np.delete(beta,0)
    
    result = modified_betas[0]
    for i in range(len(features)):
        result += np.dot(features[i], modified_betas[i+1])
    
    return result


def synthetic_datasets(betas, alphas, X, sigma):
    linear_result = []
    quardra_result = []
    
    for i in range(X.shape[0]):
        x = X[i,0]
        y = betas[0] + np.dot(x, betas[1]) + np.random.normal(0,sigma)
        linear_result.append([y,x])

    for j in range(X.shape[0]):
        x = X[j,0]
        y = alphas[0] + np.dot(x**2, alphas[1]) + np.random.normal(0,sigma)
        quardra_result.append([y,x])
    
    return np.array(linear_result), np.array(quardra_result)


def plot_mse():
    from sys import argv
    if len(argv) == 2 and argv[1] == 'csl':
        import matplotlib
        matplotlib.use('Agg')

    # TODO: Generate datasets and plot an MSE-sigma graph
    X = np.transpose(np.array([np.random.randint(-100,100,1000)]))
    betas = np.array([4,3])
    alphas = np.array([2,2])
    sigma = (0.0001,0.001,0.01,0.1,1,10,100,1000,10000,100000)

    dictionary = {}
    for i in sigma:
        dictionary[i] = synthetic_datasets(betas, alphas,X, i)

    linear_result = []
    quardra_result = [] 
    mse = None
    for val in sigma:
        mse = compute_betas(dictionary[val][0], [1])[0]
        linear_result.append(mse)
        mse = compute_betas(dictionary[val][1], [1])[0]
        quardra_result.append(mse)  

    plt.xlabel('Standard Deviation of Error Term')
    plt.ylabel('MSE of Trained Model')
    plt.plot(sigma, linear_result, '-o', label='MSE of Linear Dataset')
    plt.plot(sigma, quardra_result, '-o', label= 'MSE of Quadratic Dataset')
    plt.legend(loc='upper left', prop={'size': 8})
    plt.xscale('log')
    plt.yscale('log')
    plt.savefig("mse.pdf")
    

if __name__ == '__main__':
    ### DO NOT CHANGE THIS SECTION ###
    plot_mse()
