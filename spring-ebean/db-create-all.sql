create table accounts (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  account_number                varchar(255),
  constraint pk_accounts primary key (id)
);

