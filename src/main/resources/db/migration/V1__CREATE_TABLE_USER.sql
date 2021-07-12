CREATE TABLE person (
	id BIGINT NOT NULL constraint person_pkey primary key,
	firstname varchar( 255 ) not null,
	lastname varchar( 255 ) not null,
	email varchar( 255 ) not null,
	password varchar( 255 ) not null,
	created_at timestamp without time zone not null,
	updated_at timestamp without time zone
);
create sequence PERSON_SEQUENCE INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
