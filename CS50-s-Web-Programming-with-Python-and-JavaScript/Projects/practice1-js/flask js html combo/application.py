from flask import Flask, request, jsonify,render_template
import requests

app = Flask(__name__)

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/convert',methods=['POST'])
def convert():

    currency = request.form.get('currency')
    res = requests.get("https://api.fixer.io/latest", params={
          "base": "USD", "symbols": currency})

    if res.status_code != 200:
        return jsonify({'success':False})

    data = res.json()
    if currency not in data['rates']:
        return jsonify({'success':False})

    return jsonify({"success": True, "rate": data["rates"][currency]})