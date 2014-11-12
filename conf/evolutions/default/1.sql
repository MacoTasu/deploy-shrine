# --- Created by Slick DDL
# To stop Slick DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table "revelation" ("id" SERIAL NOT NULL PRIMARY KEY,"rokuyou" SMALLINT NOT NULL,"name" VARCHAR(254) NOT NULL,"explanation" VARCHAR(254) NOT NULL);

# --- !Downs

drop table "revelation";

