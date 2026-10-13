CREATE SEQUENCE seq_tbl_consumo_energia
  START WITH 1
  INCREMENT BY 1
  NOCACHE
  NOCYCLE;

CREATE TABLE tbl_consumo_energia (
    id NUMBER NOT NULL,
    consumo_kwh NUMBER(10, 2) NOT NULL,
    data_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    id_equipamento NUMBER,
    CONSTRAINT pk_tbl_consumo_energia PRIMARY KEY (id),
    CONSTRAINT fk_tbl_consumo_energia_equipamento FOREIGN KEY (id_equipamento) REFERENCES tbl_equipamento(id)
);
