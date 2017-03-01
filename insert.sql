--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.5
-- Dumped by pg_dump version 9.4.5
-- Started on 2017-03-01 16:01:20

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 186 (class 3079 OID 11855)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2089 (class 0 OID 0)
-- Dependencies: 186
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 173 (class 1259 OID 17386)
-- Name: atelier; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE atelier (
    id_atelier integer NOT NULL,
    des_atelier character varying(25),
    hmod integer,
    hm integer,
    fonction character varying(50)
);


ALTER TABLE atelier OWNER TO postgres;

--
-- TOC entry 172 (class 1259 OID 17384)
-- Name: atelier_id_atelier_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE atelier_id_atelier_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE atelier_id_atelier_seq OWNER TO postgres;

--
-- TOC entry 2090 (class 0 OID 0)
-- Dependencies: 172
-- Name: atelier_id_atelier_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE atelier_id_atelier_seq OWNED BY atelier.id_atelier;


--
-- TOC entry 175 (class 1259 OID 17395)
-- Name: categ_matiere; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE categ_matiere (
    id_categ_mat integer NOT NULL,
    nom_categ character varying(25)
);


ALTER TABLE categ_matiere OWNER TO postgres;

--
-- TOC entry 174 (class 1259 OID 17393)
-- Name: categ_matiere_id_categ_mat_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE categ_matiere_id_categ_mat_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE categ_matiere_id_categ_mat_seq OWNER TO postgres;

--
-- TOC entry 2091 (class 0 OID 0)
-- Dependencies: 174
-- Name: categ_matiere_id_categ_mat_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE categ_matiere_id_categ_mat_seq OWNED BY categ_matiere.id_categ_mat;


--
-- TOC entry 177 (class 1259 OID 17404)
-- Name: entree; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE entree (
    id_entree integer NOT NULL,
    id_mouvement integer NOT NULL,
    id_atelier integer,
    qte_entree integer
);


ALTER TABLE entree OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 17402)
-- Name: entree_id_entree_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE entree_id_entree_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE entree_id_entree_seq OWNER TO postgres;

--
-- TOC entry 2092 (class 0 OID 0)
-- Dependencies: 176
-- Name: entree_id_entree_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE entree_id_entree_seq OWNED BY entree.id_entree;


--
-- TOC entry 179 (class 1259 OID 17415)
-- Name: matiere; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE matiere (
    id_matiere integer NOT NULL,
    id_categ_mat integer NOT NULL,
    des_mat character varying(25),
    unite character varying(5),
    seuil integer,
    prix_unitaire numeric
);


ALTER TABLE matiere OWNER TO postgres;

--
-- TOC entry 178 (class 1259 OID 17413)
-- Name: matiere_id_matiere_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE matiere_id_matiere_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE matiere_id_matiere_seq OWNER TO postgres;

--
-- TOC entry 2093 (class 0 OID 0)
-- Dependencies: 178
-- Name: matiere_id_matiere_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE matiere_id_matiere_seq OWNED BY matiere.id_matiere;


--
-- TOC entry 181 (class 1259 OID 17425)
-- Name: mouvement; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE mouvement (
    id_mouvement integer NOT NULL,
    id_matiere integer NOT NULL,
    des_mvnt character varying(25),
    date_mvnt date
);


ALTER TABLE mouvement OWNER TO postgres;

--
-- TOC entry 180 (class 1259 OID 17423)
-- Name: mouvement_id_mouvement_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE mouvement_id_mouvement_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE mouvement_id_mouvement_seq OWNER TO postgres;

--
-- TOC entry 2094 (class 0 OID 0)
-- Dependencies: 180
-- Name: mouvement_id_mouvement_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE mouvement_id_mouvement_seq OWNED BY mouvement.id_mouvement;


--
-- TOC entry 183 (class 1259 OID 17435)
-- Name: sortie; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE sortie (
    id_sortie integer NOT NULL,
    id_atelier integer,
    id_mouvement integer NOT NULL,
    qte_sortie integer
);


