--
-- PostgreSQL database dump
--

-- Dumped from database version 16.8 (Ubuntu 16.8-0ubuntu0.24.04.1)
-- Dumped by pg_dump version 17.4

-- Started on 2025-04-01 02:25:11

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 3431 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- TOC entry 851 (class 1247 OID 24649)
-- Name: action_type; Type: TYPE; Schema: public; Owner: owner
--

CREATE TYPE public.action_type AS ENUM (
    'SEND_STATUS',
    'SEND_MESSAGE',
    'GET_ALL_USERS',
    'GET_ACTIVE_USERS',
    'GET_MESSAGES_OF_USER',
    'DELETE_USER',
    'DELETE_MESSAGE'
);


ALTER TYPE public.action_type OWNER TO owner;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 220 (class 1259 OID 24664)
-- Name: activities; Type: TABLE; Schema: public; Owner: owner
--

CREATE TABLE public.activities (
    id bigint NOT NULL,
    user_id bigint,
    "time" timestamp without time zone DEFAULT now(),
    type character varying(255) NOT NULL
);


ALTER TABLE public.activities OWNER TO owner;

--
-- TOC entry 219 (class 1259 OID 24663)
-- Name: activities_id_seq; Type: SEQUENCE; Schema: public; Owner: owner
--

CREATE SEQUENCE public.activities_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.activities_id_seq OWNER TO owner;

--
-- TOC entry 3432 (class 0 OID 0)
-- Dependencies: 219
-- Name: activities_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: owner
--

ALTER SEQUENCE public.activities_id_seq OWNED BY public.activities.id;


--
-- TOC entry 218 (class 1259 OID 24621)
-- Name: messages; Type: TABLE; Schema: public; Owner: owner
--

CREATE TABLE public.messages (
    id bigint NOT NULL,
    user_id bigint,
    body character varying(255) NOT NULL,
    "time" timestamp without time zone DEFAULT now()
);


ALTER TABLE public.messages OWNER TO owner;

--
-- TOC entry 217 (class 1259 OID 24620)
-- Name: messages_id_seq; Type: SEQUENCE; Schema: public; Owner: owner
--

CREATE SEQUENCE public.messages_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.messages_id_seq OWNER TO owner;

--
-- TOC entry 3433 (class 0 OID 0)
-- Dependencies: 217
-- Name: messages_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: owner
--

ALTER SEQUENCE public.messages_id_seq OWNED BY public.messages.id;


--
-- TOC entry 216 (class 1259 OID 24612)
-- Name: users; Type: TABLE; Schema: public; Owner: owner
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    uuid character varying(255) NOT NULL,
    role character varying(255) NOT NULL
);


ALTER TABLE public.users OWNER TO owner;

--
-- TOC entry 215 (class 1259 OID 24611)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: owner
--

CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_id_seq OWNER TO owner;

--
-- TOC entry 3434 (class 0 OID 0)
-- Dependencies: 215
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: owner
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- TOC entry 3265 (class 2604 OID 24667)
-- Name: activities id; Type: DEFAULT; Schema: public; Owner: owner
--

ALTER TABLE ONLY public.activities ALTER COLUMN id SET DEFAULT nextval('public.activities_id_seq'::regclass);


--
-- TOC entry 3263 (class 2604 OID 24624)
-- Name: messages id; Type: DEFAULT; Schema: public; Owner: owner
--

ALTER TABLE ONLY public.messages ALTER COLUMN id SET DEFAULT nextval('public.messages_id_seq'::regclass);


--
-- TOC entry 3262 (class 2604 OID 24615)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: owner
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- TOC entry 3425 (class 0 OID 24664)
-- Dependencies: 220
-- Data for Name: activities; Type: TABLE DATA; Schema: public; Owner: owner
--

