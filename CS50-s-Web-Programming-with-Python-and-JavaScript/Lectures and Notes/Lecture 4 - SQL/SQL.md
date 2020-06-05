# Lecture 4 - Database <!-- omit in toc -->

- [SQL](#sql)
  - [Data Types](#data-types)
  - [CREATE TABLE](#create-table)
    - [Constraints while creating table](#constraints-while-creating-table)
  - [INSERT INTO](#insert-into)
  - [SELECT TABLE](#select-table)
    - [Functions while selecting from table](#functions-while-selecting-from-table)
  - [WHERE condition](#where-condition)
    - [IN while using WHERE](#in-while-using-where)
    - [LIKE while using WHERE](#like-while-using-where)
  - [UPDATE TABLE](#update-table)
  - [GROUP BY](#group-by)
  - [Foreign Key](#foreign-key)
    - [Add foreign key while creating table](#add-foreign-key-while-creating-table)
  - [CREATE INDEX](#create-index)
- [Security Issues](#security-issues)
  - [SQL Injection](#sql-injection)
    - [Race conditions](#race-conditions)
- [Connect Python with database](#connect-python-with-database)

## SQL

### Data Types

* integer
* decimal
* serial
  * just like a integer
  * but it will automatically increase
* varchar
* timestamp
* boolean
* enum

### CREATE TABLE

Format

```sql
create table flights(
    id serial primary key,
    origin varchar not null,
    destination varchar not null,
    duration integer not null
);
```

#### Constraints while creating table

* not null
* unique
* primary key
* default
* check

### INSERT INTO

Format

```sql
insert into flights(origin,destination,duration) values ('New York','London',415);
```

### SELECT TABLE

Format

```sql
select * from flights
```

#### Functions while selecting from table

* sum
* count
* min
* max
* avg

### WHERE condition

Format

```sql
select * from flights where origin=''
```

#### IN while using WHERE

Format

```sql
select * from flights where origin in ('NewYork','Japan');
```

#### LIKE while using WHERE

Format

```sql
select * from flights where origin like '%a%';
```

### UPDATE TABLE

Format

```sql
update flights set duration = 430
where origin = 'New York'
and destination = 'London';
```

### GROUP BY

Format

```sql
select count(*) from flight group by origin;
```

### Foreign Key

#### Add foreign key while creating table

Format

```sql
create table passengers(
    id serial primary key,
    name varchar not null,
    flight_id integer references flights
)
```

And you can use ```join``` to select something from two tables

### CREATE INDEX

Especially when the database getting larger and larger.

The cost is, this weill slow down the update or insert operation.

## Security Issues

### SQL Injection

Some website allow user to login, and this may cause some secruity problem.

If we have a normal user input, the backend query may look like this.

```sql
select * from users
where (username = 'alice')
and (password = 1234)
```

But what if I input username as 'alice' but with passwork as ```'1' or '1' = '1'```

```sql
select * from users
where (username = 'alice')
and (password = '1' or '1' = '1')
```

And this may cause problem that the hacker didn't have to input the right password in order to get user information. And this will actually allow user to login without password.

#### Race conditions

The system may do something wrong if there are two people want to do something at the same time

So here we can use SQL Transactions

```sql

begin

...

commit

```

## Connect Python with database

So here is a library that help us to connect python to database

* SQLAlchemy

Example:

```python
import os

# Here we use a database engine
# And this engine will manage the connection between python and database
from sqlalchemy import create_engine
from sqlalchemy.orm import scoped_session,sessionmaker

# This will get the environment variable from system. Just like FLASK_APP
engine = create_engine(os.getenv("DATABASE_URL"))
# This we are creating a scoped session in order to make sure the operation from each person is kept separate from each other.
db = scoped_session(sessionmaker(bind=engine))

def main():
    # By using execute, we can convert string into sql code, and then using fetchall we can get all result.
    flights = db.execute("select origin from flights").fetchall()

    for flight in flights:
        print(f"{flight.origin}")

```