ALTER TABLE sortie OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 17433)
-- Name: sortie_id_sortie_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE sortie_id_sortie_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sortie_id_sortie_seq OWNER TO postgres;

--
-- TOC entry 2095 (class 0 OID 0)
-- Dependencies: 182
-- Name: sortie_id_sortie_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE sortie_id_sortie_seq OWNED BY sortie.id_sortie;


--
-- TOC entry 185 (class 1259 OID 17446)
-- Name: utilisateur; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE utilisateur (
    id_utilisateur integer NOT NULL,
    nom_utilisateur character varying(10),
    mdp character varying(5)
);


ALTER TABLE utilisateur OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 17444)
-- Name: utilisateur_id_utilisateur_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE utilisateur_id_utilisateur_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE utilisateur_id_utilisateur_seq OWNER TO postgres;

--
-- TOC entry 2096 (class 0 OID 0)
-- Dependencies: 184
-- Name: utilisateur_id_utilisateur_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE utilisateur_id_utilisateur_seq OWNED BY utilisateur.id_utilisateur;


--
-- TOC entry 1919 (class 2604 OID 17389)
-- Name: id_atelier; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY atelier ALTER COLUMN id_atelier SET DEFAULT nextval('atelier_id_atelier_seq'::regclass);


--
-- TOC entry 1920 (class 2604 OID 17398)
-- Name: id_categ_mat; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY categ_matiere ALTER COLUMN id_categ_mat SET DEFAULT nextval('categ_matiere_id_categ_mat_seq'::regclass);


--
-- TOC entry 1921 (class 2604 OID 17407)
-- Name: id_entree; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY entree ALTER COLUMN id_entree SET DEFAULT nextval('entree_id_entree_seq'::regclass);


--
-- TOC entry 1922 (class 2604 OID 17418)
-- Name: id_matiere; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY matiere ALTER COLUMN id_matiere SET DEFAULT nextval('matiere_id_matiere_seq'::regclass);


--
-- TOC entry 1923 (class 2604 OID 17428)
-- Name: id_mouvement; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mouvement ALTER COLUMN id_mouvement SET DEFAULT nextval('mouvement_id_mouvement_seq'::regclass);


--
-- TOC entry 1924 (class 2604 OID 17438)
-- Name: id_sortie; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sortie ALTER COLUMN id_sortie SET DEFAULT nextval('sortie_id_sortie_seq'::regclass);


--
-- TOC entry 1925 (class 2604 OID 17449)
-- Name: id_utilisateur; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY utilisateur ALTER COLUMN id_utilisateur SET DEFAULT nextval('utilisateur_id_utilisateur_seq'::regclass);


--
-- TOC entry 2069 (class 0 OID 17386)
-- Dependencies: 173
-- Data for Name: atelier; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY atelier (id_atelier, des_atelier, hmod, hm, fonction) FROM stdin;
1	mag mp	50	0	stockage matiere premiere
2	coupe	5	\N	\N
3	detail couture	\N	\N	\N
4	assemblage	\N	\N	\N
5	mag psf	\N	\N	\N
\.


--
-- TOC entry 2097 (class 0 OID 0)
-- Dependencies: 172
-- Name: atelier_id_atelier_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('atelier_id_atelier_seq', 1, false);


--
-- TOC entry 2071 (class 0 OID 17395)
-- Dependencies: 175
-- Data for Name: categ_matiere; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY categ_matiere (id_categ_mat, nom_categ) FROM stdin;
1	matiere premiere
2	produit semi fini
3	produit fini
\.


--
-- TOC entry 2098 (class 0 OID 0)
-- Dependencies: 174
-- Name: categ_matiere_id_categ_mat_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('categ_matiere_id_categ_mat_seq', 1, false);


--
-- TOC entry 2073 (class 0 OID 17404)
-- Dependencies: 177
-- Data for Name: entree; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY entree (id_entree, id_mouvement, id_atelier, qte_entree) FROM stdin;
\.


