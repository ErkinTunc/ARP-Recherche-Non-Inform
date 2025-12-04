# TP1 & TP2 – Recherche Non Informée

Auteurs :

- Karam ELNASORY
- Erkin Tunc BOYA
- Semih DOYNUK

Code source : [Répertoire de TP1 & TP2](https://github.com/ErkinTunc/ARP-Recherche-Non-Inform-)

---

# Sommaire

- Exercice 1 – Définitions générales
- Exercice 2 – Problème des cruches (3L / 5L)
- Exercice 3 – Problème du cavalier
- Exercice 4 – Missionnaires & Cannibales
- Compilation & exécution

---

# Exercice 1 – Définitions générales

### Q1 – Définitions

- **État** : une configuration dans laquelle le solveur peut se trouver.
- **Espace des états** : ensemble de toutes les configurations possibles.
- **Action** : décision effectuée par le solveur permettant de passer d’un état à un autre.
- **Fonction de succession** : fonction retournant l’état résultant après l’application d’une action.
- **Graphe d’états** : graphe orienté dont les sommets sont les états et les arcs les transitions possibles.

### Q2 – État réel vs description d’état

- **État réel** : riche, détaillé, inclut des informations non nécessaires à la résolution.
- **Description d’état** : abstraction conservant uniquement les éléments pertinents.

→ Permet de réduire la complexité, d’éviter du bruit inutile, et de réutiliser des solutions pour des problèmes similaires.

---

# Exercice 2 – Problème des cruches (3L / 5L)

## Q1 – Modélisation constructive (5 éléments vus en cours)

### Étape 1 : Espace d’état

Un état est défini par :  
\[
s = (c_3, c_5)
\]

- \(c_3\) = contenu actuel de la cruche 3L (entre 0 et 3)
- \(c_5\) = contenu actuel de la cruche 5L (entre 0 et 5)

### Étape 2 : État initial

\[
s_0 = (0, 0)
\]

### Étape 3 : Actions

Ensemble des actions \(A = \{a_0, a_1, ..., a_5\}\) :

- Remplir la cruche 3L
- Remplir la cruche 5L
- Vider la cruche 3L
- Vider la cruche 5L
- Verser 3L → 5L
- Verser 5L → 3L

### Étape 4 : Fonction de successeur

\[
succ((c_3,c_5), a) = (c'\_3, c'\_5)
\]
où les nouvelles valeurs respectent les capacités.

### Étape 5 : Ensemble d’états but

\[
T = \{ (c_3, c_5) \mid c_5 = 4 \}
\]

### Étape 6 : Fonction de coût

Toutes les actions ont un coût constant :

\[
c(s, s') = 1
\]

---

## Q3 – Taille de l’espace d’états

\[
4 \times 6 = 24 \text{ états possibles}
\]

## Q5 – Meilleure solution

Le chemin minimal vers (3,4) contient **7 étapes** pour un coût total de **6**.

![Graphe de Cruches](img/graph-cruches.png)

---

# Exercice 3 – Problème du cavalier

## Q1 – Modélisation constructive

### Étape 1 : Espace d’état

Un état est une matrice 8×8 :

- `-1` : position du cavalier
- `1` : case visitée
- `0` : case non visitée

\[
s = (p, V)
\]

- \(p = (x,y)\) position du cavalier
- \(V\) : ensemble des cases déjà visitées

### Étape 2 : État initial

\[
s_0 = ((0,0), \{(0,0)\})
\]

### Étape 3 : Actions

Déplacements possibles du cavalier :  
\[
\{HG, HD, DH, DB, BD, BG, GH, GB\}
\]
<img src="img/ChessMoveKnight.jpg" width="40%" style="display:block; margin:auto;">

### Étape 4 : Fonction de successeur

\[
succ((p, V), p') = (p', V \cup \{p'\})
\]

### Étape 5 : Ensemble d’états but

Toutes les cases sont visitées :

\[
T = \{ (p, V) \mid |V| = 64 \}
\]

### Étape 6 : Fonction de coût

\[
c((p,V), p') = 1
\]

---

## Q2 – Taille de l’espace d’états

Une grille \(m \times m\) contient \(n = m \times m\) cases.  
Le cavalier ne revisite jamais une case → **n états maximum dans un chemin**.

Pour 5×5 : 25 états → déjà très grand en développement exhaustif.

## Q4 – Limite pratique

Essais :  
Une grille 10×10 épuise la mémoire de l’ordinateur pendant la recherche exhaustive.

![Erreur mémoire](img/erreur.png)  
![Statistiques PC](img/pc-stats.png)

---

# Exercice 4 – Missionnaires & cannibales

## Q1 – Modélisation constructive

### 1. Espace d’état

Un état est défini par :

\[
s = (c, m, b)
\]

- \(c\) = nombre de cannibales sur la rive gauche
- \(m\) = nombre de missionnaires sur la rive gauche
- \(b \in \{G, D\}\) = position du bateau

**Contraintes de sécurité :**

- Si des missionnaires sont présents sur une rive, ils doivent être au moins aussi nombreux que les cannibales.
- Rive droite :  
  \[
  c_d = 3 - c,\qquad m_d = 3 - m
  \]
  \[
  m_d = 0 \;\; \text{ou} \;\; m_d \ge c_d
  \]

---

### 2. État initial et état but

\[
s_0 = (3,3,G)
\]

\[
s_f = (0,0,D)
\]

---

### 3. Ensemble d’actions

Le bateau peut transporter :

\[
A = \{(1,0),\; (0,1),\; (2,0),\; (0,2),\; (1,1)\}
\]

où \((\Delta c, \Delta m)\) représente le nombre de cannibales et missionnaires déplacés.

---

### 4. Fonction de successeur

#### Cas 1 : Bateau à gauche (\(b = G\))

Pour une action \(a = (\Delta c,\Delta m) \in A\) :

\[
succ((c,m,G), a) = (c - \Delta c,\; m - \Delta m,\; D)
\]

**Préconditions :**

1. Assez de personnes à embarquer :
   \[
   \Delta c \le c,\qquad \Delta m \le m
   \]

2. Sécurité rive gauche :
   \[
   (m - \Delta m = 0) \;\; \text{ou} \;\; (m - \Delta m \ge c - \Delta c)
   \]

3. Sécurité rive droite :
   \[
   c_d' = 3 - (c - \Delta c),\qquad m_d' = 3 - (m - \Delta m)
   \]
   \[
   (m_d' = 0) \;\; \text{ou} \;\; (m_d' \ge c_d')
   \]

---

#### Cas 2 : Bateau à droite (\(b = D\))

\[
succ((c,m,D), a) = (c + \Delta c,\; m + \Delta m,\; G)
\]

**Préconditions :**

1. Assez de personnes sur la rive droite :
   \[
   c_d = 3 - c,\qquad m_d = 3 - m
   \]
   \[
   \Delta c \le c_d,\qquad \Delta m \le m_d
   \]

2. Sécurité rive gauche :
   \[
   (m + \Delta m = 0) \;\; \text{ou} \;\; (m + \Delta m \ge c + \Delta c)
   \]

3. Sécurité rive droite :
   \[
   c_d' = 3 - (c + \Delta c),\qquad m_d' = 3 - (m + \Delta m)
   \]
   \[
   (m_d' = 0) \;\; \text{ou} \;\; (m_d' \ge c_d')
   \]

---

### 5. Ensemble d’états but

\[
T = \{(0,0,D)\}
\]

---

### 6. Fonction de coût

\[
c(s,a,s') = 1
\]

---

### Boucles possibles

Le problème autorise des retours arrière, ce qui peut créer des cycles si les états visités ne sont pas mémorisés.

---

# Compilation & Exécution

```bash
# Compilation
javac -d ./bin/ *.java

# Exécution
java -cp ./bin/ Exercise
```

---

# Références

- Sujet officiel du TP3 (Recherche non informée – L3)  
- Rapport TP : *Karam ELNASORY · Erkin Tunc BOYA· Semih DOYNUK Rendu TP1 & TP2 Recherche Non Informé.pdf*  