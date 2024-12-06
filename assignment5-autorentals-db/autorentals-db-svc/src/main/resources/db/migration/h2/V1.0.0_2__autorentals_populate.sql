--h2
insert into rentals_autorental(
    id,
    auto_id,
    renter_id,
    start_date,
    end_date,
    amount,
    make_model,
    renter_name,
    renter_age,
    address_street, address_city, address_state, address_zip,
    username)
values('id','autoId','rental_id',CURRENT_DATE,CURRENT_DATE,0.00,'make_model','renter_name',21,'street','city','MD','12345','user');

update rentals_autorental
set start_date=CURRENT_DATE+7,end_date=CURRENT_DATE+10
where id='id';
