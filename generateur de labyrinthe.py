import pygame
import time
import random

# Configuration de la fenêtre pygame
LARGEUR = 500
HAUTEUR = 600
FPS = 30

BLANC = (255, 255, 255)
VERT = (0, 255, 0)
BLEU = (0, 0, 100)

# Initialiser Pygame
pygame.init()
ecran = pygame.display.set_mode((LARGEUR, HAUTEUR))
pygame.display.set_caption("Générateur de labyrinthe")
horloge = pygame.time.Clock()

# Initialisation des variables du labyrinthe
x = 0
y = 0
w = 20
grille = []
visites = []
pile = []

def construire_grille(x, y, w):
    for i in range(1, 6):
        x = 20
        y = y + 20
        for j in range(1, 6):
            pygame.draw.line(ecran, BLEU, [x, y], [x + w, y])
            pygame.draw.line(ecran, BLEU, [x + w, y], [x + w, y + w])
            pygame.draw.line(ecran, BLEU, [x + w, y + w], [x, y + w])
            pygame.draw.line(ecran, BLEU, [x, y + w], [x, y])
            grille.append((x, y))
            x = x + 20

def pousser_haut(x, y):
    pygame.draw.rect(ecran, BLANC, (x + 1, y - w + 1, 19, 39), 0)
    pygame.display.update()

def pousser_bas(x, y):
    pygame.draw.rect(ecran, BLANC, (x + 1, y + 1, 19, 39), 0)
    pygame.display.update()

def pousser_gauche(x, y):
    pygame.draw.rect(ecran, BLANC, (x - w + 1, y + 1, 39, 19), 0)
    pygame.display.update()

def pousser_droite(x, y):
    pygame.draw.rect(ecran, BLANC, (x + 1, y + 1, 39, 19), 0)
    pygame.display.update()

def cellule_unique(x, y):
    pygame.draw.rect(ecran, VERT, (x + 1, y + 1, 18, 18), 0)
    pygame.display.update()

def cellule_retour(x, y):
    pygame.draw.rect(ecran, BLANC, (x + 1, y + 1, 18, 18), 0)
    pygame.display.update()

def creer_labyrinthe(x, y):
    cellule_unique(x, y)
    pile.append((x, y))
    visites.append((x, y))
    while len(pile) > 0:
        time.sleep(.01)   # bien visualiser la méthode de construction du labyrinthe
        cellule = []
        if (x + w, y) not in visites and (x + w, y) in grille:
            cellule.append("droite")
        if (x - w, y) not in visites and (x - w, y) in grille:
            cellule.append("gauche")
        if (x, y + w) not in visites and (x, y + w) in grille:
            cellule.append("bas")
        if (x, y - w) not in visites and (x, y - w) in grille:
            cellule.append("haut")

        if len(cellule) > 0:
            cellule_choisie = random.choice(cellule)
            if cellule_choisie == "droite":
                pousser_droite(x, y)
                x = x + w
                visites.append((x, y))
                pile.append((x, y))
            elif cellule_choisie == "gauche":
                pousser_gauche(x, y)
                x = x - w
                visites.append((x, y))
                pile.append((x, y))
            elif cellule_choisie == "bas":
                pousser_bas(x, y)
                y = y + w
                visites.append((x, y))
                pile.append((x, y))
            elif cellule_choisie == "haut":
                pousser_haut(x, y)
                y = y - w
                visites.append((x, y))
                pile.append((x, y))
        else:
            x, y = pile.pop()
            cellule_unique(x, y)
            time.sleep(0.03)         
            cellule_retour(x, y)

x, y = 20, 20
construire_grille(40, 0, 20)
creer_labyrinthe(x, y)

# Boucle pygame
en_cours = True
while en_cours:
    horloge.tick(FPS)
    for evenement in pygame.event.get():
        if evenement.type == pygame.QUIT:
            en_cours = False

pygame.quit()
