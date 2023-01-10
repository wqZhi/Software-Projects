import os
import math


# These first two functions require os operations and so are completed for you
# Completed for you
def load_training_data(vocab, directory):
    """ Create the list of dictionaries """
    top_level = os.listdir(directory)
    dataset = []
    for d in top_level:
        if d.startswith('.'):
            # ignore hidden files
            continue
        if d[-1] == '/':
            label = d[:-1]
            subdir = d
        else:
            label = d
            subdir = d + "/"
        files = os.listdir(directory + subdir)
        for f in files:
            bow = create_bow(vocab, directory + subdir + f)
            dataset.append({'label': label, 'bow': bow})
    return dataset


# Completed for you
def create_vocabulary(directory, cutoff):
    """ Create a vocabulary from the training directory
        return a sorted vocabulary list
    """

    top_level = os.listdir(directory)
    vocab = {}
    for d in top_level:
        if d.startswith('.'):
            # ignore hidden files
            continue
        subdir = d if d[-1] == '/' else d + '/'
        files = os.listdir(directory + subdir)
        for f in files:
            with open(directory + subdir + f, 'r') as doc:
                for word in doc:
                    word = word.strip()
                    if not word in vocab and len(word) > 0:
                        vocab[word] = 1
                    elif len(word) > 0:
                        vocab[word] += 1
    return sorted([word for word in vocab if vocab[word] >= cutoff])


# The rest of the functions need modifications ------------------------------
# Needs modifications
def create_bow(vocab, filepath):
    """ Create a single dictionary for the data
        Note: label may be None
    """
    bow = {}
    with open(filepath, 'r', encoding = 'utf-8') as f:

        for word in f:
            word = word.strip()
            if word in vocab:
                if word in bow:
                    bow[word] += 1
                else:
                    bow[word] = 1
            else:
                if None in bow:
                    bow[None] += 1
                else:
                    bow[None] = 1

    return bow


# Needs modifications
def prior(training_data, label_list):
    """ return the prior probability of the label in the training set
        => frequency of DOCUMENTS
    """
    smooth = 1  # smoothing factor
    logprob = {}

    # TODO: add your code here
    count = {}

    for elem in training_data:

        if elem['label'] in label_list:
            if elem['label'] in count:
                count[elem['label']] = count[elem['label']] + 1
            else:
                count[elem['label']] = 1

    for val in label_list:
        logprob[val] = math.log((count[val] + smooth) / (len(training_data) + smooth * 2))

    return logprob


# Needs modifications
def p_word_given_label(vocab, training_data, label):
    """ return the class conditional probability of label over all words, with smoothing """

    smooth = 1  # smoothing factor
    word_prob = {}
    # TODO: add your code here
    words_dict = {}

    for word in vocab:
        words_dict[word] = 0
    words_dict[None] = 0

    total_word = 0
    for diction in training_data:
        if diction['label'] == label:
            for val in diction['bow'].keys():
                if val in words_dict.keys():
                    words_dict[val] = diction['bow'][val] + words_dict[val]
                else:
                    words_dict[None] = words_dict[val] + words_dict[None]

                total_word = diction['bow'][val] + total_word

    for val in words_dict.keys():
        word_prob[val] = math.log((words_dict[val] + smooth * 1) / (total_word + smooth * (len(vocab) + 1)))

    return word_prob


##################################################################################
# Needs modifications
def train(training_directory, cutoff):
    """ return a dictionary formatted as follows:
            {
             'vocabulary': <the training set vocabulary>,
             'log prior': <the output of prior()>,
             'log p(w|y=2016)': <the output of p_word_given_label() for 2016>,
             'log p(w|y=2020)': <the output of p_word_given_label() for 2020>
            }
    """
    retval = {}
    label_list = [f for f in os.listdir(training_directory) if not f.startswith('.')]  # ignore hidden files
    # TODO: add your code here
    vocab = create_vocabulary(training_directory, cutoff)
    loaded_data = load_training_data(vocab, training_directory)
    retval['vocabulary'] = vocab

    prior_val = prior(loaded_data, label_list)
    retval['log prior'] = prior_val

    p_word_2020 = p_word_given_label(vocab, loaded_data, '2020')
    p_word_2016 = p_word_given_label(vocab, loaded_data, '2016')
    retval['log p(w|y=2020)'] = p_word_2020
    retval['log p(w|y=2016)'] = p_word_2016

    return retval


# Needs modifications
def classify(model, filepath):
    """ return a dictionary formatted as follows:
            {
             'predicted y': <'2016' or '2020'>,
             'log p(y=2016|x)': <log probability of 2016 label for the document>,
             'log p(y=2020|x)': <log probability of 2020 label for the document>
            }
    """
    retval = {}
    # TODO: add your code here
    with open(filepath, 'r', encoding = 'utf-8') as f:
        log_2020 = model['log prior']['2020']
        log_2016 = model['log prior']['2016']

        for val in f:
            val = val.strip()
            if val in model['vocabulary']:
                log_2020 = log_2020 + model['log p(w|y=2020)'][val]
                log_2016 = log_2016 + model['log p(w|y=2016)'][val]
            else:
                log_2020 = log_2020 + model['log p(w|y=2020)'][None]
                log_2016 = log_2016 + model['log p(w|y=2016)'][None]

        # add to dictionary
        retval['log p(w|y=2020)'] = log_2020
        retval['log p(w|y=2016)'] = log_2016
        if log_2016 < log_2020:
            retval['predicted y'] = 2020
        else:
            retval['predicted y'] = 2016

    return retval