--
-- TOC entry 2099 (class 0 OID 0)
-- Dependencies: 176
-- Name: entree_id_entree_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('entree_id_entree_seq', 1, false);


--
-- TOC entry 2075 (class 0 OID 17415)
-- Dependencies: 179
-- Data for Name: matiere; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY matiere (id_matiere, id_categ_mat, des_mat, unite, seuil, prix_unitaire) FROM stdin;
1	1	coton	metre	500	5000.00
2	1	jeans	metre	700	4000.00
4	1	soie	metre	200	3000.00
5	1	mousseline	metre	200	2500.00
6	1	tissus imprime	metre	800	5500.00
3	1	dentelle	metre	200	5000.00
7	1	velours	metre	900	3500.00
8	1	doublure	metre	1000	2000.00
9	1	fermeture	piece	1000	500.00
10	1	fils	roure	2000	200.00
11	1	patchwork	piece	1000	50.00
12	1	boutons	piece	2000	50.00
\.


--
-- TOC entry 2100 (class 0 OID 0)
-- Dependencies: 178
-- Name: matiere_id_matiere_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('matiere_id_matiere_seq', 1, false);


--
-- TOC entry 2077 (class 0 OID 17425)
-- Dependencies: 181
-- Data for Name: mouvement; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY mouvement (id_mouvement, id_matiere, des_mvnt, date_mvnt) FROM stdin;
\.


--
-- TOC entry 2101 (class 0 OID 0)
-- Dependencies: 180
-- Name: mouvement_id_mouvement_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('mouvement_id_mouvement_seq', 1, false);


--
-- TOC entry 2079 (class 0 OID 17435)
-- Dependencies: 183
-- Data for Name: sortie; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY sortie (id_sortie, id_atelier, id_mouvement, qte_sortie) FROM stdin;
\.


--
-- TOC entry 2102 (class 0 OID 0)
-- Dependencies: 182
-- Name: sortie_id_sortie_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('sortie_id_sortie_seq', 1, false);


--
-- TOC entry 2081 (class 0 OID 17446)
-- Dependencies: 185
-- Data for Name: utilisateur; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY utilisateur (id_utilisateur, nom_utilisateur, mdp) FROM stdin;
\.


--
-- TOC entry 2103 (class 0 OID 0)
-- Dependencies: 184
-- Name: utilisateur_id_utilisateur_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('utilisateur_id_utilisateur_seq', 1, false);


--
-- TOC entry 1928 (class 2606 OID 17391)
-- Name: pk_atelier; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY atelier
    ADD CONSTRAINT pk_atelier PRIMARY KEY (id_atelier);


--
-- TOC entry 1931 (class 2606 OID 17400)
-- Name: pk_categ_matiere; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY categ_matiere
    ADD CONSTRAINT pk_categ_matiere PRIMARY KEY (id_categ_mat);


--
-- TOC entry 1936 (class 2606 OID 17409)
-- Name: pk_entree; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY entree
    ADD CONSTRAINT pk_entree PRIMARY KEY (id_entree);


--
-- TOC entry 1940 (class 2606 OID 17420)
-- Name: pk_matiere; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY matiere
    ADD CONSTRAINT pk_matiere PRIMARY KEY (id_matiere);


--
-- TOC entry 1944 (class 2606 OID 17430)
-- Name: pk_mouvement; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY mouvement
    ADD CONSTRAINT pk_mouvement PRIMARY KEY (id_mouvement);


--
-- TOC entry 1947 (class 2606 OID 17440)
-- Name: pk_sortie; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY sortie
    ADD CONSTRAINT pk_sortie PRIMARY KEY (id_sortie);


--
-- TOC entry 1951 (class 2606 OID 17451)
-- Name: pk_utilisateur; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY utilisateur
    ADD CONSTRAINT pk_utilisateur PRIMARY KEY (id_utilisateur);


