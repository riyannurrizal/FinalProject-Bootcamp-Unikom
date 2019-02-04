create table merk_category (
  id          varchar(64)  not null primary key,
  name_merk        varchar(150) not null,
  description text
) engine = InnoDB;
