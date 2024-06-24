const dataPath = "./highscores.json"
const port = 8080;

const express = require('express');
const fs = require('fs');
const data = require(dataPath);
const app = express();

function writeData() {
    fs.writeFileSync(dataPath, JSON.stringify(data, null, 2));
}

app.get('/', (req, res) => {
  res.send('Hello World!')
})

app.listen(port, () => {
  console.log(`Pong Highscore Server listening on port ${port}`)
})