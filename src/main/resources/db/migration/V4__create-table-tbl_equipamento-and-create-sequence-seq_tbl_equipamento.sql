CREATE SEQUENCE seq_tbl_equipamento
  START WITH 1
  INCREMENT BY 1
  NOCACHE
  NOCYCLE;

CREATE TABLE tbl_equipamento (
    id NUMBER NOT NULL,
    nome VARCHAR2(100) NOT NULL,
    tipo VARCHAR2(100) NOT NULL,
    status VARCHAR2(50) NOT NULL,
    id_setor NUMBER,
    CONSTRAINT pk_tbl_equipamento PRIMARY KEY (id),
    CONSTRAINT fk_tbl_equipamento_setor FOREIGN KEY (id_setor) REFERENCES tbl_setor(id)
);
