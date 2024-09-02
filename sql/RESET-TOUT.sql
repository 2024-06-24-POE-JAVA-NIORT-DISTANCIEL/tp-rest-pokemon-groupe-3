DELETE FROM public.pokemon_attaque;

DELETE FROM public.pokemon;
ALTER SEQUENCE pokemon_id_seq RESTART WITH 1;

DELETE FROM public.espece;
ALTER SEQUENCE espece_id_seq RESTART WITH 1;

DELETE FROM public.attaque;
ALTER SEQUENCE attaque_id_seq RESTART WITH 1;

DELETE FROM public.dresseur_objet;
ALTER SEQUENCE dresseur_objet_id_seq RESTART WITH 1;

DELETE FROM public.dresseur;
ALTER SEQUENCE dresseur_id_seq RESTART WITH 1;

DELETE FROM public.objet;
ALTER SEQUENCE objet_id_seq RESTART WITH 1;
