# -*- coding: utf-8 -*-
# import libraries

import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn.preprocessing import StandardScaler
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LogisticRegression
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import classification_report, confusion_matrix, accuracy_score
from sklearn.metrics import *
import sklearn.metrics as metrics
import pickle

# load data

data = pd.read_csv('backend/asset/smart_grid_stability_augmented.csv')

# change the target variable to binary

mapping = {'unstable': 0, 'stable': 1}
data['stabf'] = data['stabf'].map(mapping)

# drop the stab column and separate the target variable from the predictors

data = data.drop('stab', axis=1)
X = data.drop('stabf', axis=1)
y = data['stabf']

# split the data into training and testing sets

X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.3, random_state=42)

# standardize the data

scalar = StandardScaler()
X_train_scaled = scalar.fit_transform(X_train)
X_test_scaled = scalar.transform(X_test)

# train the model

model = RandomForestClassifier(n_estimators=100, max_depth=10, max_features='sqrt', random_state=42)
model.fit(X_train_scaled, y_train)

# make predictions

y_pred_train = model.predict(X_train_scaled)
y_pred_test = model.predict(X_test_scaled)

# evaluate the model

train_accuracy = accuracy_score(y_train, y_pred_train) * 100
test_accuracy = accuracy_score(y_test, y_pred_test) * 100

print("Training Accuracy: ", train_accuracy, "%")
print("Testing Accuracy: ", test_accuracy, "%")

# other metrics

accuracy = metrics.accuracy_score(y_test, y_pred_test)
sensitivity = metrics.recall_score(y_test, y_pred_test)
specificity = metrics.recall_score(y_test, y_pred_test, pos_label=0)
precision = metrics.precision_score(y_test, y_pred_test)
f1_score = metrics.f1_score(y_test, y_pred_test)

print("Accuracy: ", accuracy)
print("Sensitivity: ", sensitivity)
print("Specificity: ", specificity)
print("Precision: ", precision)
print("F1 Score: ", f1_score)

# save the model

filename = 'backend/asset/smart_grid_stability_model.pkl'
pickle.dump(model, open(filename, 'wb'))