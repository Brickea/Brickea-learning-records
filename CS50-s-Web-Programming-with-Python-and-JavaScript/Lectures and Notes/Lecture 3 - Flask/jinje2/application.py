from flask import Flask,render_template
import datetime

app = Flask(__name__)


@app.route("/")
def index():
    now = datetime.datetime.now()
    new_year = now.year == 1 and now.month == 1
    return render_template('new_year.html', new_year=new_year)


@app.route("/for_practice")
def for_practice():
    name = ['A','B','C']
    return render_template('for_practice.html', name=name)

@app.route("/href_link")
def href_link():
    return render_template('page_1.html')

@app.route("/page_2")
def page_2():
    return render_template('page_2.html')