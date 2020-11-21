CREATE DATABASE capestart;

DROP SEQUENCE IF EXISTS public.user_id_seq;
CREATE SEQUENCE public.user_id_seq;

DROP TABLE IF EXISTS public.user CASCADE;
CREATE TABLE public.user
(
    user_id bigint NOT NULL DEFAULT nextval('public.user_id_seq'),
    user_name character varying(250) NOT NULL,
    user_email character varying(250) NOT NULL,
    user_type  smallint NOT NULL,
    is_active smallint NOT NULL DEFAULT 0,
    created_date timestamp without time zone NOT NULL,
    CONSTRAINT user_id_pkey PRIMARY KEY (user_id)
);

INSERT INTO public."user"(user_name, user_type, created_date) VALUES ('admin@gmail.com', -1, now());

--Book Table
DROP SEQUENCE IF EXISTS public.book_id_seq;
CREATE SEQUENCE public.book_id_seq;

DROP TABLE IF EXISTS public.book CASCADE;
CREATE TABLE public.book
(
    book_id bigint NOT NULL DEFAULT nextval('public.book_id_seq'),
    book_name character varying(250),
    book_category character varying(250),
    book_image oid ,
    publisher_id bigint NOT NULL ,
    author_id bigint NOT NULL ,
    is_delete smallint NOT NULL DEFAULT 0,
    CONSTRAINT book_id_pkey PRIMARY KEY (book_id) ,
    CONSTRAINT fkey_book_author FOREIGN KEY (author_id)
    REFERENCES public.author (author_id) ,
    CONSTRAINT fkey_book_publisher FOREIGN KEY (publisher_id)
    REFERENCES public.publisher (publisher_id)
);

--Author Table
DROP SEQUENCE IF EXISTS public.author_id_seq;
CREATE SEQUENCE public.author_id_seq;

DROP TABLE IF EXISTS public.author CASCADE;
CREATE TABLE public.author
(
    author_id bigint NOT NULL DEFAULT nextval('public.author_id_seq'),
    author_name character varying(250),
    CONSTRAINT author_id_pkey PRIMARY KEY (author_id)
);

-- Publisher Table
DROP SEQUENCE IF EXISTS public.publisher_id_seq;
CREATE SEQUENCE public.publisher_id_seq;

DROP TABLE IF EXISTS public.publisher CASCADE;
CREATE TABLE public.publisher
(
    publisher_id bigint NOT NULL DEFAULT nextval('public.publisher_id_seq'),
    publisher_name character varying(250),
    CONSTRAINT publisher_id_pkey PRIMARY KEY (publisher_id)
);

