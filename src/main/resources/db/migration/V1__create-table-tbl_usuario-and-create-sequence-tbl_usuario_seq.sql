CREATE SEQUENCE seq_tbl_usuario
  START WITH 1
  INCREMENT BY 1
  NOCACHE
  NOCYCLE;

CREATE TABLE tbl_usuario(
    id NUMBER NOT NULL,
    nome VARCHAR2(100) NOT NULL,
    email VARCHAR2(100) NOT NULL,
    senha VARCHAR2(100) NOT NULL,
    role VARCHAR2(20) DEFAULT 'USER' NOT NULL,
    id_empresa NUMBER,
    CONSTRAINT pk_tbl_usuario PRIMARY KEY (id),
    CONSTRAINT uk_tbl_usuario_email UNIQUE (email)
);