COPY public.activities (id, user_id, "time", type) FROM stdin;
908	\N	2025-03-30 21:05:16.902431	SEND_STATUS
932	10	2025-03-31 22:06:06.948753	GET_ALL_USERS
909	\N	2025-03-30 21:05:21.899486	SEND_STATUS
910	\N	2025-03-30 21:08:21.711007	SEND_STATUS
911	\N	2025-03-30 21:08:26.752984	SEND_STATUS
913	7	2025-03-30 21:08:36.715392	SEND_STATUS
912	\N	2025-03-30 21:08:31.713219	SEND_STATUS
933	10	2025-03-31 22:26:22.524838	GET_ALL_USERS
934	10	2025-03-31 22:26:35.756493	GET_ALL_USERS
935	10	2025-03-31 22:26:51.165681	GET_ACTIVE_USERS
936	10	2025-03-31 22:27:05.845962	GET_MESSAGES_BY_USER_ID
937	10	2025-03-31 22:27:32.210358	GET_ALL_USERS
938	10	2025-03-31 22:27:41.982027	DELETE_USER_BY_ID
939	10	2025-03-31 22:27:55.738271	GET_MESSAGES_BY_USER_ID
940	10	2025-03-31 22:28:03.821942	GET_MESSAGES_BY_USER_ID
941	10	2025-03-31 22:28:15.650105	DELETE_MESSAGE_BY_ID
660	5	2025-03-30 19:04:55.610874	SEND_STATUS
914	9	2025-03-31 19:07:10.398781	ACTIVITY_STATUS
915	9	2025-03-31 19:07:20.41264	ACTIVITY_STATUS
916	9	2025-03-31 19:07:30.405159	ACTIVITY_STATUS
917	9	2025-03-31 19:07:40.397765	ACTIVITY_STATUS
918	9	2025-03-31 19:07:50.404988	ACTIVITY_STATUS
919	9	2025-03-31 19:08:00.419837	ACTIVITY_STATUS
920	9	2025-03-31 19:08:10.403039	ACTIVITY_STATUS
921	9	2025-03-31 19:08:20.410539	ACTIVITY_STATUS
922	9	2025-03-31 19:08:30.399829	ACTIVITY_STATUS
942	10	2025-03-31 22:34:39.601956	GET_ALL_USERS
943	9	2025-03-31 22:52:47.295497	ACTIVITY_STATUS
944	9	2025-03-31 22:52:57.302358	ACTIVITY_STATUS
945	9	2025-03-31 22:53:07.299851	ACTIVITY_STATUS
946	10	2025-03-31 22:53:16.641783	GET_ACTIVE_USERS
947	9	2025-03-31 22:53:17.301542	ACTIVITY_STATUS
948	10	2025-03-31 22:53:21.729935	GET_ALL_USERS
949	9	2025-03-31 22:53:27.298035	ACTIVITY_STATUS
950	10	2025-03-31 22:53:31.645243	GET_MESSAGES_BY_USER_ID
951	9	2025-03-31 22:53:37.294997	ACTIVITY_STATUS
923	9	2025-03-31 19:08:40.413228	ACTIVITY_STATUS
924	9	2025-03-31 19:08:44.077442	MESSAGE
925	9	2025-03-31 19:08:47.095911	MESSAGE
926	9	2025-03-31 19:08:48.17241	MESSAGE
927	9	2025-03-31 19:08:50.408656	ACTIVITY_STATUS
928	9	2025-03-31 19:09:00.405961	ACTIVITY_STATUS
929	9	2025-03-31 19:09:10.410835	ACTIVITY_STATUS
930	9	2025-03-31 19:09:20.403293	ACTIVITY_STATUS
931	9	2025-03-31 19:09:30.405058	ACTIVITY_STATUS
880	6	2025-03-30 20:30:05.581623	SEND_STATUS
\.


--
-- TOC entry 3423 (class 0 OID 24621)
-- Dependencies: 218
-- Data for Name: messages; Type: TABLE DATA; Schema: public; Owner: owner
--

