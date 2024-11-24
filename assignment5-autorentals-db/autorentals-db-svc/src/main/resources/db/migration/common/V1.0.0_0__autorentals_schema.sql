drop table IF EXISTS rentals_autorental;

create table rentals_autorental (
    id varchar(36) not null,
    auto_id varchar(36) not null,
    start_date date not null,

    address_street varchar(40)
--TODO
--    constraint autorental_pk primary key (id)
);

comment on table rentals_autorental is 'autorentals';
comment on column rentals_autorental.id is 'primary key';
comment on column rentals_autorental.auto_id is 'auto ID';
comment on column rentals_autorental.start_date is 'rental start inclusive';
comment on column rentals_autorental.address_street is 'auto street';
