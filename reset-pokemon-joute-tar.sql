toc.dat                                                                                             0000600 0004000 0002000 00000033514 14665301773 0014461 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        PGDMP                       |           pokemon-joute    16.3    16.3 1    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false         �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false         �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false         �           1262    25637    pokemon-joute    DATABASE     �   CREATE DATABASE "pokemon-joute" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'French_France.1252';
    DROP DATABASE "pokemon-joute";
                postgres    false         �            1259    25638    attaque    TABLE     �   CREATE TABLE public.attaque (
    id bigint NOT NULL,
    nom character varying(50) NOT NULL,
    type character varying(20) NOT NULL,
    degats integer NOT NULL
);
    DROP TABLE public.attaque;
       public         heap    postgres    false         �            1259    25641    attaque_id_seq    SEQUENCE     �   ALTER TABLE public.attaque ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.attaque_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    215         �            1259    25642    dresseur    TABLE     �   CREATE TABLE public.dresseur (
    id bigint NOT NULL,
    pseudo character varying(50) NOT NULL,
    mot_de_passe character varying(50) NOT NULL,
    portefeuille integer
);
    DROP TABLE public.dresseur;
       public         heap    postgres    false         �            1259    25645    dresseur_id_seq    SEQUENCE     �   ALTER TABLE public.dresseur ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.dresseur_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    217         �            1259    25646    dresseur_objet    TABLE     �   CREATE TABLE public.dresseur_objet (
    dresseur_id bigint NOT NULL,
    objet_id bigint NOT NULL,
    quantite integer,
    id bigint NOT NULL
);
 "   DROP TABLE public.dresseur_objet;
       public         heap    postgres    false         �            1259    25775    dresseur_objet_id_seq    SEQUENCE     ~   CREATE SEQUENCE public.dresseur_objet_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.dresseur_objet_id_seq;
       public          postgres    false    219         �           0    0    dresseur_objet_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.dresseur_objet_id_seq OWNED BY public.dresseur_objet.id;
          public          postgres    false    228         �            1259    25649    espece    TABLE     �   CREATE TABLE public.espece (
    id bigint NOT NULL,
    nom character varying(50) NOT NULL,
    pv_initial integer NOT NULL,
    attaque_initiale_id bigint,
    type character varying NOT NULL
);
    DROP TABLE public.espece;
       public         heap    postgres    false         �            1259    25654    espece_id_seq    SEQUENCE     �   ALTER TABLE public.espece ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.espece_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    220         �            1259    25655    objet    TABLE     �   CREATE TABLE public.objet (
    id bigint NOT NULL,
    nom character varying(50) NOT NULL,
    prix integer NOT NULL,
    type character varying(20) NOT NULL
);
    DROP TABLE public.objet;
       public         heap    postgres    false         �            1259    25658    objet_id_seq    SEQUENCE     �   ALTER TABLE public.objet ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.objet_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    222         �            1259    25659    pokemon    TABLE     �   CREATE TABLE public.pokemon (
    id bigint NOT NULL,
    nom character varying(50) NOT NULL,
    niveau integer NOT NULL,
    experience integer,
    espece_id bigint NOT NULL,
    dresseur_id bigint,
    pv integer,
    pv_max integer NOT NULL
);
    DROP TABLE public.pokemon;
       public         heap    postgres    false         �            1259    25662    pokemon_attaque    TABLE     h   CREATE TABLE public.pokemon_attaque (
    pokemon_id bigint NOT NULL,
    attaque_id bigint NOT NULL
);
 #   DROP TABLE public.pokemon_attaque;
       public         heap    postgres    false         �            1259    25665    pokemon_id_seq    SEQUENCE     �   ALTER TABLE public.pokemon ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.pokemon_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    224         �            1259    25666    pokemon_seq    SEQUENCE     t   CREATE SEQUENCE public.pokemon_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.pokemon_seq;
       public          postgres    false         8           2604    25776    dresseur_objet id    DEFAULT     v   ALTER TABLE ONLY public.dresseur_objet ALTER COLUMN id SET DEFAULT nextval('public.dresseur_objet_id_seq'::regclass);
 @   ALTER TABLE public.dresseur_objet ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    228    219         �          0    25638    attaque 
   TABLE DATA           8   COPY public.attaque (id, nom, type, degats) FROM stdin;
    public          postgres    false    215       4831.dat �          0    25642    dresseur 
   TABLE DATA           J   COPY public.dresseur (id, pseudo, mot_de_passe, portefeuille) FROM stdin;
    public          postgres    false    217       4833.dat �          0    25646    dresseur_objet 
   TABLE DATA           M   COPY public.dresseur_objet (dresseur_id, objet_id, quantite, id) FROM stdin;
    public          postgres    false    219       4835.dat �          0    25649    espece 
   TABLE DATA           P   COPY public.espece (id, nom, pv_initial, attaque_initiale_id, type) FROM stdin;
    public          postgres    false    220       4836.dat �          0    25655    objet 
   TABLE DATA           4   COPY public.objet (id, nom, prix, type) FROM stdin;
    public          postgres    false    222       4838.dat �          0    25659    pokemon 
   TABLE DATA           b   COPY public.pokemon (id, nom, niveau, experience, espece_id, dresseur_id, pv, pv_max) FROM stdin;
    public          postgres    false    224       4840.dat �          0    25662    pokemon_attaque 
   TABLE DATA           A   COPY public.pokemon_attaque (pokemon_id, attaque_id) FROM stdin;
    public          postgres    false    225       4841.dat �           0    0    attaque_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.attaque_id_seq', 1, false);
          public          postgres    false    216         �           0    0    dresseur_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.dresseur_id_seq', 1, false);
          public          postgres    false    218         �           0    0    dresseur_objet_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.dresseur_objet_id_seq', 1, false);
          public          postgres    false    228         �           0    0    espece_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.espece_id_seq', 1, false);
          public          postgres    false    221         �           0    0    objet_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.objet_id_seq', 1, false);
          public          postgres    false    223         �           0    0    pokemon_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.pokemon_id_seq', 1, false);
          public          postgres    false    226         �           0    0    pokemon_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.pokemon_seq', 1, false);
          public          postgres    false    227         :           2606    25668    attaque attaque_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.attaque
    ADD CONSTRAINT attaque_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.attaque DROP CONSTRAINT attaque_pkey;
       public            postgres    false    215         @           2606    25778 "   dresseur_objet dresseur_objet_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.dresseur_objet
    ADD CONSTRAINT dresseur_objet_pkey PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.dresseur_objet DROP CONSTRAINT dresseur_objet_pkey;
       public            postgres    false    219         <           2606    25670    dresseur dresseur_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.dresseur
    ADD CONSTRAINT dresseur_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.dresseur DROP CONSTRAINT dresseur_pkey;
       public            postgres    false    217         B           2606    25672    espece espece_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.espece
    ADD CONSTRAINT espece_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.espece DROP CONSTRAINT espece_pkey;
       public            postgres    false    220         D           2606    25676    objet objet_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.objet
    ADD CONSTRAINT objet_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.objet DROP CONSTRAINT objet_pkey;
       public            postgres    false    222         H           2606    25678 $   pokemon_attaque pokemon_attaque_pkey 
   CONSTRAINT     v   ALTER TABLE ONLY public.pokemon_attaque
    ADD CONSTRAINT pokemon_attaque_pkey PRIMARY KEY (pokemon_id, attaque_id);
 N   ALTER TABLE ONLY public.pokemon_attaque DROP CONSTRAINT pokemon_attaque_pkey;
       public            postgres    false    225    225         F           2606    25680    pokemon pokemon_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.pokemon
    ADD CONSTRAINT pokemon_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.pokemon DROP CONSTRAINT pokemon_pkey;
       public            postgres    false    224         >           2606    25682    dresseur pseudo_unique 
   CONSTRAINT     S   ALTER TABLE ONLY public.dresseur
    ADD CONSTRAINT pseudo_unique UNIQUE (pseudo);
 @   ALTER TABLE ONLY public.dresseur DROP CONSTRAINT pseudo_unique;
       public            postgres    false    217         N           2606    25683    pokemon_attaque attaque_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.pokemon_attaque
    ADD CONSTRAINT attaque_id_fkey FOREIGN KEY (attaque_id) REFERENCES public.attaque(id) NOT VALID;
 I   ALTER TABLE ONLY public.pokemon_attaque DROP CONSTRAINT attaque_id_fkey;
       public          postgres    false    4666    215    225         K           2606    25688    espece attaque_initiale_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.espece
    ADD CONSTRAINT attaque_initiale_id_fkey FOREIGN KEY (attaque_initiale_id) REFERENCES public.attaque(id) NOT VALID;
 I   ALTER TABLE ONLY public.espece DROP CONSTRAINT attaque_initiale_id_fkey;
       public          postgres    false    4666    220    215         L           2606    25693    pokemon dresseur_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.pokemon
    ADD CONSTRAINT dresseur_id_fkey FOREIGN KEY (dresseur_id) REFERENCES public.dresseur(id) NOT VALID;
 B   ALTER TABLE ONLY public.pokemon DROP CONSTRAINT dresseur_id_fkey;
       public          postgres    false    224    4668    217         I           2606    25698    dresseur_objet dresseur_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.dresseur_objet
    ADD CONSTRAINT dresseur_id_fkey FOREIGN KEY (dresseur_id) REFERENCES public.dresseur(id) NOT VALID;
 I   ALTER TABLE ONLY public.dresseur_objet DROP CONSTRAINT dresseur_id_fkey;
       public          postgres    false    4668    219    217         M           2606    25703    pokemon espece_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.pokemon
    ADD CONSTRAINT espece_id_fkey FOREIGN KEY (espece_id) REFERENCES public.espece(id) NOT VALID;
 @   ALTER TABLE ONLY public.pokemon DROP CONSTRAINT espece_id_fkey;
       public          postgres    false    220    4674    224         J           2606    25708    dresseur_objet objet_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.dresseur_objet
    ADD CONSTRAINT objet_id_fkey FOREIGN KEY (objet_id) REFERENCES public.objet(id) NOT VALID;
 F   ALTER TABLE ONLY public.dresseur_objet DROP CONSTRAINT objet_id_fkey;
       public          postgres    false    222    219    4676         O           2606    25713    pokemon_attaque pokemon_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.pokemon_attaque
    ADD CONSTRAINT pokemon_id_fkey FOREIGN KEY (pokemon_id) REFERENCES public.pokemon(id) NOT VALID;
 I   ALTER TABLE ONLY public.pokemon_attaque DROP CONSTRAINT pokemon_id_fkey;
       public          postgres    false    225    224    4678                                                                                                                                                                                            4831.dat                                                                                            0000600 0004000 0002000 00000000005 14665301773 0014260 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        \.


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           4833.dat                                                                                            0000600 0004000 0002000 00000000005 14665301773 0014262 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        \.


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           4835.dat                                                                                            0000600 0004000 0002000 00000000005 14665301773 0014264 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        \.


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           4836.dat                                                                                            0000600 0004000 0002000 00000000005 14665301773 0014265 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        \.


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           4838.dat                                                                                            0000600 0004000 0002000 00000000005 14665301773 0014267 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        \.


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           4840.dat                                                                                            0000600 0004000 0002000 00000000005 14665301773 0014260 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        \.


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           4841.dat                                                                                            0000600 0004000 0002000 00000000005 14665301773 0014261 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        \.


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           restore.sql                                                                                         0000600 0004000 0002000 00000026633 14665301773 0015412 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        --
-- NOTE:
--
-- File paths need to be edited. Search for $$PATH$$ and
-- replace it with the path to the directory containing
-- the extracted data files.
--
--
-- PostgreSQL database dump
--

