create table color_category (
  id          varchar(64)  not null primary key,
  name        varchar(150) not null,
  code        varchar(64) not null,
  description text
) engine = InnoDB;
