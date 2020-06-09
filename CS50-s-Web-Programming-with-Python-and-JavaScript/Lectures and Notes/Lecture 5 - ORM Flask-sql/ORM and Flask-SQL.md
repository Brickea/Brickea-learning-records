# Object-relational Mapping and Flask SQLAlchemy <!-- omit in toc -->

- [Flask SQLAlchemy](#flask-sqlalchemy)
  - [Flask SQLAlchemy insertion](#flask-sqlalchemy-insertion)
  - [Flask SQLAlchemy selection](#flask-sqlalchemy-selection)
  - [Flask SQLAlchemy update](#flask-sqlalchemy-update)
  - [Flask SQLAlchemy delete](#flask-sqlalchemy-delete)
  - [Flask SQLAlchemy order by](#flask-sqlalchemy-order-by)
  - [More conditions in Flask SQLAlchemy](#more-conditions-in-flask-sqlalchemy)
  - [Flask SQLAlchemy join](#flask-sqlalchemy-join)
- [JSON (JavaScript Object Notation )](#json-javascript-object-notation-)
  - [HTTP Methods](#http-methods)
  - [Request library](#request-library)
  - [API Route](#api-route)
  - [API Key](#api-key)

## Flask SQLAlchemy

How to relate the python object with a table in the database?

In the ```models.py```

```python
from flask_sqlalchemy import SQLAlchemy

db = SQLAlchemy()

class Flight(db.Model):
    __tablename__ = "flights" # This should as the same as the table name
    id = db.Column(db.Integer, primary_key=True)
    origin = db.Column(db.String, nullable=False)
    destination = db.Column(db.String, nullable=False)
    duration = db.Column(db.Integer,  nullable=False)
```

In the ```create.py```

```python
from flask import Flask, render_template, request
from models import *

app = Flask(__name__)
app.config[""SQLALCHEMY_DATABASE_URI] = os.getenv("DATABASE_RUL")
app.config["SQLALCHEMY_TRACK_MODIFICATIONS"] = False
db.init_app(app) # This mean tie this database with this flask application

def main():
    db.create_all() # Create all table in the models.py

if __name__ == "__main__":
    with app.app_context():
        main()


```

### Flask SQLAlchemy insertion

```python
flight = Flight(origin="New York", destination="Paris", duration=540)

db.session.add(flight)

db.session.commit()
```

### Flask SQLAlchemy selection

```python

Flight.query.filter_by(origin="Paris").all()

Flight.query.filter_by(origin="Paris").first()
```

### Flask SQLAlchemy update

```python
flight = Flight.query.get(id=6)
flight.duration = 280
```

### Flask SQLAlchemy delete

```python
flight = Flight.query.get(id=28)
db.session.delete(flight)
```

### Flask SQLAlchemy order by

```python
Flight.query.order_by(Flight.origin.desc()).all()
```

### More conditions in Flask SQLAlchemy

```python
Flight.query.filter(Flight.origin != "Paris").all()

Flight.query.filter(Flight.origin.like("%a%")).all()

Flight.query.filter(Flight.origin.in_(["Tokyo","Paris"])).all()

Flight.query.filter(or_(Flight.origin=="Paris", Flight.duration>500)).all()

```

### Flask SQLAlchemy join

```python
db.session.query(Flight, Passenger).filter(Flight.id==Passenger.flight_id).all()
```

## JSON (JavaScript Object Notation )

### HTTP Methods

* GET: retrieve resource
* POST: create a new resource
* PUT: replace a resource
* PATCH: update a resource
* DELETE: delete a resource

### Request library

```python
request.get(url)
request.post(url)
request.put(url)
request.patch(url)
request.delete(url)

import requests

def main():
    res = requests.get("")
    if res.status_code != 200:
        raise Exception("error")
    data = res.json()
    print(data)

if __name__ == "__main__":
    main()

```

**Status Codes**
* 200: ok
* 201: Created
* 400: Bad Request
* 403: Forbidden
* 404: Not Found
* 405: Method Not Allowed
* 422: Unprocessable Entity

### API Route

```python

app.route("api/flights/<int:flight_id>")
def flight_api(flight_id):
    # make sure the flight exists
    flight = Flight.query.get(flight_id)
    if flight is None:
        return jsonify("error":"Invalid flight_id"}) , 422

    # Get all passengers
    passengers = flight.passengers
    names = []
    for passenger in passengers:
        names.append(passenger)
    return jsonify({
        "origin":flight.origin,
        ...

    }) # if there is no any status code, it will return 200 by default.
```

### API Key

* Sometime you don't want people to access your api too often and you can add a very long string ahead to your api
* And you can also limit the time per hour that one person can access to your api.