-- Dumped from database version 16.3
-- Dumped by pg_dump version 16.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE "pokemon-joute";
--
-- Name: pokemon-joute; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "pokemon-joute" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'French_France.1252';


ALTER DATABASE "pokemon-joute" OWNER TO postgres;

\connect -reuse-previous=on "dbname='pokemon-joute'"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: attaque; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.attaque (
    id bigint NOT NULL,
    nom character varying(50) NOT NULL,
    type character varying(20) NOT NULL,
    degats integer NOT NULL
);


ALTER TABLE public.attaque OWNER TO postgres;

--
-- Name: attaque_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.attaque ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.attaque_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: dresseur; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.dresseur (
    id bigint NOT NULL,
    pseudo character varying(50) NOT NULL,
    mot_de_passe character varying(50) NOT NULL,
    portefeuille integer
);


ALTER TABLE public.dresseur OWNER TO postgres;

--
-- Name: dresseur_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.dresseur ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.dresseur_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: dresseur_objet; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.dresseur_objet (
    dresseur_id bigint NOT NULL,
    objet_id bigint NOT NULL,
    quantite integer,
    id bigint NOT NULL
);


ALTER TABLE public.dresseur_objet OWNER TO postgres;

--
-- Name: dresseur_objet_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.dresseur_objet_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.dresseur_objet_id_seq OWNER TO postgres;

