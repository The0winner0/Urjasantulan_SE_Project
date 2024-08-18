# import necessary libraries

import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
import tensorflow as tf
from sklearn.preprocessing import StandardScaler
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LogisticRegression
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import classification_report, confusion_matrix, accuracy_score
from sklearn.metrics import *
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense, Dropout
from tensorflow.keras.optimizers import Adam
from tensorflow import lite
import sklearn.metrics as metrics
import pickle

# tensorflow version

tf.__version__

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

# make the model

model = Sequential()
model.add(Dense(128, activation='relu', input_shape=(12,)))
model.add(Dropout(0.3))
model.add(Dense(64, activation='relu'))
model.add(Dropout(0.3))
model.add(Dense(32, activation='relu'))
model.add(Dense(1, activation='sigmoid'))

model.compile(optimizer='adam', loss='binary_crossentropy', metrics=['accuracy'])

# summary of the model

model.summary()

# train the model

model.fit(X_train_scaled, y_train, epochs=200, batch_size=32)

# evaluate the model

accuracy = model.evaluate(X_test_scaled, y_test)
accuracy

# make tflite model

converter = tf.lite.TFLiteConverter.from_keras_model(model)
tflite_model = converter.convert()
open("backend/asset/smart_grid_stability.tflite", "wb").write(tflite_model)