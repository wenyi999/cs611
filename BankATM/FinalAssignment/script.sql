create table Bank
(
    bid      integer
        constraint Bank_pk
            primary key autoincrement,
    profit   double,
    balance  double,
    currency integer
);

create table Customer
(
    uid       integer
        constraint Customer_pk
            primary key autoincrement,
    name      CHARACTER(50),
    loggingID CHARACTER(50),
    password  CHARACTER(50),
    in_debt   integer,
    loan      double,
    currency  integer
);

create table Account
(
    aid        integer
        constraint Account_pk
            primary key autoincrement,
    uid        integer
        references Customer,
    type       integer,
    balance    double,
    currency   integer,
    start_time Datetime
);

create unique index Customer_loggingID_uindex
    on Customer (loggingID);

create table Manager
(
    Mid       integer
        constraint Manager_pk
            primary key autoincrement,
    Name      CHARACTER(50),
    loggingID CHARACTER(50),
    password  CHARACTER(50)
);

create unique index Manager_loggingID_uindex
    on Manager (loggingID);

create table "Transaction"
(
    Tid      integer
        constraint Transaction_pk
            primary key autoincrement,
    aid1     integer
        references Account,
    aid2     integer
        references Account,
    money    double,
    currency integer,
    time     Datetime
);