--
-- Name: dresseur_objet_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.dresseur_objet_id_seq OWNED BY public.dresseur_objet.id;


--
-- Name: espece; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.espece (
    id bigint NOT NULL,
    nom character varying(50) NOT NULL,
    pv_initial integer NOT NULL,
    attaque_initiale_id bigint,
    type character varying NOT NULL
);


ALTER TABLE public.espece OWNER TO postgres;

--
-- Name: espece_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.espece ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.espece_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: objet; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.objet (
    id bigint NOT NULL,
    nom character varying(50) NOT NULL,
    prix integer NOT NULL,
    type character varying(20) NOT NULL
);


ALTER TABLE public.objet OWNER TO postgres;

--
-- Name: objet_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.objet ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.objet_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: pokemon; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pokemon (
    id bigint NOT NULL,
    nom character varying(50) NOT NULL,
    niveau integer NOT NULL,
    experience integer,
    espece_id bigint NOT NULL,
    dresseur_id bigint,
    pv integer,
    pv_max integer NOT NULL
);


ALTER TABLE public.pokemon OWNER TO postgres;

--
-- Name: pokemon_attaque; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pokemon_attaque (
    pokemon_id bigint NOT NULL,
    attaque_id bigint NOT NULL
);


ALTER TABLE public.pokemon_attaque OWNER TO postgres;

