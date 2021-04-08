create table waiter
(
    id serial not null
        constraint waiter_pk
            primary key,
    first_name varchar,
    last_name varchar
);

alter table waiter owner to postgres;

create table "table"
(
    id serial not null
        constraint table_pk
            primary key,
    name varchar
);

alter table "table" owner to postgres;

create table dish
(
    id serial not null
        constraint dish_pk
            primary key,
    name varchar,
    price double precision
);

alter table dish owner to postgres;

create table bill
(
    id serial not null
        constraint bill_pk
            primary key,
    date timestamp,
    waiter_idx integer
        constraint waiter_fk
            references waiter,
    table_idx integer
        constraint table_fk
            references "table"
);

alter table bill owner to postgres;

create table bill_item
(
    bill_idx integer not null
        constraint bill_fk
            references bill,
    dish_idx integer not null
        constraint dish_idx
            references dish,
    quantity integer,
    constraint bill_item_pk
        primary key (bill_idx, dish_idx)
);

alter table bill_item owner to postgres;

