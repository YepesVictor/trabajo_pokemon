DROP TABLE POKEMON;
DROP TABLE GIMNASIO;
DROP TABLE ENTRENADOR;
DROP TABLE REGION;

CREATE TABLE POKEMON (
  ID NUMBER(10) generated by default as identity(start with 1 increment by 1), 
  NOMBRE VARCHAR2(50) NOT NULL,
  TIPO1 VARCHAR2(20) NOT NULL,
  TIPO2 VARCHAR2(20) ,
  ID_REGION NUMBER(10) NOT NULL, 
  CONSTRAINT POKEMON_PK PRIMARY KEY(ID) 
);

CREATE TABLE REGION 
(
  ID NUMBER(10) generated by default as identity(start with 1 increment by 1),
  NOMBRE VARCHAR2(50) NOT NULL,
  CONSTRAINT REGION_PK PRIMARY KEY (ID) 
);

alter table pokemon ADD CONSTRAINT POKEMON_FK1 FOREIGN KEY(ID_REGION)REFERENCES REGION(ID) on delete cascade;

create table gimnasio
(
    ID NUMBER(10) GENERATED BY DEFAULT AS IDENTITY(START WITH 1 INCREMENT BY 1),
    LIDER NUMBER(10) NOT NULL,
    ID_REGION NUMBER(10) NOT NULL,
    CONSTRAINT GIMNASIO_PK PRIMARY KEY(ID),
    CONSTRAINT GIMNASIO_FK_REGION FOREIGN KEY(ID_REGION) REFERENCES REGION (ID)on delete cascade
);

CREATE TABLE ENTRENADOR
(
    ID NUMBER(10) GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1),
    NOMBRE VARCHAR2(50) NOT NULL,
    ID_REGION NUMBER(10),
    ES_LIDER NUMBER(1) DEFAULT 0 CHECK (ES_LIDER IN (0, 1)),
    CONSTRAINT ENTRENADOR_PK PRIMARY KEY (ID),
    CONSTRAINT ENTRENADOR_FK_REGION FOREIGN KEY(ID_REGION) REFERENCES REGION (ID)on delete cascade
);

ALTER TABLE GIMNASIO ADD CONSTRAINT GIMNASIO_FK_LIDER FOREIGN KEY(LIDER) REFERENCES ENTRENADOR(ID) on delete cascade;

INSERT INTO REGION (NOMBRE) VALUES ('Kanto');
INSERT INTO REGION (NOMBRE) VALUES ('Johto');
INSERT INTO REGION (NOMBRE) VALUES ('Hoenn');
INSERT INTO REGION (NOMBRE) VALUES ('Sinnoh');
INSERT INTO REGION (NOMBRE) VALUES ('Unova');
INSERT INTO REGION (NOMBRE) VALUES ('Kalos');

INSERT INTO ENTRENADOR (NOMBRE, ID_REGION, ES_LIDER) VALUES ('Ash', 1, 0);
INSERT INTO ENTRENADOR (NOMBRE, ID_REGION, ES_LIDER) VALUES ('Brock', 1, 1);
INSERT INTO ENTRENADOR (NOMBRE, ID_REGION, ES_LIDER) VALUES ('Misty', 1, 1);
INSERT INTO ENTRENADOR (NOMBRE, ID_REGION, ES_LIDER) VALUES ('Whitney', 2,1);
INSERT INTO ENTRENADOR (NOMBRE, ID_REGION, ES_LIDER) VALUES ('Norman', 3, 1);
INSERT INTO ENTRENADOR (NOMBRE, ID_REGION, ES_LIDER) VALUES ('Cynthia', 4, 1);

INSERT INTO GIMNASIO (LIDER, ID_REGION) VALUES (2, 1); 
INSERT INTO GIMNASIO (LIDER, ID_REGION) VALUES (3, 1); 
INSERT INTO GIMNASIO (LIDER, ID_REGION) VALUES (4, 2); 
INSERT INTO GIMNASIO (LIDER, ID_REGION) VALUES (5, 3); 
INSERT INTO GIMNASIO (LIDER, ID_REGION) VALUES (6, 4); 
INSERT INTO GIMNASIO (LIDER, ID_REGION) VALUES (5, 3); 

INSERT INTO POKEMON (NOMBRE, TIPO1, TIPO2, ID_REGION) VALUES ('Pikachu', 'Eléctrico', NULL, 1);
INSERT INTO POKEMON (NOMBRE, TIPO1, TIPO2, ID_REGION) VALUES ('Charizard', 'Fuego', 'Volador', 1);
INSERT INTO POKEMON (NOMBRE, TIPO1, TIPO2, ID_REGION) VALUES ('Bulbasaur', 'Planta', 'Veneno', 1);
INSERT INTO POKEMON (NOMBRE, TIPO1, TIPO2, ID_REGION) VALUES ('Totodile', 'Agua', NULL, 2);
INSERT INTO POKEMON (NOMBRE, TIPO1, TIPO2, ID_REGION) VALUES ('Torchic', 'Fuego', NULL, 3);
INSERT INTO POKEMON (NOMBRE, TIPO1, TIPO2, ID_REGION) VALUES ('Garchomp', 'Dragón', 'Tierra', 4);

CREATE OR REPLACE PROCEDURE actualizaGimnasio (
idGim IN NUMBER, idLid IN NUMBER, idRegion IN NUMBER) AS 
BEGIN 
UPDATE gimnasio 
SET lider = idLid, id_region = idRegion 
WHERE id = idGim; 
END;