--
-- Name: pokemon_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.pokemon ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.pokemon_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: pokemon_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pokemon_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.pokemon_seq OWNER TO postgres;

--
-- Name: dresseur_objet id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dresseur_objet ALTER COLUMN id SET DEFAULT nextval('public.dresseur_objet_id_seq'::regclass);


--
-- Data for Name: attaque; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.attaque (id, nom, type, degats) FROM stdin;
\.
COPY public.attaque (id, nom, type, degats) FROM '$$PATH$$/4831.dat';

--
-- Data for Name: dresseur; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.dresseur (id, pseudo, mot_de_passe, portefeuille) FROM stdin;
\.
COPY public.dresseur (id, pseudo, mot_de_passe, portefeuille) FROM '$$PATH$$/4833.dat';

--
-- Data for Name: dresseur_objet; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.dresseur_objet (dresseur_id, objet_id, quantite, id) FROM stdin;
\.
COPY public.dresseur_objet (dresseur_id, objet_id, quantite, id) FROM '$$PATH$$/4835.dat';

--
-- Data for Name: espece; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.espece (id, nom, pv_initial, attaque_initiale_id, type) FROM stdin;
\.
COPY public.espece (id, nom, pv_initial, attaque_initiale_id, type) FROM '$$PATH$$/4836.dat';

