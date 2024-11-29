drop table IF EXISTS rentals_autorental;

create table rentals_autorental (
    id varchar(36) not null,
    auto_id varchar(36) not null,
    renter_id varchar(36) not null,
    start_date date not null,
    end_date date not null,
    amount decimal(65,2) unsigned not null,
    make_model varchar(36) not null,
    renter_name varchar(50) not null,
    renter_age int(3) unsigned not null,
    address_street varchar(40),
    username varchar(25),

    constraint autorental_pk primary key (id)
);

comment on table rentals_autorental is 'autorentals';
comment on column rentals_autorental.id is 'primary key';
comment on column rentals_autorental.auto_id is 'auto ID';
comment on column rentals_autorental.renter_id is 'renter ID';
comment on column rentals_autorental.start_date is 'rental start inclusive';
comment on column rentals_autorental.end_date is 'rental end inclusive';
comment on column rentals_autorental.amount is 'cost of rental';
comment on column rentals_autorental.make_model is 'make and model of auto';
comment on column rentals_autorental.renter_name is 'first and last renter name';
comment on column rentals_autorental. renter_age is 'renter age at time of rental';
comment on column rentals_autorental.address_street is 'auto street';
comment on column rentals_autorental.username is 'username';
