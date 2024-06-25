const dataPath = "./highscores.json"
const port = 8080;

const express = require('express');
const fs = require('fs');
const app = express();

function writeData() {
    fs.writeFileSync(dataPath, JSON.stringify(data, null, 2));
}

let data;
function readData() {
  data = JSON.parse(fs.readFileSync(dataPath));
}

// serve html file
app.get('/', (req, res) => {
  res.status(200).sendFile(__dirname + '/index.html');
})

// get highscores
app.get('/highscore/1', (req, res) => {
  readData();
  console.log("highscore requested by client " + req.ip);
  res.status(200).send(data["1"].toString());
})

app.get('/highscore/2', (req, res) => {
  readData();
  console.log("highscore requested by client " + req.ip);
  res.status(200).send(data["2"].toString());
})

app.get('/highscore/3', (req, res) => {
  readData();
  console.log("highscore requested by client " + req.ip);
  res.status(200).send(data["3"].toString());
})

app.get('/highscore/4', (req, res) => {
  readData();
  console.log("highscore requested by client " + req.ip);
  res.status(200).send(data["4"].toString());
})

// set highscores
app.get('/setHighscore/1', (req, res) => {
  console.log("highscore set by client " + req.ip);
  if (parseInt(req.query.score) > data["1"]) {
    data["1"] = parseInt(req.query.score);
    writeData();
  }
  res.status(200).send("Highscore set");
})

app.get('/setHighscore/2', (req, res) => {
  console.log("highscore set by client " + req.ip);
  if (parseInt(req.query.score) > data["2"]) {
    data["2"] = parseInt(req.query.score);
    writeData();
  }
  res.status(200).send("Highscore set");
})

app.get('/setHighscore/3', (req, res) => {
  console.log("highscore set by client " + req.ip);
  if (parseInt(req.query.score) > data["3"]) {
    data["3"] = parseInt(req.query.score);
    writeData();
  }
  res.status(200).send("Highscore set");
})

app.get('/setHighscore/4', (req, res) => {
  console.log("highscore set by client " + req.ip);
  if (parseInt(req.query.score) > data["4"]) {
    data["4"] = parseInt(req.query.score);
    writeData();
  }
  res.status(200).send("Highscore set");
})

app.listen(port, () => {
  console.log(`Pong Highscore Server listening on port ${port}`)
})