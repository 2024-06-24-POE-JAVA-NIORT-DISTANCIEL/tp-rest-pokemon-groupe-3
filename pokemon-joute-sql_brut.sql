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


--
-- Data for Name: dresseur; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.dresseur (id, pseudo, mot_de_passe, portefeuille) FROM stdin;
\.


--
-- Data for Name: dresseur_objet; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.dresseur_objet (dresseur_id, objet_id, quantite, id) FROM stdin;
\.


--
-- Data for Name: espece; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.espece (id, nom, pv_initial, attaque_initiale_id, type) FROM stdin;
\.


--
-- Data for Name: objet; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.objet (id, nom, prix, type) FROM stdin;
\.


--
-- Data for Name: pokemon; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pokemon (id, nom, niveau, experience, espece_id, dresseur_id, pv, pv_max) FROM stdin;
\.


--
-- Data for Name: pokemon_attaque; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pokemon_attaque (pokemon_id, attaque_id) FROM stdin;
\.


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

