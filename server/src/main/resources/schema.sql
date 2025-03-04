drop table IF EXISTS users,
                     items,
                     requests,
                     items,
                     bookings,
                     comments;

create TABLE IF NOT EXISTS users (
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name varchar(50) NOT NULL,
    email varchar(50) NOT NULL UNIQUE
);

create TABLE IF NOT EXISTS requests (
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    description varchar(300) NOT NULL,
    requestor_id int NOT NULL REFERENCES users(id) ON delete CASCADE,
    created timestamp without time zone
);

create TABLE IF NOT EXISTS items (
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name varchar(50) NOT NULL,
    description varchar(300) NOT NULL,
    is_available boolean NOT NULL,
    owner_id int NOT NULL REFERENCES users(id) ON delete CASCADE,
    request_id int REFERENCES requests(id)
);

create TABLE IF NOT EXISTS bookings (
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    start_date timestamp without time zone,
    end_date timestamp without time zone,
    item_id int NOT NULL REFERENCES items(id) ON delete CASCADE,
    booker_id int NOT NULL REFERENCES users(id) ON delete CASCADE,
    status varchar(10) NOT NULL
);

create TABLE IF NOT EXISTS comments (
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    text varchar(300) NOT NULL,
    item_id int NOT NULL REFERENCES items(id) ON delete CASCADE,
    author_id int NOT NULL REFERENCES users(id) ON delete CASCADE,
    created timestamp without time zone
);