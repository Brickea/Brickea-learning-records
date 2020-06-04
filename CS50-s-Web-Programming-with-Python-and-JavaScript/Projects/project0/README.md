# Project 0

![](https://img.shields.io/badge/Author-Brickea-blue)

Web Programming with Python and JavaScript

This is a flask webpage practice while I studying the CS50

If there is any problem with running this website project. Please feel free to connect me ```brickeawang@gmail.com```

## How to run it

* Step1: install ```flask```
  * Make sure ```FLASK_APP=application.py```
* Step2: go to ```project0``` root file path.
  * Run ```flask run```
  * check ```http://127.0.0.1:5000/```
* Step3: enjoy

## Project structure

The following introduction starts from root filepath.

* ```application.py``` contains all flask configuration
* ```static``` contains all static files that the webpage needs. The reason why I use this name is because of the flask requirement. Read [flask document](https://flask.palletsprojects.com/en/1.1.x/tutorial/static/) to get more information
  * ```scss``` I use one scss file to customize the art style of my page. And I use ```live scss complier``` vscode component to convert the scss into css.
  * ```css``` this folder will contain the css file that from scss file
  * ```img``` this folder contains all images that the web needs
  * ```media``` this folder contains all media files (audio, video, etc) that the web needs
* ```templates``` this folder will contain all html pages of webpage.
  * ```layout``` this folder contains layout HTML pages by using Jinja2

## Attention

Responsive CSS is not perfect. So while using mobile devices to check this project page may cause unexpect display result