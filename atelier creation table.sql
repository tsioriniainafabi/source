/*==============================================================*/
/* DBMS name:      PostgreSQL 8                                 */
/* Created on:     01/03/2017 14:22:59                          */
/*==============================================================*/


drop index ATELIER_PK;

drop table ATELIER;

drop index CATEG_MATIERE_PK;

drop table CATEG_MATIERE;

drop index ENTREE_AT_FK;

drop index MOUVNT_ENTREE_FK;

drop index ENTREE_PK;

drop table ENTREE;

drop index CATEG_MAT_FK;

drop index MATIERE_PK;

drop table MATIERE;

drop index MOUV_MAT_FK;

drop index MOUVEMENT_PK;

drop table MOUVEMENT;

drop index SORTIE_AT_FK;

drop index MOVNT_SORTIE_FK;

drop index SORTIE_PK;

drop table SORTIE;

drop index UTILISATEUR_PK;

drop table UTILISATEUR;

/*==============================================================*/
/* Table: ATELIER                                               */
/*==============================================================*/
create table ATELIER (
   ID_ATELIER           SERIAL               not null,
   DES_ATELIER          VARCHAR(25)          null,
   HMOD                 INT4                 null,
   UO                   MONEY                null,
   HM                   INT4                 null,
   FONCTION             VARCHAR(50)          null,
   constraint PK_ATELIER primary key (ID_ATELIER)
);

/*==============================================================*/
/* Index: ATELIER_PK                                            */
/*==============================================================*/
create unique index ATELIER_PK on ATELIER (
ID_ATELIER
);

/*==============================================================*/
/* Table: CATEG_MATIERE                                         */
/*==============================================================*/
create table CATEG_MATIERE (
   ID_CATEG_MAT         SERIAL               not null,
   NOM_CATEG            VARCHAR(25)          null,
   constraint PK_CATEG_MATIERE primary key (ID_CATEG_MAT)
);

/*==============================================================*/
/* Index: CATEG_MATIERE_PK                                      */
/*==============================================================*/
create unique index CATEG_MATIERE_PK on CATEG_MATIERE (
ID_CATEG_MAT
);

/*==============================================================*/
/* Table: ENTREE                                                */
/*==============================================================*/
create table ENTREE (
   ID_ENTREE            SERIAL               not null,
   ID_MOUVEMENT         INT4                 not null,
   ID_ATELIER           INT4                 null,
   QTE_ENTREE           INT4                 null,
   constraint PK_ENTREE primary key (ID_ENTREE)
);

/*==============================================================*/
/* Index: ENTREE_PK                                             */
/*==============================================================*/
create unique index ENTREE_PK on ENTREE (
ID_ENTREE
);

/*==============================================================*/
/* Index: MOUVNT_ENTREE_FK                                      */
/*==============================================================*/
create  index MOUVNT_ENTREE_FK on ENTREE (
ID_MOUVEMENT
);

/*==============================================================*/
/* Index: ENTREE_AT_FK                                          */
/*==============================================================*/
create  index ENTREE_AT_FK on ENTREE (
ID_ATELIER
);

/*==============================================================*/
/* Table: MATIERE                                               */
/*==============================================================*/
create table MATIERE (
   ID_MATIERE           SERIAL               not null,
   ID_CATEG_MAT         INT4                 not null,
   DES_MAT              VARCHAR(25)          null,
   UNITE                VARCHAR(5)           null,
   SEUIL                INT4                 null,
   PRIX_UNITAIRE        MONEY                null,
   constraint PK_MATIERE primary key (ID_MATIERE)
);

/*==============================================================*/
/* Index: MATIERE_PK                                            */
/*==============================================================*/
create unique index MATIERE_PK on MATIERE (
ID_MATIERE
);

/*==============================================================*/
/* Index: CATEG_MAT_FK                                          */
/*==============================================================*/
create  index CATEG_MAT_FK on MATIERE (
ID_CATEG_MAT
);

/*==============================================================*/
/* Table: MOUVEMENT                                             */
/*==============================================================*/
create table MOUVEMENT (
   ID_MOUVEMENT         SERIAL               not null,
   ID_MATIERE           INT4                 not null,
   DES_MVNT             VARCHAR(25)          null,
   DATE_MVNT            DATE                 null,
   constraint PK_MOUVEMENT primary key (ID_MOUVEMENT)
);

/*==============================================================*/
/* Index: MOUVEMENT_PK                                          */
/*==============================================================*/
create unique index MOUVEMENT_PK on MOUVEMENT (
ID_MOUVEMENT
);

/*==============================================================*/
/* Index: MOUV_MAT_FK                                           */
/*==============================================================*/
create  index MOUV_MAT_FK on MOUVEMENT (
ID_MATIERE
);

/*==============================================================*/
/* Table: SORTIE                                                */
/*==============================================================*/
create table SORTIE (
   ID_SORTIE            SERIAL               not null,
   ID_ATELIER           INT4                 null,
   ID_MOUVEMENT         INT4                 not null,
   QTE_SORTIE           INT4                 null,
   constraint PK_SORTIE primary key (ID_SORTIE)
);

/*==============================================================*/
/* Index: SORTIE_PK                                             */
/*==============================================================*/
create unique index SORTIE_PK on SORTIE (
ID_SORTIE
);

/*==============================================================*/
/* Index: MOVNT_SORTIE_FK                                       */
/*==============================================================*/
create  index MOVNT_SORTIE_FK on SORTIE (
ID_MOUVEMENT
);

/*==============================================================*/
/* Index: SORTIE_AT_FK                                          */
/*==============================================================*/
create  index SORTIE_AT_FK on SORTIE (
ID_ATELIER
);

/*==============================================================*/
/* Table: UTILISATEUR                                           */
/*==============================================================*/
create table UTILISATEUR (
   ID_UTILISATEUR       SERIAL               not null,
   NOM_UTILISATEUR      VARCHAR(10)          null,
   MDP                  VARCHAR(5)           null,
   constraint PK_UTILISATEUR primary key (ID_UTILISATEUR)
);

/*==============================================================*/
/* Index: UTILISATEUR_PK                                        */
/*==============================================================*/
create unique index UTILISATEUR_PK on UTILISATEUR (
ID_UTILISATEUR
);

alter table ENTREE
   add constraint FK_ENTREE_ENTREE_AT_ATELIER foreign key (ID_ATELIER)
      references ATELIER (ID_ATELIER)
      on delete restrict on update restrict;

alter table ENTREE
   add constraint FK_ENTREE_MOUVNT_EN_MOUVEMEN foreign key (ID_MOUVEMENT)
      references MOUVEMENT (ID_MOUVEMENT)
      on delete restrict on update restrict;

alter table MATIERE
   add constraint FK_MATIERE_CATEG_MAT_CATEG_MA foreign key (ID_CATEG_MAT)
      references CATEG_MATIERE (ID_CATEG_MAT)
      on delete restrict on update restrict;

alter table MOUVEMENT
   add constraint FK_MOUVEMEN_MOUV_MAT_MATIERE foreign key (ID_MATIERE)
      references MATIERE (ID_MATIERE)
      on delete restrict on update restrict;

alter table SORTIE
   add constraint FK_SORTIE_MOVNT_SOR_MOUVEMEN foreign key (ID_MOUVEMENT)
      references MOUVEMENT (ID_MOUVEMENT)
      on delete restrict on update restrict;

alter table SORTIE
   add constraint FK_SORTIE_SORTIE_AT_ATELIER foreign key (ID_ATELIER)
      references ATELIER (ID_ATELIER)
      on delete restrict on update restrict;

