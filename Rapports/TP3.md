# TP3 – Recherche Non Informée

Auteurs

- Karam ELNASORY
- Erkin Tunc BOYA
- Semih DOYNUK

Code source :  [Répertoire de TP3](https://github.com/ErkinTunc/ARP-Recherche-Non-Inform-/tree/main/TP3)

---

## Contexte du TP

Ce TP prolonge les travaux précédents sur la recherche non informée :

- questions théoriques (graphes, arbres, états, nœuds),
- exploration exhaustive du **tour du cavalier**,
- comparaison BFS / DFS / DFS itératif sur les **missionnaires & cannibales**.

---

## Sommaire

- Exercice 1 – Théorie  
- Exercice 2 – Retour du cavalier  
- Exercice 3 – Retour aux cannibales  
- Organisation du code  
- Captures d’écran  
- Références  

---

# Exercice 1 – Théorie

### Question 1 – Graphe d’états vs arbre de recherche
- Un **graphe d’état** peut contenir des cycles.  
- Un **arbre de recherche** ne peut pas en contenir.

### Question 2 – Condition pour obtenir un arbre fini
Un graphe d’état ne produit pas automatiquement un arbre de recherche.  
Pour obtenir un arbre fini, le graphe doit être :
- **fini**,  
- **sans cycles** ou avec **gestion des revisites**,  
- **connexe** pour les parties explorées (chemin entre chaque pair de sommets).

### Question 3 – État vs nœud
- Un **état** = description abstraite du problème.  
- Un **nœud** = état + parent + coût + profondeur.  
→ Plusieurs nœuds peuvent correspondre au même état.

---

# Exercice 2 – Retour du cavalier

Le but : trouver une séquence de mouvements du cavalier visitant chaque case exactement une fois.

## Question 1 – Algorithme utilisé

Le code suivant implémente une **recherche en largeur (BFS)** :

```java
public class ResearchAlgorithm {
    public static Node search(KnightsTourProblem problem) {
        int counter = 0;
        List<Node> frontier = new LinkedList<>();
        Node root = new Node(problem.initialState(), null, null);
        frontier.add(root);

        while (!frontier.isEmpty()) {
            Node currentNode = frontier.remove(0);
            counter++;

            if (problem.isGoalState(currentNode.getState())) {
                System.out.println("Found a solution after evaluating " + counter + " nodes.");
                return currentNode;
            }
            frontier.addAll(currentNode.expand(problem)); // Parcours en largeur
        }
        return null;
    }
}
```

Justification :

- `frontier.remove(0)` = file FIFO  
- `frontier.addAll(...)` = développement niveau par niveau  
→ C’est un **BFS exhaustif**.

---

## Question 2 – Complexité

Le cavalier a au maximum **8 mouvements** possibles.

Pour une grille `m × m` → `n = m * m` cases.

Un BFS a une consommation mémoire en :

\[
O(8^{\,n})
\]

Exemples :

- 5×5 : `n = 25` → \(8^{25} = 152\,587\,890\,625\)  
- 6×6 : `n = 36` → \(8^{36} = 2\,821\,109\,907\,456\)

→ **Explosion exponentielle**.

---

## Question 3 – Version DFS

Le code DFS correspondant se trouve dans :

```
/TP3/Ex2_Tour_Cavalier_DFS
```

DFS :

- utilise une **pile** au lieu d’une file,  
- consomme moins de mémoire,  
- mais peut s’enfoncer dans des branches inutiles.

---

# Exercice 3 – Retour aux cannibales

### Question 1 – Quel algorithme est le plus efficace ?

Par comparaison des trois stratégies (BFS / DFS / DFS itératif),  
le plus pertinent est généralement :

→ **DFS itératif (Iterative Deepening DFS)**  
car il combine :

- mémoire faible (comme DFS),  
- optimalité en profondeur (comme BFS).

### Question 2 – Comment éviter les répétitions ?

Solution :

- garder une **liste / table des états visités**,  
- vérifier avant chaque ajout dans la frontière que l’état :
  - n’a pas déjà été exploré,  
  - n’est pas déjà dans la frontière.

Effets :

- élimine les boucles,  
- réduit fortement le nombre de nœuds générés,  
- rend l’analyse plus stable et comparable.

---

# Organisation du code

Principales classes du TP3 :

- `State.java` : structure d’un état (cavalier ou cannibales)  
- `Position.java` : coordonnées du cavalier  
- `Node.java` : nœud de recherche (état + parent + action)  
- `KnightsTourProblem.java` : définition du problème du cavalier  
- `ResearchAlgorithm.java` : implémentation BFS + variantes  
- `Exercise.java` : point d’entrée pour exécuter le TP

---

# Captures d’écran

Créer un dossier : `images/`

```markdown
![Tour du cavalier – Exploration](images/tour-cavalier.png)
```

```markdown
![Sortie console – Dépassement mémoire](images/console-output.png)
```

---

# Références

- Sujet officiel du TP3 (Recherche non informée – L3)  
- Rapport TP : *ELNASORY_DOYNUK_BOYA.pdf*  
