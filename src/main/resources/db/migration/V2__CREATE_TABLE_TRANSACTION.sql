CREATE TABLE transaction (
    id BIGINT NOT NULL constraint transaction_pkey primary key,
    person_id BIGINT NOT NULL,
    source_currency VARCHAR(3) NOT NULL,
    target_currency VARCHAR(3) NOT NULL,
    source_value DECIMAL NOT NULL,
    conversion_rate DECIMAL NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP not null,
    CONSTRAINT fk_person_transaction FOREIGN KEY (person_id)
              REFERENCES person(id) MATCH SIMPLE
              ON UPDATE NO ACTION ON DELETE NO ACTION
);
CREATE SEQUENCE TRANSACTION_SEQUENCE INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
