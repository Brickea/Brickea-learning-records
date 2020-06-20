import os

from flask import Flask, session, render_template, request, redirect, url_for
from flask_session import Session
from sqlalchemy import create_engine, text
from sqlalchemy.orm import scoped_session, sessionmaker

app = Flask(__name__)

# Check for environment variable
if not os.getenv("DATABASE_URL"):
    raise RuntimeError("DATABASE_URL is not set")


# Configure session to use filesystem
app.config["SESSION_PERMANENT"] = False
# If set to True the session lives for permanent_session_lifetime seconds.
# The default is 31 days. If set to False (which is the default) the session will be deleted when the user closes the browser.
app.config["SESSION_TYPE"] = "filesystem"
# This will store the session information into the local file
Session(app)

# Set up database
engine = create_engine(os.getenv("DATABASE_URL"))
db = scoped_session(sessionmaker(bind=engine))

# Sign in page
@app.route("/")
def index():
    return render_template('sign_in.html')

# Sign up page & Insert new user into database
@app.route("/registration", methods=['POST', 'GET'])
def registration():
    if request.method == "POST":
        # Post will allow backend to insert a new user into database
        username = request.form.get("username")
        password = request.form.get("password")
        # Check if the username is exist
        sql = text('''select * from users where username = :username
        ''')
        para = {"username":username}
        user = db.execute(sql,para).fetchone()
        if user is None:
            # The username is not exist, create one
            sql = text('''insert into users (username, password)
                values (:username,:password);
                ''')
            para = {"username": username, "password": password}
            db.execute(sql, para)
            db.commit()
            return render_template('message_redirect_sign_in.html',message="Success!")
        else:
            # The username is exist, redirect to sign in page
            return render_template('message_redirect_sign_in.html',message="User exist!")
    elif request.method == "GET":
        # This will let the backend render the sign up page into front
        return render_template('sign_up.html')
    return "Bad request", 400


# Login validation
@app.route("/login", methods=["POST"])
def login():
    # if request.method == "GET":
    #     pass
    if request.method == "POST":
        # This will search a particular user from the database 
        # and login into the webpage
        username = request.form.get("username")
        password = request.form.get("password")
        
        sql = text('''select * from users where username = :username
        ''')
        para = {"username":username}
        user = db.execute(sql,para).fetchone()
        if user is None:
            # No such user
            return render_template("message_redirect_sign_in.html",message="no such user!")
        else:
            # Check if the password is right
            if password == user.password:
                # Add current user into session
                session['username'] = username
                return redirect('/search')
            else:
                return render_template("message_redirect_sign_in.html",message="Wrong password!")
    return 'Bad request', 400

# Logout
@app.route("/logout")
def logout():
    session.pop("username")
    return render_template("message_redirect_sign_in.html",message="Logout!")

# Book search
@app.route("/search")
def search():
    if "username" in session:
        # The user already login
        return render_template("search.html")
    else:
        # No user login
        return render_template("message_redirect_sign_in.html",message="No user login!")
    return 'Bad request', 400
