--postgres
insert into rentals_autorental(id,auto_id,renter_id, start_date,end_date,amount, make_model,renter_name, renter_age,address_street)
values('id', 'autoId','rental_id',CURRENT_DATE,CURRENT_DATE,0,'make_model','renter_name',0,'street');

update rentals_autorental
set start_date=CURRENT_DATE+7,end_date=CURRENT_DATE+10
where id='id';