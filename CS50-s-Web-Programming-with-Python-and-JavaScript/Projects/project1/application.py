import os

from flask import Flask, session, render_template
from flask_session import Session
from sqlalchemy import create_engine
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

# Sign up page
@app.route("/sign_up")
def registration():
    return render_template('sign_up.html')

# Login validation
@app.route("/login")
def login():
    return 'login'

