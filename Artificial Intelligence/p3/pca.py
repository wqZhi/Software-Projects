#!/usr/bin/env python
# coding: utf-8

# In[1]:


from scipy.linalg import eigh
import numpy as np
import matplotlib.pyplot as plt


def load_and_center_dataset(filename):    
    x = np.load(filename)
    n = np.array(x) - np.mean(x, axis=0) 
    
    return n


def get_covariance(dataset):    
    x = np.array(dataset)
    matrix = np.dot(np.transpose(x), x) / (len(dataset) - 1)
    
    return matrix


def get_eig(S, m):    
    Lambda, U = eigh(S, subset_by_index = [len(S[0]) - m, len(S[0]) - 1])  
    swap = np.arange(start = len(Lambda) - 1, stop = -1, step = -1)
    
    Lambda = Lambda[swap]
    Lambda = np.diag(Lambda)
    U = U[:, swap]
    
    return Lambda, U


def get_eig_perc(S, perc):
    Lambda, U = eigh(S)
    Lambda, U = eigh(S, subset_by_value = [np.sum(Lambda) * perc, np.inf])
    
    swap = np.arange(start = len(Lambda) - 1, stop = -1, step = -1)
    Lambda[:] = Lambda[swap]
    Lambda = np.diag(Lambda)
    U[:,:] = U[:, swap]
    
    return Lambda, U


def project_image(img, U):   
    ai = np.dot(np.transpose(U[:,0]), img)
    aj = np.dot(np.transpose(U[:,1]), img)
    
    projection = np.dot(ai, U[:,0]) + np.dot(aj, U[:,1])
    
    return projection


def display_image(orig, proj):   
    orig_image = np.reshape(orig, (32, 32))
    proj_image = np.reshape(proj, (32, 32))
                
    fig, (ax1, ax2) = plt.subplots(nrows = 1, ncols = 2)
    ax1.set_title('Original')
    pos_neg_clipped = ax1.imshow(np.transpose(orig_image), aspect='equal')
    cbar = fig.colorbar(pos_neg_clipped, ax = ax1)
    
    ax2.set_title('Projection')
    pos_neg_clipped = ax2.imshow(np.transpose(proj_image), aspect='equal')
    cbar = fig.colorbar(pos_neg_clipped, ax = ax2)

    return plt.show()

