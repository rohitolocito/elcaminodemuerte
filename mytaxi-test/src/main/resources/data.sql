/**
 * CREATE Script for init of DB
 */

-- Create 3 OFFLINE drivers

insert into driver (id, date_created, deleted, online_status, password, username) values (1, now(), false, 'OFFLINE',
'$2a$10$ZURC5U8mLk3shySLJJlDcuK8L7shIz71bWH3Ka7Fnbt5HoRIyLh2K', 'driver01');

insert into driver (id, date_created, deleted, online_status, password, username) values (2, now(), false, 'OFFLINE',
'$2a$10$vF2tBQL.0sDFLQDME5IZNuAJ0BRF1EuzwD515qVoKjZhUqCE8UwXS', 'driver02');

insert into driver (id, date_created, deleted, online_status, password, username) values (3, now(), false, 'OFFLINE',
'$2a$10$pPEsGKJSnmTvuBxREWDw1.z/OWuwrUqEFPm82THI6ygZTvMCwSz1a', 'driver03');


-- Create 3 ONLINE drivers

insert into driver (id, date_created, deleted, online_status, password, username) values (4, now(), false, 'ONLINE',
'$2a$10$cEooeRJ5jWQb/k3QSHxp0eeT0V2EfxmAh.zM0JfKPM3kJ.iZxQBUC', 'driver04');

insert into driver (id, date_created, deleted, online_status, password, username) values (5, now(), false, 'ONLINE',
'$2a$10$lwIhrIa/M3pMKBOQKxQBn.xP1uSkjoUDimiCb0DRchsmVeqMWGe.q', 'driver05');

insert into driver (id, date_created, deleted, online_status, password, username) values (6, now(), false, 'ONLINE',
'$2a$10$kDZ9XQBz/Oicf3WwuIQjO.kQZ7c7rUfm4XsxkdQCXhCVTMzTKjQoC', 'driver06');

-- Create 1 OFFLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username)
values
 (7,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(), now(), false, 'OFFLINE',
'$2a$10$fBJDRPxAj9fiojGGhZBfLOYKDdlTzlAC8LtWiLN4FF6YrdmryZTjG', 'driver07');

-- Create 1 ONLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username)
values
 (8,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(), now(), false, 'ONLINE',
'$2a$10$b6cnXn4tVZ94.xyEbwDJwuFx3Tcrdy19axnCvLPnsoYUma7LzsIrq', 'driver08');

-- Create CarManufacturers for MyTaxi

insert into carmanufacturer (id, date_created, engine_type, manufacturer, max_passengers_allowed, model, type)
values
  (1, now(), 'GAS', 'Acura', 4, 'TSX', 'LUXURY');

insert into carmanufacturer (id, date_created, engine_type, manufacturer, max_passengers_allowed, model, type)
values
  (2, now(), 'GAS', 'Abarth', 4, 'Grande Punto', 'SUV');

insert into carmanufacturer (id, date_created, engine_type, manufacturer, max_passengers_allowed, model, type)
values
  (3, now(), 'GAS', 'Aston Martin', 4, 'Virage', 'COUPE');

insert into carmanufacturer (id, date_created, engine_type, manufacturer, max_passengers_allowed, model, type)
values
  (4, now(), 'GAS', 'Acura', 5, 'TL', 'SUV');

insert into carmanufacturer (id, date_created, engine_type, manufacturer, max_passengers_allowed, model, type)
values
  (5, now(), 'ELECTRIC', 'Tesla', 4, 'Model S', 'ELECTRIC');

insert into carmanufacturer (id, date_created, engine_type, manufacturer, max_passengers_allowed, model, type)
values
  (6, now(), 'HYBRID', 'Toyota', 4, 'Prius', 'HYBRID');

insert into carmanufacturer (id, date_created, engine_type, manufacturer, max_passengers_allowed, model, type)
values
  (7, now(), 'GAS', 'Ferrari', 4, 'LaFerrari', 'COUPE');

insert into carmanufacturer (id, date_created, engine_type, manufacturer, max_passengers_allowed, model, type)
values
  (8, now(), 'GAS', 'Ferrari', 4, 'Testarossa', 'COUPE');
