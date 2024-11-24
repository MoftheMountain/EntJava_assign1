--h2
insert into rentals_autorental(id,auto_id,start_date,address_street)
values('id', 'autoId',CURRENT_DATE,'street');

update rentals_autorental
set start_date=CURRENT_DATE+7
where id='id';
