CREATE SEQUENCE seq_tbl_setor
  START WITH 1
  INCREMENT BY 1
  NOCACHE
  NOCYCLE;

CREATE TABLE tbl_setor (
    id NUMBER NOT NULL,
    nome VARCHAR2(100) NOT NULL,
    id_empresa NUMBER,
    CONSTRAINT pk_tbl_setor PRIMARY KEY (id),
    CONSTRAINT fk_tbl_setor_empresa FOREIGN KEY (id_empresa) REFERENCES tbl_empresa(id)
);
