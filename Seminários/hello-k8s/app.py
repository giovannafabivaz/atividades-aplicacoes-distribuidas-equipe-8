from flask import Flask
import socket

app = Flask(__name__)

@app.route("/")
def hello():
    return f"Olá do pod {socket.gethostname()}!\n"

app.run(host="0.0.0.0", port=5000)
