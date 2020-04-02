insert into country_city values('Vancouver', 'Canada');
insert into country_city values('Beijing', 'China');
insert into country_city values('London', 'United Kingdom');
insert into country_city values('Seoul', 'South Korea');
insert into country_city values('New York', 'United States');

insert into player values('100', 'sniperElite', 'oneHundred@gmail.com', 'Vancouver');
insert into player values('200', 'goodPlayer', 'twoHundred@hotmail.com', 'Beijing');
insert into player values('300', 'badPlayer', 'threeHundred@yahoo.com', 'London');
insert into player values('400', 'averagePlayer', 'fourHundred@outlook.com', 'Seoul');
insert into player values('500', 'gamerGirl', 'fiveHundred@mail.com', 'New York');

insert into player_group values('CSGOTeam', 5, 'CSGOAT');
insert into player_group values('GamerGods', 4, 'GG');
insert into player_group values('GroupOfPlayers', 3, 'GOP');
insert into player_group values('NAAim', 2, 'NA');
insert into player_group values('GirlsOnly', 2, 'GO');

insert into developer values('Valve');
insert into developer values('SEGA');
insert into developer values('From Software');
insert into developer values('Team Cherry');
insert into developer values('Team Cherry Sounds');
insert into developer values('Colossal Order Ltd.');
insert into developer values('RockStar');
insert into developer values('Bethesda Game Studios');
insert into developer values('Red Hook Studios');
insert into developer values('Studio MDHR');
insert into developer values('CD PROJEKT RED');

insert into develop_product values('000001', 'Valve', '2004-11-16', 'Half Life 2', 20, 'Half Life 2');
insert into develop_product values('000002', 'Team Cherry Sounds', '2017-02-24', 'Hollow Knight: Official Soundtrack', 11, 'Hollow Knight');
insert into develop_product values('000003', 'Valve', '2005-01-13', 'Half Life 2: Lost Coast', 11, 'Half Life 2');
insert into develop_product values('000004', 'SEGA', '2017-12-12', 'Yakuza 0', 30, 'Yakuza 0');
insert into develop_product values('000005', 'Team Cherry', '2017-02-24', 'Hollow Knight', 30, 'Hollow Knight');
insert into develop_product values('000006', 'Colossal Order Ltd.', '2015-03-10', 'Cities Skylines', 32, 'Cities Skylines');
insert into develop_product values('000009', 'SEGA', '2019-05-21', 'Team Sonic Racing', 55, 'Team Sonic Racing');
insert into develop_product values('000011', 'Red Hook Studios', '2016-01-19', 'Darkest Dungeon', 28, 'Darkest Dungeon');
insert into develop_product values('000012', 'Red Hook Studios', '2015-02-03', 'Darkest Dungeon Soundtrack', 10, 'Darkest Dungeon');
insert into develop_product values('000021', 'Bethesda Game Studios', '2016-05-12', 'DOOM', 25, 'DOOM');
insert into develop_product values('000022', 'Bethesda Game Studios', '2019-05-21', 'DOOM Soundtrack', 13, 'DOOM');
insert into develop_product values('000031', 'Studio MDHR', '2017-09-29', 'Cuphead', 22, 'Cuphead');
insert into develop_product values('000032', 'Studio MDHR', '2017-09-29', 'Cuphead - Official Soundtrack', 11, 'Cuphead');
insert into develop_product values('000041', 'CD PROJEKT RED', '2019-05-21', 'The Witcher 3: Wild Hunt', 55, 'The Witcher 3: Wild Hunt');
insert into develop_product values('000042', 'CD PROJEKT RED', '2020-02-10', 'The Witcher 3: Wild Hunt Soundtrack', 13, 'The Witcher 3: Wild Hunt');

insert into in_group values('CSGOTeam', 100);
insert into in_group values('CSGOTeam', 200);
insert into in_group values('CSGOTeam', 300);
insert into in_group values('CSGOTeam', 400);
insert into in_group values('CSGOTeam', 500);
insert into in_group values('GamerGods', 300);
insert into in_group values('GamerGods', 200);
insert into in_group values('GamerGods', 100);
insert into in_group values('GamerGods', 500);
insert into in_group values('GroupOfPlayers', 200);
insert into in_group values('GroupOfPlayers', 100);
insert into in_group values('GroupOfPlayers', 500);
insert into in_group values('GroupOfPlayers', 400);
insert into in_group values('NAAim', 100);
insert into in_group values('NAAim', 200);
insert into in_group values('GirlsOnly', 500);
insert into in_group values('GirlsOnly', 100);

insert into type_tradability values('CSGO Knife', 1);
insert into type_tradability values('Lootbox', 0);
insert into type_tradability values('Card', 1);
insert into type_tradability values('CSGO Skin', 1);
insert into type_tradability values('Foil', 1);

insert into own_item values('1005', '100', 'CSGO Knife');
insert into own_item values('1008', '100', 'Lootbox');
insert into own_item values('1002', '200', 'Card');
insert into own_item values('1000', '300', 'Lootbox');
insert into own_item values('1001', '500', 'CSGO Skin');
insert into own_item values('1007', '500', 'Foil');
insert into own_item values('1003', '200', 'Card');

insert into trade values('300', '100', '1005', '2015-01-21');
insert into trade values('200', '500', '1007', '2012-07-31');
insert into trade values('400', '200', '1002', '2019-11-23');
insert into trade values('500', '200', '1003', '2020-03-27');
insert into trade values('100', '500', '1001', '2009-12-08');

insert into purchase values('100', '000001', '2019-12-25');
insert into purchase values('100', '000003', '2019-12-26');
insert into purchase values('200', '000006', '2018-12-29');
insert into purchase values('300', '000002', '2017-06-12');
insert into purchase values('300', '000005', '2018-01-01');
insert into purchase values('400', '000002', '2017-06-21');
insert into purchase values('500', '000004', '2019-06-30');
insert into purchase values('300', '000009', '2020-01-02');

insert into game values('000001', 'Action');
insert into game values('000004', 'Action');
insert into game values('000005', 'Platformer');
insert into game values('000006', 'City Builder');
insert into game values('000009', 'Racing');
insert into game values('000011', 'Turn-based RPG');
insert into game values('000021', 'FPS');
insert into game values('000031', 'Platformer');
insert into game values('000041', 'Adventure');

insert into soundtrack values('000002', 48);
insert into soundtrack values('000012', 120);
insert into soundtrack values('000022', 231);
insert into soundtrack values('000032', 70);
insert into soundtrack values('000042', 94);

insert into create_for_mod values('000001', '1', '100');
insert into create_for_mod values('000001', '2', '100');
insert into create_for_mod values('000005', '1', '300');
insert into create_for_mod values('000006', '3', '200');
insert into create_for_mod values('000009', '5', '300');

insert into has_achievement values('001', '000001');
insert into has_achievement values('011', '000004');
insert into has_achievement values('021', '000001');
insert into has_achievement values('035', '000005');
insert into has_achievement values('010', '000006');
insert into has_achievement values('002', '000009');

insert into attain values('100', '001');
insert into attain values('500', '011');
insert into attain values('100', '021');
insert into attain values('300', '035');
insert into attain values('200', '010');
insert into attain values('300', '002');

insert into review_writereview values('900001', '2019-12-26', 1, '100', '000001');
insert into review_writereview values('900002', '2019-12-27', 1, '100', '000003');
insert into review_writereview values('900005', '2017-06-15', 0, '300', '000002');
insert into review_writereview values('900007', '2018-01-04', 0, '300', '000005');
insert into review_writereview values('900009', '2019-08-21', 1, '500', '000004');