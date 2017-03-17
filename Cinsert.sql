
INSERT INTO atelier (id_atelier, des_atelier, hmod, hm, fonction) VALUES (1, 'mag mp', 50, 0, 'stockage matiere premiere');
INSERT INTO atelier (id_atelier, des_atelier, hmod, hm, fonction) VALUES (2, 'coupe', 5, 5, 'atelier de coupe');
INSERT INTO atelier (id_atelier, des_atelier, hmod, hm, fonction) VALUES (3, 'detail couture', 9, 8, 'avant assemblage');
INSERT INTO atelier (id_atelier, des_atelier, hmod, hm, fonction) VALUES (4, 'assemblage', 8, 8, 'finition');
INSERT INTO atelier (id_atelier, des_atelier, hmod, hm, fonction) VALUES (5, 'mag psf', 8, 0, 'stockage produit semi fini');
INSERT INTO atelier (id_atelier, des_atelier, hmod, hm, fonction) VALUES (6, 'mag pf', 8, 0, 'stockage produit fini');



INSERT INTO categ_matiere (id_categ_mat, nom_categ) VALUES (1, 'matiere premiere');
INSERT INTO categ_matiere (id_categ_mat, nom_categ) VALUES (2, 'produit semi fini');
INSERT INTO categ_matiere (id_categ_mat, nom_categ) VALUES (3, 'produit fini');



INSERT INTO matiere (id_matiere, id_categ_mat, des_mat, unite, seuil, prix_unitaire) VALUES (1, 1, 'coton', 'metre', 500, 5000.00);
INSERT INTO matiere (id_matiere, id_categ_mat, des_mat, unite, seuil, prix_unitaire) VALUES (2, 1, 'jeans', 'metre', 700, 4000.00);
INSERT INTO matiere (id_matiere, id_categ_mat, des_mat, unite, seuil, prix_unitaire) VALUES (4, 1, 'soie', 'metre', 200, 3000.00);
INSERT INTO matiere (id_matiere, id_categ_mat, des_mat, unite, seuil, prix_unitaire) VALUES (5, 1, 'mousseline', 'metre', 200, 2500.00);
INSERT INTO matiere (id_matiere, id_categ_mat, des_mat, unite, seuil, prix_unitaire) VALUES (6, 1, 'tissus imprime', 'metre', 800, 5500.00);
INSERT INTO matiere (id_matiere, id_categ_mat, des_mat, unite, seuil, prix_unitaire) VALUES (3, 1, 'dentelle', 'metre', 200, 5000.00);
INSERT INTO matiere (id_matiere, id_categ_mat, des_mat, unite, seuil, prix_unitaire) VALUES (7, 1, 'velours', 'metre', 900, 3500.00);
INSERT INTO matiere (id_matiere, id_categ_mat, des_mat, unite, seuil, prix_unitaire) VALUES (8, 1, 'doublure', 'metre', 1000, 2000.00);
INSERT INTO matiere (id_matiere, id_categ_mat, des_mat, unite, seuil, prix_unitaire) VALUES (9, 1, 'fermeture', 'piece', 1000, 500.00);
INSERT INTO matiere (id_matiere, id_categ_mat, des_mat, unite, seuil, prix_unitaire) VALUES (10, 1, 'fils', 'roure', 2000, 200.00);
INSERT INTO matiere (id_matiere, id_categ_mat, des_mat, unite, seuil, prix_unitaire) VALUES (11, 1, 'patchwork', 'piece', 1000, 50.00);
INSERT INTO matiere (id_matiere, id_categ_mat, des_mat, unite, seuil, prix_unitaire) VALUES (12, 1, 'boutons', 'piece', 2000, 50.00);


INSERT INTO utilisateur (id_utilisateur, nom_utilisateur, mdp) VALUES (2, 'tsiory', 'etu43');
INSERT INTO utilisateur (id_utilisateur, nom_utilisateur, mdp) VALUES (1, 'fabienne', 'etu43');

ALTER SEQUENCE atelier_id_atelier_seq RESTART WITH 7;
ALTER SEQUENCE categ_matiere_id_categ_mat_seq RESTART WITH 4;
ALTER SEQUENCE entree_id_entree_seq RESTART WITH 1;
ALTER SEQUENCE matiere_id_matiere_seq RESTART WITH 13;
ALTER SEQUENCE sortie_id_sortie_seq RESTART WITH 1;
ALTER SEQUENCE utilisateur_id_utilisateur_seq RESTART WITH 1;