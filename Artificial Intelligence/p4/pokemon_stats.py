#!/usr/bin/env python
# coding: utf-8

# In[1]:


import random
import csv
import numpy as np
import matplotlib.pyplot as plt
import math


# In[2]:


def load_data(filepath):
    with open('{}'.format(filepath), newline='') as csvfile:
        reader = csv.DictReader(csvfile)
        result_list = []
        type(result_list)
        
        count = 0
        for row in reader:
            if (count == 20): 
                break
            result_list.append(row)      
            count += 1
        
        for i in range(len(result_list)):
            result_list[i]['Total'] = int(result_list[i]['Total'])
            result_list[i]['HP'] = int(result_list[i]['HP'])
            result_list[i]['Attack'] = int(result_list[i]['Attack'])
            result_list[i]['Defense'] = int(result_list[i]['Defense'])
            result_list[i]['Sp. Atk'] = int(result_list[i]['Sp. Atk'])
            result_list[i]['Sp. Def'] = int(result_list[i]['Sp. Def'])
            result_list[i]['Speed'] = int(result_list[i]['Speed'])
            
            del result_list[i]['Generation']
            del result_list[i]['Legendary']
        
        return result_list


# In[3]:


def calculate_x_y(stats):
    x = stats['Attack'] + stats['Sp. Atk'] + stats['Speed']
    y = stats['Defense'] + stats['Sp. Def'] + stats['HP']

    return x, y


# In[5]:


def random_x_y(m):
    list = []
    result = []
    
    for i in range(m):
        list.append(random.randrange(0, 360))
        list.append(random.randrange(0, 360))
        result.append(list)
        list = []
        
    return result