--
-- TOC entry 1926 (class 1259 OID 17392)
-- Name: atelier_pk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX atelier_pk ON atelier USING btree (id_atelier);


--
-- TOC entry 1937 (class 1259 OID 17422)
-- Name: categ_mat_fk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX categ_mat_fk ON matiere USING btree (id_categ_mat);


--
-- TOC entry 1929 (class 1259 OID 17401)
-- Name: categ_matiere_pk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX categ_matiere_pk ON categ_matiere USING btree (id_categ_mat);


--
-- TOC entry 1932 (class 1259 OID 17412)
-- Name: entree_at_fk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX entree_at_fk ON entree USING btree (id_atelier);


--
-- TOC entry 1933 (class 1259 OID 17410)
-- Name: entree_pk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX entree_pk ON entree USING btree (id_entree);


--
-- TOC entry 1938 (class 1259 OID 17421)
-- Name: matiere_pk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX matiere_pk ON matiere USING btree (id_matiere);


--
-- TOC entry 1941 (class 1259 OID 17432)
-- Name: mouv_mat_fk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX mouv_mat_fk ON mouvement USING btree (id_matiere);


--
-- TOC entry 1942 (class 1259 OID 17431)
-- Name: mouvement_pk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX mouvement_pk ON mouvement USING btree (id_mouvement);


--
-- TOC entry 1934 (class 1259 OID 17411)
-- Name: mouvnt_entree_fk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX mouvnt_entree_fk ON entree USING btree (id_mouvement);


--
-- TOC entry 1945 (class 1259 OID 17442)
-- Name: movnt_sortie_fk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX movnt_sortie_fk ON sortie USING btree (id_mouvement);


--
-- TOC entry 1948 (class 1259 OID 17443)
-- Name: sortie_at_fk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX sortie_at_fk ON sortie USING btree (id_atelier);


--
-- TOC entry 1949 (class 1259 OID 17441)
-- Name: sortie_pk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX sortie_pk ON sortie USING btree (id_sortie);


--
-- TOC entry 1952 (class 1259 OID 17452)
-- Name: utilisateur_pk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX utilisateur_pk ON utilisateur USING btree (id_utilisateur);


--
-- TOC entry 1953 (class 2606 OID 17453)
-- Name: fk_entree_entree_at_atelier; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY entree
    ADD CONSTRAINT fk_entree_entree_at_atelier FOREIGN KEY (id_atelier) REFERENCES atelier(id_atelier) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1954 (class 2606 OID 17458)
-- Name: fk_entree_mouvnt_en_mouvemen; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY entree
    ADD CONSTRAINT fk_entree_mouvnt_en_mouvemen FOREIGN KEY (id_mouvement) REFERENCES mouvement(id_mouvement) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1955 (class 2606 OID 17463)
-- Name: fk_matiere_categ_mat_categ_ma; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY matiere
    ADD CONSTRAINT fk_matiere_categ_mat_categ_ma FOREIGN KEY (id_categ_mat) REFERENCES categ_matiere(id_categ_mat) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1956 (class 2606 OID 17468)
-- Name: fk_mouvemen_mouv_mat_matiere; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mouvement
    ADD CONSTRAINT fk_mouvemen_mouv_mat_matiere FOREIGN KEY (id_matiere) REFERENCES matiere(id_matiere) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1957 (class 2606 OID 17473)
-- Name: fk_sortie_movnt_sor_mouvemen; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sortie
    ADD CONSTRAINT fk_sortie_movnt_sor_mouvemen FOREIGN KEY (id_mouvement) REFERENCES mouvement(id_mouvement) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1958 (class 2606 OID 17478)
-- Name: fk_sortie_sortie_at_atelier; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sortie
    ADD CONSTRAINT fk_sortie_sortie_at_atelier FOREIGN KEY (id_atelier) REFERENCES atelier(id_atelier) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2088 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2017-03-01 16:01:20

--
-- PostgreSQL database dump complete
--

