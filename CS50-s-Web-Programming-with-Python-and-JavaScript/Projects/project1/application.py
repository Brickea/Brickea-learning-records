import os

from flask import Flask, session, render_template, request, redirect, url_for
from flask_session import Session
from sqlalchemy import create_engine, text
from sqlalchemy.orm import scoped_session, sessionmaker
import json

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
app.config['SECRET_KEY'] = "brickeawang"  # 设置session加密的密钥
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
        para = {"username": username}
        user = db.execute(sql, para).fetchone()
        if user is None:
            # The username is not exist, create one
            sql = text('''insert into users (username, password)
                values (:username,:password);
                ''')
            para = {"username": username, "password": password}
            db.execute(sql, para)
            db.commit()
            return render_template('message_redirect.html', message="Success!", goto_page="sign_in")
        else:
            # The username is exist, redirect to sign in page
            return render_template('message_redirect.html', message="User exist!", goto_page="sign_in")
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
        para = {"username": username}
        user = db.execute(sql, para).fetchone()
        if user is None:
            # No such user
            return render_template("message_redirect.html", message="no such user!", goto_page="sign_in")
        else:
            # Check if the password is right
            if password == user.password:
                # Add current user into session
                session['username'] = username
                return redirect('/search')
            else:
                return render_template("message_redirect.html", message="Wrong password!", goto_page="sign_in")
    return 'Bad request', 400

# Logout
@app.route("/logout")
def logout():
    session.pop("username")
    return render_template("message_redirect.html", message="Logout!", goto_page="sign_in")

# Book search
@app.route("/search")
def search():
    if "username" in session:
        # The user already login
        return render_template("search.html")
    else:
        # No user login
        return render_template("message_redirect.html", message="No user login!", goto_page="sign_in")
    return 'Bad request', 400


@app.route("/search_for_book", methods=["POST","GET"])
def search_for_book():
    # search_option
    # 1: isbn
    # 2: title
    # 3: author
    if request.method == "POST":
        user_input = request.form.get("user_input")
        search_class = request.form.get("search_option")
        res = []
        if(search_class == "1"):
            # isbn search
            sql = text("select * from books where isbn = :user_input")
            para = {"user_input": user_input}
            res = db.execute(sql, para).fetchall()
            if len(res) == 0:
                # No such book!
                return render_template("message_redirect.html", message="No such book found!", goto_page="search")
            
        elif(search_class == "2"):
            # title search
            sql = text(
                "select * from books where lower(title) like '%%%s%%'" % user_input)
            para = {"user_input": user_input}
            res = db.execute(sql, para).fetchall()
            if len(res) == 0:
                # No such book!
                return render_template("message_redirect.html", message="No such book found!", goto_page="search")
            
        elif(search_class == "3"):
            # author search
            sql = text(
                "select * from books where lower(author) like '%%%s%%'" % user_input)
            para = {"user_input": user_input}
            res = db.execute(sql, para).fetchall()
            if len(res) == 0:
                # No such book!
                return render_template("message_redirect.html", message="No such book found!", goto_page="search")
        
    elif request.method == "GET":
        print("get session")
        print(session)
        if "search_result" not in session:
            return render_template("message_redirect.html", message="No book search result found!", goto_page="search")
        res = session["search_result"]
    
    session['test'] = 'test'
    print("session")
    print(session)
    return render_template("book_list.html", res=res)


# Book review
@app.route("/review/<review_isbn>", methods=["GET"])
def review(review_isbn):
    print(session)
    if request.method == "GET": 
        if "username" in session:
            # The user already login
            username = session["username"]
            # Check if the user already reivewed this book
            sql = text('select * from reviews \
            where username = :username and isbn = :review_isbn')
            para = {"username":username,"review_isbn":review_isbn}
            res = db.execute(sql,para).fetchone()
            if res is None:
                return render_template("review.html",review_isbn=review_isbn)
            else:
                return render_template("message_redirect.html", message="You have reviewed this book!", goto_page="search_for_book")
        else:
            # No user login
            return render_template("message_redirect.html", message="No user login!", goto_page="sign_in")
    return 'Bad request', 400

@app.route("/submit_review/<review_isbn>",methods=["POST"])
def submit_review(review_isbn):
    if request.method == "POST":
        comment = request.form.get("comment")
        star = request.form.get("review_rate")
        # Submit the review
        sql = text("insert into reviews (username,isbn,comment,star) \
        values (:username,:isbn,:comment,:star)")
        para = {
            "username":session["username"],
            "isbn":review_isbn,
            "comment": comment,
            "star":star
        }
        res = db.execute(sql,para)
        db.commit()
        return render_template("message_redirect.html", message="Success review!", goto_page="search_for_book")
    return 'Bad request', 400