COPY public.messages (id, user_id, body, "time") FROM stdin;
1272	8	Activity	2025-03-31 13:05:55.72442
1273	8	Activity	2025-03-31 13:06:00.713599
1274	8	Activity	2025-03-31 13:06:05.724321
1275	8	Activity	2025-03-31 13:06:10.72527
1276	8	Activity	2025-03-31 13:06:15.722904
1277	8	Activity	2025-03-31 13:06:20.727495
1278	8	Message: Hello from Postman!	2025-03-31 13:06:23.596055
1279	8	Activity	2025-03-31 13:06:25.721251
1280	8	Activity	2025-03-31 13:06:30.724563
1281	8	Activity	2025-03-31 13:06:35.714267
1284	9	Activity	2025-03-31 19:07:10.398781
1286	9	Activity	2025-03-31 19:07:10.398781
1287	9	Activity	2025-03-31 19:07:10.398781
1288	9	Activity	2025-03-31 19:07:10.398781
1289	9	Activity	2025-03-31 19:07:10.398781
1290	9	Activity	2025-03-31 19:07:10.398781
1291	9	Activity	2025-03-31 19:07:10.398781
1292	9	Activity	2025-03-31 19:07:10.398781
1293	9	Activity	2025-03-31 19:07:10.398781
1294	9	Activity	2025-03-31 19:07:10.398781
1295	9	Activity	2025-03-31 19:07:10.398781
1296	9	Activity	2025-03-31 19:07:10.398781
1297	9	Activity	2025-03-31 19:07:10.398781
1298	9	Activity	2025-03-31 19:07:10.398781
1299	9	Activity	2025-03-31 19:07:10.398781
1300	9	Activity	2025-03-31 19:07:10.398781
1301	9	Activity	2025-03-31 19:07:10.398781
1302	9	Activity	2025-03-31 19:07:10.398781
1303	9	Activity	2025-03-31 19:07:10.398781
1304	9	Activity	2025-03-31 19:07:10.398781
1305	9	Activity	2025-03-31 19:07:10.398781
1306	9	Activity	2025-03-31 19:07:10.398781
1307	9	Activity	2025-03-31 19:07:10.398781
1308	9	Activity	2025-03-31 19:07:20.41264
1309	9	Activity	2025-03-31 19:07:10.398781
1310	9	Activity	2025-03-31 19:07:20.41264
1311	9	Activity	2025-03-31 19:07:10.398781
1312	9	Activity	2025-03-31 19:07:20.41264
1313	9	Activity	2025-03-31 19:07:10.398781
1314	9	Activity	2025-03-31 19:07:20.41264
1315	9	Activity	2025-03-31 19:07:30.405159
1316	9	Activity	2025-03-31 19:07:40.397765
1317	9	Activity	2025-03-31 19:07:50.404988
1318	9	Activity	2025-03-31 19:08:00.419837
1319	9	Activity	2025-03-31 19:08:10.403039
1320	9	Activity	2025-03-31 19:08:20.410539
1321	9	Activity	2025-03-31 19:08:30.399829
1322	9	Activity	2025-03-31 19:08:40.413228
1323	9	Message: Heeeellooo	2025-03-31 19:08:44.077442
1324	9	Message: Heeeellooo	2025-03-31 19:08:47.095911
1325	9	Message: Heeeellooo	2025-03-31 19:08:48.17241
1326	9	Activity	2025-03-31 19:08:50.408656
1327	9	Activity	2025-03-31 19:09:00.405961
1328	9	Activity	2025-03-31 19:09:10.410835
1329	9	Activity	2025-03-31 19:09:20.403293
1330	9	Activity	2025-03-31 19:09:30.405058
1331	9	Activity	2025-03-31 22:52:47.295497
1332	9	Activity	2025-03-31 22:52:57.302358
1333	9	Activity	2025-03-31 22:53:07.299851
1334	9	Activity	2025-03-31 22:53:17.301542
1335	9	Activity	2025-03-31 22:53:27.298035
1336	9	Activity	2025-03-31 22:53:37.294997
\.


--
-- TOC entry 3421 (class 0 OID 24612)
-- Dependencies: 216
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: owner
--

COPY public.users (id, uuid, role) FROM stdin;
3	29a6129e-d7f2-486b-9903-a5c047fc8253	USER
4	a9a956c8-7f80-4196-8693-fbfbf81cf21f	USER
5	9dc32b64-d382-40c7-872a-fc60b7c92b5f	USER
6	3ff6f33d-a011-4d99-bf20-bfd2118e7f8f	USER
7	604cf600-d8e8-4af6-bbd2-a07c9e41e5cb	USER
8	29c9e859-e474-4365-8a62-56d6d543019a	USER
9	ed71e798-2c3a-4e64-b475-84fcb687221c	USER
10	A777AA77	ADMIN
\.


--
-- TOC entry 3435 (class 0 OID 0)
-- Dependencies: 219
-- Name: activities_id_seq; Type: SEQUENCE SET; Schema: public; Owner: owner
--

SELECT pg_catalog.setval('public.activities_id_seq', 951, true);


--
-- TOC entry 3436 (class 0 OID 0)
-- Dependencies: 217
-- Name: messages_id_seq; Type: SEQUENCE SET; Schema: public; Owner: owner
--

SELECT pg_catalog.setval('public.messages_id_seq', 1336, true);


--
-- TOC entry 3437 (class 0 OID 0)
-- Dependencies: 215
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: owner
--

SELECT pg_catalog.setval('public.users_id_seq', 10, true);


--
-- TOC entry 3274 (class 2606 OID 24670)
-- Name: activities activities_pkey; Type: CONSTRAINT; Schema: public; Owner: owner
--

ALTER TABLE ONLY public.activities
    ADD CONSTRAINT activities_pkey PRIMARY KEY (id);


--
-- TOC entry 3272 (class 2606 OID 24629)
-- Name: messages messages_pkey; Type: CONSTRAINT; Schema: public; Owner: owner
--

ALTER TABLE ONLY public.messages
    ADD CONSTRAINT messages_pkey PRIMARY KEY (id);


--
-- TOC entry 3268 (class 2606 OID 24617)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: owner
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 3270 (class 2606 OID 24681)
-- Name: users users_uuid_key; Type: CONSTRAINT; Schema: public; Owner: owner
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_uuid_key UNIQUE (uuid);


--
-- TOC entry 3276 (class 2606 OID 24671)
-- Name: activities activities_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: owner
--

ALTER TABLE ONLY public.activities
    ADD CONSTRAINT activities_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE CASCADE;


--
-- TOC entry 3275 (class 2606 OID 24630)
-- Name: messages messages_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: owner
--

ALTER TABLE ONLY public.messages
    ADD CONSTRAINT messages_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE CASCADE;


-- Completed on 2025-04-01 02:27:11

--
-- PostgreSQL database dump complete
--

