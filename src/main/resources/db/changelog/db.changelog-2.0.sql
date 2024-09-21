--liquibase formatted sql

--changeset aidyninho:1
--
-- Data for Name: city; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.city (name, id) VALUES ('Birmingham', 2);
INSERT INTO public.city (name, id) VALUES ('Northhampton', 4);
INSERT INTO public.city (name, id) VALUES ('Coventry', 5);
INSERT INTO public.city (name, id) VALUES ('Swindon', 6);
INSERT INTO public.city (name, id) VALUES ('Reading', 7);
INSERT INTO public.city (name, id) VALUES ('London', 1);
INSERT INTO public.city (name, id) VALUES ('Bristol', 3);


--
-- Data for Name: route; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.route (source_city_id, destination_city_id, segments, id) VALUES (1, 7, 1, 36);
INSERT INTO public.route (source_city_id, destination_city_id, segments, id) VALUES (1, 4, 2, 37);
INSERT INTO public.route (source_city_id, destination_city_id, segments, id) VALUES (7, 6, 4, 38);
INSERT INTO public.route (source_city_id, destination_city_id, segments, id) VALUES (6, 3, 2, 39);
INSERT INTO public.route (source_city_id, destination_city_id, segments, id) VALUES (6, 2, 4, 40);
INSERT INTO public.route (source_city_id, destination_city_id, segments, id) VALUES (3, 2, 3, 41);
INSERT INTO public.route (source_city_id, destination_city_id, segments, id) VALUES (2, 5, 1, 42);
INSERT INTO public.route (source_city_id, destination_city_id, segments, id) VALUES (5, 4, 2, 43);


--
-- Data for Name: ticket; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.users (id, username, password, role, balance) VALUES (7, 'aidyninho', '$2a$10$fcBTNPwEDDJRYAI7PbcYHODh6/R7HNfbFwQ9Z8arXd6qTWqceZk5C', 'TRAVELLER', 123);

