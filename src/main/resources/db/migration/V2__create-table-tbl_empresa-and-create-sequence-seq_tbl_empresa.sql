CREATE SEQUENCE seq_tbl_empresa
  START WITH 1
  INCREMENT BY 1
  NOCACHE
  NOCYCLE;

CREATE TABLE tbl_empresa (
    id NUMBER NOT NULL,
    name VARCHAR2(100) NOT NULL,
    cnpj VARCHAR2(20) NOT NULL,
    CONSTRAINT pk_tbl_empresa PRIMARY KEY (id),
    CONSTRAINT uk_tbl_empresa_cnpj UNIQUE (cnpj)
);
