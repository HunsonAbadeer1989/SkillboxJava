drop table if exists grocery_items;

create table grocery_items (
    id bigint not null auto_increment,
    item_name varchar(255),
    description varchar(255),
    primary key(id));