--
-- Data for Name: objet; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.objet (id, nom, prix, type) FROM stdin;
\.
COPY public.objet (id, nom, prix, type) FROM '$$PATH$$/4838.dat';

--
-- Data for Name: pokemon; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pokemon (id, nom, niveau, experience, espece_id, dresseur_id, pv, pv_max) FROM stdin;
\.
COPY public.pokemon (id, nom, niveau, experience, espece_id, dresseur_id, pv, pv_max) FROM '$$PATH$$/4840.dat';

--
-- Data for Name: pokemon_attaque; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pokemon_attaque (pokemon_id, attaque_id) FROM stdin;
\.
COPY public.pokemon_attaque (pokemon_id, attaque_id) FROM '$$PATH$$/4841.dat';

--
-- Name: attaque_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.attaque_id_seq', 1, false);


--
-- Name: dresseur_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.dresseur_id_seq', 1, false);


--
-- Name: dresseur_objet_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.dresseur_objet_id_seq', 1, false);


--
-- Name: espece_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.espece_id_seq', 1, false);


--
-- Name: objet_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.objet_id_seq', 1, false);


--
-- Name: pokemon_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pokemon_id_seq', 1, false);


--
-- Name: pokemon_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pokemon_seq', 1, false);


--
-- Name: attaque attaque_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attaque
    ADD CONSTRAINT attaque_pkey PRIMARY KEY (id);


--
-- Name: dresseur_objet dresseur_objet_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dresseur_objet
    ADD CONSTRAINT dresseur_objet_pkey PRIMARY KEY (id);


--
-- Name: dresseur dresseur_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dresseur
    ADD CONSTRAINT dresseur_pkey PRIMARY KEY (id);


--
-- Name: espece espece_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.espece
    ADD CONSTRAINT espece_pkey PRIMARY KEY (id);


--
-- Name: objet objet_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.objet
    ADD CONSTRAINT objet_pkey PRIMARY KEY (id);


--
-- Name: pokemon_attaque pokemon_attaque_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pokemon_attaque
    ADD CONSTRAINT pokemon_attaque_pkey PRIMARY KEY (pokemon_id, attaque_id);


--
-- Name: pokemon pokemon_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pokemon
    ADD CONSTRAINT pokemon_pkey PRIMARY KEY (id);


--
-- Name: dresseur pseudo_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dresseur
    ADD CONSTRAINT pseudo_unique UNIQUE (pseudo);


--
-- Name: pokemon_attaque attaque_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pokemon_attaque
    ADD CONSTRAINT attaque_id_fkey FOREIGN KEY (attaque_id) REFERENCES public.attaque(id) NOT VALID;


--
-- Name: espece attaque_initiale_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.espece
    ADD CONSTRAINT attaque_initiale_id_fkey FOREIGN KEY (attaque_initiale_id) REFERENCES public.attaque(id) NOT VALID;


--
-- Name: pokemon dresseur_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pokemon
    ADD CONSTRAINT dresseur_id_fkey FOREIGN KEY (dresseur_id) REFERENCES public.dresseur(id) NOT VALID;


--
-- Name: dresseur_objet dresseur_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dresseur_objet
    ADD CONSTRAINT dresseur_id_fkey FOREIGN KEY (dresseur_id) REFERENCES public.dresseur(id) NOT VALID;


--
-- Name: pokemon espece_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pokemon
    ADD CONSTRAINT espece_id_fkey FOREIGN KEY (espece_id) REFERENCES public.espece(id) NOT VALID;


--
-- Name: dresseur_objet objet_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dresseur_objet
    ADD CONSTRAINT objet_id_fkey FOREIGN KEY (objet_id) REFERENCES public.objet(id) NOT VALID;


--
-- Name: pokemon_attaque pokemon_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pokemon_attaque
    ADD CONSTRAINT pokemon_id_fkey FOREIGN KEY (pokemon_id) REFERENCES public.pokemon(id) NOT VALID;


--
-- PostgreSQL database dump complete
--

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     