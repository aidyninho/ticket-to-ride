--liquibase formatted sql

--changeset aidyninho:1

--
-- Name: city; Type: TABLE; Schema: public;
--

CREATE TABLE IF NOT EXISTS public.city (
                             name character varying(128) NOT NULL,
                             id serial NOT NULL
);

--
-- Name: city_id_seq; Type: SEQUENCE; Schema: public;
--

CREATE SEQUENCE IF NOT EXISTS public.city_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: city_id_seq; Type: SEQUENCE OWNED BY; Schema: public;
--

ALTER SEQUENCE public.city_id_seq OWNED BY public.city.id;

--
-- Name: route; Type: TABLE; Schema: public;
--

CREATE TABLE IF NOT EXISTS public.route (
                              source_city_id integer NOT NULL,
                              destination_city_id integer NOT NULL,
                              segments integer,
                              id serial NOT NULL
);

--
-- Name: route_id_seq; Type: SEQUENCE; Schema: public;
--

CREATE SEQUENCE IF NOT EXISTS public.route_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: route_id_seq; Type: SEQUENCE OWNED BY; Schema: public;
--

ALTER SEQUENCE public.route_id_seq OWNED BY public.route.id;

--
-- Name: ticket; Type: TABLE; Schema: public;
--

CREATE TABLE IF NOT EXISTS public.ticket (
                               id serial NOT NULL,
                               source_city_id integer NOT NULL,
                               destination_city_id integer NOT NULL,
                               segments integer NOT NULL,
                               price double precision NOT NULL,
                               currency character varying(128) NOT NULL,
                               user_id integer NOT NULL
);

--
-- Name: ticket_id_seq; Type: SEQUENCE; Schema: public;
--

CREATE SEQUENCE IF NOT EXISTS public.ticket_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: ticket_id_seq; Type: SEQUENCE OWNED BY; Schema: public;
--

ALTER SEQUENCE public.ticket_id_seq OWNED BY public.ticket.id;


--
-- Name: users; Type: TABLE; Schema: public;
--

CREATE TABLE IF NOT EXISTS public.users (
                              id serial NOT NULL,
                              username character varying(128) NOT NULL,
                              password character varying(128) NOT NULL,
                              role character varying(128),
                              balance integer
);

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public;
--

CREATE SEQUENCE IF NOT EXISTS public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public;
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;

--
-- Name: city city_pk; Type: CONSTRAINT; Schema: public;
--

ALTER TABLE ONLY public.city
    ADD CONSTRAINT city_pk PRIMARY KEY (id);


--
-- Name: city city_pk2; Type: CONSTRAINT; Schema: public;
--

ALTER TABLE ONLY public.city
    ADD CONSTRAINT city_pk2 UNIQUE (name);


--
-- Name: route route_pk; Type: CONSTRAINT; Schema: public;
--

ALTER TABLE ONLY public.route
    ADD CONSTRAINT route_pk PRIMARY KEY (id);


--
-- Name: ticket ticket_pk; Type: CONSTRAINT; Schema: public;
--

ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT ticket_pk PRIMARY KEY (id);


--
-- Name: users users_pk; Type: CONSTRAINT; Schema: public;
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pk PRIMARY KEY (id);


--
-- Name: users users_pk2; Type: CONSTRAINT; Schema: public;
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pk2 UNIQUE (username);


--
-- Name: route route_city_id_fk; Type: FK CONSTRAINT; Schema: public;
--

ALTER TABLE ONLY public.route
    ADD CONSTRAINT route_city_id_fk FOREIGN KEY (source_city_id) REFERENCES public.city(id);


--
-- Name: route route_city_id_fk2; Type: FK CONSTRAINT; Schema: public;
--

ALTER TABLE ONLY public.route
    ADD CONSTRAINT route_city_id_fk2 FOREIGN KEY (destination_city_id) REFERENCES public.city(id);


--
-- Name: ticket ticket_city_id_fk; Type: FK CONSTRAINT; Schema: public;
--

ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT ticket_city_id_fk FOREIGN KEY (source_city_id) REFERENCES public.city(id);


--
-- Name: ticket ticket_city_id_fk2; Type: FK CONSTRAINT; Schema: public;
--

ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT ticket_city_id_fk2 FOREIGN KEY (destination_city_id) REFERENCES public.city(id);


--
-- Name: ticket ticket_users_id_fk; Type: FK CONSTRAINT; Schema: public;
--

ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT ticket_users_id_fk FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- PostgreSQL database dump complete
--

