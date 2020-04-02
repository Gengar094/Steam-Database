create table country_city
(
	city varchar(20) primary key,
	country varchar(20)
);

grant select on country_city to public;

create table player
(
	player_id varchar(10) primary key,
	pname varchar(20),
	email varchar(40),
	city varchar(20) not null,
	foreign key (city) references country_city ON DELETE SET NULL
);

grant select on player to public;

create table player_group
(
	gname varchar(20) primary key,
	num_mem int,
	tag varchar(20)
);

grant select on player_group to public;

create table developer
(
	dv_name varchar(30) primary key
);

grant select on developer to public;

create table develop_product
(
	app_id varchar(20) primary key,
	dv_name varchar(30) not null,
	ddate char(20),
	product_name varchar(50),
	price int,
	base_game_name varchar(40),
	foreign key (dv_name) references developer ON DELETE CASCADE
);

grant select on develop_product to public;

create table in_group
(
	gname varchar(20),
	player_id varchar(10),
	primary key (gname, player_id),
	foreign key (gname) references player_group ON DELETE SET NULL,
	foreign key (player_id) references player ON DELETE SET NULL
);

grant select on in_group to public;

create table type_tradability
(
	item_type varchar(20) primary key,
	tradability number(1,0)
);

grant select on type_tradability to public;

create table own_item
(
	item_id varchar(10) primary key,
	player_id varchar(10),
	item_type varchar(20),
	foreign key (player_id) references player ON DELETE SET NULL,
	foreign key (item_type) references type_tradability ON DELETE SET NULL
);

grant select on own_item to public;

create table trade
(
	giver_id varchar(10),
	receiver_id varchar(10),
	item_id varchar(10),
	tdate char(20),
	primary key (giver_id, receiver_id, item_id),
	foreign key (giver_id) references player (player_id) ON DELETE SET NULL,
	foreign key (receiver_id) references player (player_id) ON DELETE SET NULL,
	foreign key (item_id) references own_item ON DELETE CASCADE
);

grant select on trade to public;

create table purchase
(
	player_id varchar(10),
	app_id varchar(10),
	pdate char(20),
	primary key (player_id, app_id),
	foreign key (player_id) references player ON DELETE SET NULL,
	foreign key (app_id) references develop_product ON DELETE SET NULL 
);

grant select on purchase to public;

create table game
(
	app_id varchar(10) primary key,
	genre varchar(20),
	foreign key (app_id) references develop_product ON DELETE CASCADE
);

grant select on game to public;

create table soundtrack
(
	app_id varchar(10) primary key,
	total_length int,
	foreign key (app_id) references develop_product ON DELETE CASCADE
);

grant select on soundtrack to public;

create table create_for_mod
(
	app_id varchar(10),
	mod_id varchar(10),
	player_id varchar(10) not null,
	primary key (app_id, mod_id),
	foreign key (app_id) references develop_product ON DELETE CASCADE,
	foreign key (player_id) references player ON DELETE CASCADE
);

grant select on create_for_mod to public;

create table has_achievement
(
	ach_id varchar(10) primary key,
	app_id varchar(10) not null,
	foreign key (app_id) references develop_product ON DELETE CASCADE
);

grant select on has_achievement to public;

create table attain
(
	player_id varchar(10),
	ach_id varchar(10),
	primary key (player_id, ach_id),
	foreign key (player_id) references player ON DELETE CASCADE,
	foreign key (ach_id) references has_achievement ON DELETE CASCADE
);

grant select on attain to public;

create table review_writereview
(
	review_id varchar(10) primary key,
	rdate char(20),
	recommendation number(1,0),
	player_id varchar(10) not null,
	app_id varchar(10) not null,
	foreign key (player_id) references player ON DELETE SET NULL,
	foreign key (app_id) references develop_product  ON DELETE CASCADE
);

grant select on review_writereview to public;