# ARP-Recherche-Non-Inform

---

## Ex1

### Q1:

- **état**: une position dans la situation (correspond à une situation dans laquelle le solveur peut se trouver)
- **espace des états**: toutes les positions possibles (ensemble de tous les états possibles)
- **action** : correspond à une prise de décision menée par le solveur face à une situation donnée (operation qui permet de choisir le route)
- **fonction de succession**: une fonction de succession nous permet de sauter à l’état suivant (élément de l’espace des états) grâce à l’action choisie.
    
    Elle permet de modifier la situation courante et d’accéder donc à un nouvel état.
    
- **graphe d'états**: c’est un graphe orienté où
    - les nœuds sont les états présents dans l'espace des états,
    - il existe un arc entre les états "A" et "B" s’il existe une action reliant A à B.
        
        Pour montrer tous les états et comment se déplacer entre ces états.
        

### Q2:

- **état du monde réel** : situations réelles avec beaucoup de détails
- **une description d’état** : représentation abstraite de l’état, on abstrait les détails inutile et qui influent pas la solution.

Cette distinction est utile car elle simplifie la complexité du problème et nous permet de le résoudre sans soucier de détails qui complique le calcule des solutions sans forcément avoir plus de valeur ajouter à la solution. Elle permet de faire le traitement. Enplus, si on rancrontre une problem simulaire on peut appliquer une solution qu’on a deja utilise avant facilement.

---

