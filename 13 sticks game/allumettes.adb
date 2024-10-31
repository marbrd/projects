with Ada.Text_IO;
use Ada.Text_IO;
with Ada.Integer_Text_IO;
use Ada.Integer_Text_IO;
with Alea;

--------------------------------------------------------------------------------
--  Auteur   :  BERRAD Marouane
--  Objectif :  Jeu de 13 allumettes
--------------------------------------------------------------------------------


-- Jouer au jeu de 13 allumettes entre un joueur humain et une machine
procedure Allumettes is

	package Alea_1_3 is
		new Alea (1, 3);
    use Alea_1_3;

  
    niveau : Character ;                       -- niveau du jeu
    Allumettes : Integer ;                     -- nombre d'allumettes restantes
    choix : Character ;                        -- le choix du joueur humain s'il veut commencer ou non
    Est_Tour_Humain : Boolean ;                -- si c'est vrai donc c'et le role du joueur humain
    minimum : Integer ;                        -- le minimun entre 3 et le nombre d'allumettes restantes
    nbr_choisi : Integer ;                     -- le nombre d'allumettes chosi par le joueur
    nbr_bloc : Integer ;                       -- le nombre de bloc complet d'allumettes affichées
    reste : Integer ;                          -- le nombre d'allumettes dans le dernier bloc non complet
    nbr_batonnets_bloc : Integer;              -- le nombre d'allumettes dans un bloc
    nbr_lignes : Integer;                      -- le nombre de ligne pour l'affichage des allumettes


begin

    -- Demander le niveau de jeu
    Put ("Niveau de l'ordinateur (n)aïf, (d)istrait, (r)apide ou (e)xpert ? ");
    Get (niveau);
    Skip_Line;

    -- Afficher le niveau chosi
    case niveau is
        when 'n' | 'N' =>
            Put ("Mon niveau est naÏf.");
        when 'd' | 'D' =>
            Put ("Mon niveau est distrait.");
        when 'r' | 'R' =>
            Put ("Mon niveau est rapide.");
        when others =>
            Put ("Mon niveau est expert.");
    end case;

    -- Jouer le jeu 13 allumettes
    
    -- Demander au joueur humain s’il veut commencer ou non
    New_Line;
    Put ("Est-ce que vous commencez (o/n) ? ");
    Get (choix);
    Skip_Line;

    -- Jouer le jeu
    Allumettes := 13;

    -- Identifier le joueur qui commence le jeu
    Est_Tour_Humain := (choix = 'o' or choix = 'O');
    loop

        -- Afficher les allumettes 
        New_Line;
        nbr_lignes := 3;
        for I in 1..nbr_lignes loop

            -- Afficher une ligne
            nbr_batonnets_bloc := 5;
            nbr_bloc := Allumettes / nbr_batonnets_bloc;
            reste := Allumettes mod nbr_batonnets_bloc;

            -- Construire les blocs
            for k in 1..nbr_bloc loop
                
                -- Ecrire sans retour à la ligne un bloc et un espace
                for j in 1..nbr_batonnets_bloc loop
                    Put ("|");
                end loop;
                Put ("  ");
            end loop;
	
            -- Ajouter le reste
            for j in 1..reste loop
                Put ("|");
            end loop;
        New_Line;
        end loop;

        -- Chosir un nombre par le joueur en respectant les règles du jeu
        loop

            -- Calculer le minimum de 3 et Allumettes
            if Allumettes > 3 then
	        minimum := 3;
            else 
                minimum := Allumettes ;
            end if;

            -- Choisir un nombre par le joueur
            if Est_Tour_Humain then
            
                -- Choisir le nombre par le joueur humain
                New_Line;
                Put ("Combien d'allumettes prenez-vous ? ");
                Get (nbr_choisi);		        
                Skip_Line;
            
	    else
                -- Choisir le nombre par la machine
                case niveau is
                    when 'n' | 'N' =>
                        -- Choisir un nombre aléatoirement entre 1 et le minimum entre 3 et Allumettes
                        loop
                            Get_Random_Number(nbr_choisi);
                            exit when nbr_choisi <= Allumettes;
                        end loop;
                    when 'd' | 'D' =>
                        -- Choisir un nombre aléatoirement entre 1 et 3
                        Get_Random_Number(nbr_choisi);
                    when 'r' | 'R' =>
                        --  Choisir le minimum entre 3 et Allumettes
                        nbr_choisi := minimum;
                    when others =>
                        -- Choisir le meilleur choix
                        if Allumettes mod 4 = 0 then
                            nbr_choisi := 3;
                        elsif Allumettes mod 4 = 2 then
                            nbr_choisi := 1;
                        elsif Allumettes mod 4 = 3 then
                            nbr_choisi := 2;
                        else
                            loop
                                Get_Random_Number(nbr_choisi);
                                exit when nbr_choisi <= Allumettes;
                            end loop;
                        end if;
                end case;
	       
		-- Afficher le choix de la machine
	       	New_Line;
	        if nbr_choisi = 1 then
                    Put_Line("Je prends une allumette.");
                else
                    Put("Je prends ");
		    Put(nbr_choisi,1);
                    Put_Line(" allumettes.");
                end if;
	    end if;

            -- Vérifier le respect des règles du jeu et afficher un message explicatif
            if nbr_choisi <= 0 then
                Put_Line ("Arbitre : Il faut prendre au moins une allumette.");
	    elsif nbr_choisi > 3 then
	        Put_Line ("Arbitre : Il est interdit de prendre plus de 3 allumettes.");
	    elsif nbr_choisi > Allumettes then

		-- Afficher l’avertissement selon les allumettes restantes.
	        if Allumettes = 1 then 
        		Put_Line ("Arbitre : Il reste une seule allumette.");
                else
			Put ("Arbitre : Il reste seulement ");
			Put (Allumettes,1);
			Put_Line (" allumettes.");
		end if;
	    else
		    Null;
	    end if;
	    exit when nbr_choisi <= minimum and nbr_choisi > 0;
        end loop;

        -- Soustraire le nombre choisi du nombre des allumettes restantes
        Allumettes := Allumettes - nbr_choisi;

        -- Faire passer le rôles à l'autre joueur
        Est_Tour_Humain := not Est_Tour_Humain;
        
	exit when Allumettes = 0;
    end loop;

    -- Afficher qui a gagné
    New_Line;
    if Est_Tour_Humain then
        Put_Line ("Vous avez gagné.");
    else
        Put_Line ("J'ai gagné.");
    end if;
end Allumettes;
