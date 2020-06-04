"""
Author: Brickea
Date: 05/27/2020
Description: Project0 for Harvard CS50
"""
# coding:utf-8
from flask import Flask, render_template, request

# app
app = Flask(__name__)
# debug mode
app.debug = True


@app.route("/")
def index():
    return render_template('main_page.html')


@app.route("/travel_nav")
def travel_nav():
    flag = 'travel_nav'
    return render_template('travel_nav.html', flag=flag)


@app.route("/plan_nav", methods=['GET', 'POST'])
def plan_nav():
    flag = 'plan_nav'
    if request.method == 'POST':
        weight = float(request.form.get('weight'))
        height = float(request.form.get('height'))
        age = float(request.form.get('age'))
        gender = int(request.form.get('gender'))
        workout_frequence = int(request.form.get('workout_frequence'))
        body_info = {
            'weight': weight,
            'height': height,
            'age': age,
            'gender': gender,
            'workout_frequence': workout_frequence
        }
        # Calculation
        BMR = 0
        

        if gender == 1:
            # male
            BMR = 13.7*weight + 5*height - 6.8*age + 66
        else:
            # female
            BMR = 9.6*weight + 1.8*height - 4.7*age + 655
        if weight < 0 or height < 0 or age < 0:
            BMR = -1

        advance_BMR = BMR
        if workout_frequence < 1:
            advance_BMR = BMR*1.2
        elif workout_frequence < 4:
            advance_BMR = BMR * 1.375
        elif workout_frequence < 6:
            advance_BMR = BMR * 1.55
        else:
            advance_BMR = BMR * 1.725

        return render_template('plan_nav.html', flag=flag, BMR=BMR, advance_BMR=round(advance_BMR))
    else:
        BMR = 0
        advance_BMR = 0
        return render_template('plan_nav.html', flag=flag, BMR=BMR, advance_BMR=round(advance_BMR))


@app.route("/music_nav")
def music_nav():
    flag = 'music_nav'
    return render_template('music_nav.html', flag=flag)
