create sequence hibernate_sequence start 1 increment 1;

    create table t_artists (
       id int8 not null,
        bio varchar(3000),
        created_date timestamp not null,
        name varchar(100) not null,
        primary key (id)
    );

    create table t_customers (
       id int8 not null,
        created_date timestamp not null,
        e_mail varchar(255) not null,
        first_name varchar(50) not null,
        last_name varchar(50) not null,
        primary key (id)
    );

    create table t_items (
       DTYPE varchar(31) not null,
        id int8 not null,
        created_date timestamp not null,
        description varchar(3000),
        price numeric(19, 2) not null,
        title varchar(100) not null,
        genre varchar(100),
        music_company varchar(255),
        isbn varchar(15),
        language varchar(255),
        nb_of_pages int4,
        publication_date date,
        artist_fk int8,
        publisher_fk int8,
        primary key (id)
    );

    create table t_publisher_alt (
       id int8 not null,
        createdDate timestamp,
        name varchar(255),
        primary key (id)
    );

    create table t_publishers (
       id int8 not null,
        created_date timestamp not null,
        name varchar(50) not null,
        primary key (id)
    );

    create table t_purchase_order (
       id int8 not null,
        created_date date not null,
        purchase_order_date date not null,
        customer_fk int8,
        primary key (id)
    );

    create table t_purchase_order_line (
       id int8 not null,
        created_date date not null,
        quantity int4 not null,
        item_fk int8,
        purchase_order_fk int8,
        primary key (id)
    );

    create table t_tracks (
       id int8 not null,
        created_date timestamp not null,
        duration int8 not null,
        title varchar(255) not null,
        cd_fk int8,
        primary key (id)
    );

    alter table if exists t_items 
       add constraint FKr3152tukbog585dik5qwonldg 
       foreign key (artist_fk) 
       references t_artists;

    alter table if exists t_items 
       add constraint FKi6lqpcqfnc4dtsp9w473p5kkj 
       foreign key (publisher_fk) 
       references t_publishers;

    alter table if exists t_purchase_order 
       add constraint FKpivw1s8l9sxlwos0n89gwykcu 
       foreign key (customer_fk) 
       references t_customers;

    alter table if exists t_purchase_order_line 
       add constraint FKk5o0lynwj3vddwn397a24kwqj 
       foreign key (item_fk) 
       references t_items;

    alter table if exists t_purchase_order_line 
       add constraint FKg1x77wlg3bqqpu4o0tjty0qoa 
       foreign key (purchase_order_fk) 
       references t_purchase_order;

    alter table if exists t_tracks 
       add constraint FK23u6r10m0dkp0m8t5hr40ilux 
       foreign key (cd_fk) 
       references t_items;
