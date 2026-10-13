CREATE SEQUENCE seq_tbl_alerta
  START WITH 1
  INCREMENT BY 1
  NOCACHE
  NOCYCLE;

CREATE TABLE tbl_alerta (
    id NUMBER NOT NULL,
    mensagem VARCHAR2(255) NOT NULL,
    data_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    id_setor NUMBER,
    CONSTRAINT pk_tbl_alerta PRIMARY KEY (id),
    CONSTRAINT fk_tbl_alerta_setor FOREIGN KEY (id_setor) REFERENCES tbl_setor(id)
);