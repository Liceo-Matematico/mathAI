# 🧠 MathAI – Libreria Java didattica per l’Intelligenza Artificiale

**MathAI** è una libreria Java pensata per l’insegnamento dell’Intelligenza Artificiale
nel **liceo scientifico – indirizzo matematico / scienze applicate**, in particolare per il **liceo matematico**.

La libreria permette di programmare:

* **regressione lineare**
* **reti neurali feed-forward**
* esempi classici come **AND**, **OR**, **XOR**
* sia in **1D** che in **ND (input vettoriale)**

👉 con **codice semplice**, **senza array di array**, e con concetti coerenti con la teoria.

---

## 🎯 Obiettivi didattici

* Separare chiaramente:

  * **input**
  * **modello**
  * **addestramento**
* Rendere il codice:

  * leggibile dagli studenti
  * modificabile
  * riutilizzabile
* Evitare costrutti avanzati non ancora studiati
* Collegare direttamente:

  * formule matematiche
  * codice Java

---

## 📦 Struttura del progetto

```
src/
└── math_ai/
    ├── core/
    │   ├── InputValue.java
    │   ├── Model.java
    │   └── Trainable.java
    │
    ├── math/
    │   └── VectorMath.java
    │
    ├── regression/
    │   └── LinearRegression1D.java
    │
    └── neural/
        ├── Activation.java
        ├── ActivationFunctions.java
        ├── Layer.java
        ├── NeuralLayer.java
        └── NeuralNetwork.java
```

---

## 🧩 Concetti fondamentali

### 🔹 `InputValue`

Rappresenta **l’input di un modello**.

* può essere **scalare** (1 valore)
* oppure **vettoriale** (più valori)
* nasconde completamente l’uso degli array agli studenti

```java
InputValue x1 = new InputValue(3);        // input 1D
InputValue x2 = new InputValue(1, 0, 1);  // input ND
```

---

### 🔹 `Model`

Classe astratta base per **tutti i modelli**.

```java
public abstract class Model {
    public abstract double predict(InputValue x);
}
```

* l’output è **sempre un `double`**
* sia per regressione che per reti neurali

---

### 🔹 `Trainable`

Interfaccia per i modelli che possono essere **addestrati**.

```java
void train(InputValue[] X, double[] Y, double lr, int epochs);
```

Include anche il calcolo automatico della **loss MSE**.

---

## 📈 Regressione lineare

### `LinearRegression1D`

Modello del tipo:

[
y = m \cdot x + q
]

* addestramento con **discesa del gradiente**
* funzione di errore: **Mean Squared Error**

### Esempio

```java
InputValue[] X = {
    new InputValue(1),
    new InputValue(2),
    new InputValue(3),
    new InputValue(4)
};

double[] Y = {2, 4, 6, 8};

LinearRegression1D model = new LinearRegression1D();
model.train(X, Y, 0.01, 3000);

System.out.println(model.predict(new InputValue(5)));
```

---

## 🧠 Reti neurali

### 🔹 `NeuralLayer`

Un **vero layer neurale**, composto da:

* pesi
* bias
* funzione di attivazione

👉 **Dense + Activation sono un’unica entità**, non due layer separati.

```java
NeuralLayer layer = new NeuralLayer(
    inputSize,
    outputSize,
    Activation.SIGMOID
);
```

---

### 🔹 `NeuralNetwork`

Rete neurale feed-forward composta da più `NeuralLayer`.

* input: `InputValue`
* output: `double`
* supporta input **1D e ND**
* backpropagation implementata internamente

### Esempio: XOR

```java
NeuralNetwork nn = new NeuralNetwork();

nn.add(new NeuralLayer(2, 3, Activation.SIGMOID));
nn.add(new NeuralLayer(3, 1, Activation.SIGMOID));

InputValue[] X = {
    new InputValue(0,0),
    new InputValue(0,1),
    new InputValue(1,0),
    new InputValue(1,1)
};

double[] Y = {0, 1, 1, 0};

nn.train(X, Y, 0.1, 10000);

System.out.println(nn.predict(new InputValue(1,0)));
```

---

## 🧮 Funzioni matematiche

### `VectorMath`

Classe di supporto con funzioni di errore:

* MSE
* RMSE
* MAE

Esempio:

```java
double mse = VectorMath.mse(X, Y, model);
```

---

## 🧠 Scelte progettuali (perché è fatta così)

* **Output sempre scalare**
  → semplifica enormemente l’uso didattico

* **Input incapsulato in `InputValue`**
  → niente `double[][]`, niente confusione

* **Un solo tipo di layer neurale**
  → coerente con la teoria: *un layer = pesi + attivazione*

* **1D e ND con lo stesso codice**
  → il caso 1D è solo un ND con un valore

---

## 🎓 Destinatari

* studenti del **liceo scientifico**
* corsi di **AI introduttiva**
* docenti che vogliono esempi di AI **programmata**, non “magica”

---

## 📜 Licenza

[Gpl 3.0](https://github.com/Liceo-Matematico/mathAI?tab=GPL-3.0-1-ov-file#readme)

---
