# ARP-Recherche-Non-Inform 
Un rapport sur TP1&2 de Algorithmes pour la Résolution de Problèmes L3

Les Developeurs: Erkin Tunc BOYA | Semih DOYNUK | Karam ELNASORY
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




## Ex3 
<img width="933" height="712" alt="image" src="https://github.com/user-attachments/assets/753e19d0-cdbf-4680-aed1-711181937ef2" />



<Graph indexType="custom" height="400" width="400" nodes={[{label:"3,3,G",center:{x:62.6,y:131.1}},{label:"1,3,D",center:{x:168.1,y:160.5}},{label:"2,3,G",center:{x:248.6,y:83.2}},{label:"0,3,D",center:{x:351.3,y:119.5}},{label:"1,3,G",center:{x:373.2,y:227}},{label:"1,1,D",center:{x:268.1,y:186.9}},{label:"2,2,G",center:{x:185.3,y:261.3}},{label:"2,0,D",center:{x:288,y:284.8}},{label:"3,0,G",center:{x:325.4,y:382.8}},{label:"1,0,D",center:{x:215.8,y:364.1}},{label:"2,0,G",center:{x:109.5,y:338.8}},{label:"0,0,D",center:{x:84.4,y:233.1}},{label:"2,2,D",center:{x:143.9,y:56.8}}]} edges={[{source:0,target:1},{source:1,target:2},{source:2,target:3},{source:3,target:4},{source:4,target:5},{source:5,target:6},{source:6,target:7},{source:7,target:8},{source:8,target:9},{source:9,target:10},{source:10,target:11},{source:0,target:12},{source:12,target:2},{source:3,target:4},{source:4,target:5},{source:5,target:6},{source:6,target:7},{source:8,target:9},{source:9,target:10},{source:10,target:11}]} />
---

