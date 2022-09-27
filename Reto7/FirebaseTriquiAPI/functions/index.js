const functions = require("firebase-functions");
const admin = require('firebase-admin');
const express = require('express');
const app = express();

var serviceAccount = require("./credentials.json");
admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  databaseURL: 'https://fb-triqui-api.firebaseio.com'
});

const db = admin.firestore();

app.get('/hello-world', (req, res) => {
    return res.status(200).json({message: 'Hello World'});
});

app.post('/api/games/:player_id', (req, res) => {
    (async () => {
      try{
        const doc = db.collection("players").doc(req.params.player_id);
        const item = await doc.get();
        const playerOneId = item.id;
        await db.collection("games")
        .doc("/"+ req.body.id + "/")
        .create({board: [' ',' ',' ',' ',' ',' ',' ',' ',' '],playerOneId: parseInt(playerOneId), playerTwoId: null, actualTurn:parseInt(playerOneId)});
        return res.status(204).json();
      }catch(error){
        return res.status(500).send(error);
      }
    })();
  });
  
  app.post('/api/players', async (req, res) => {
      await db.collection("players")
      .doc("/"+ req.body.id + "/")
      .create({name: req.body.name, wins: 0, losses: 0, ties: 0});
      return res.status(204).json();
  });
  
  app.put('/api/joinGame/:game_id/:player_id',(req, res) => {
    (async () => {
      try{
        const playerdoc = db.collection("players").doc(req.params.player_id);
        const playeritem = await playerdoc.get();
        const playerId = playeritem.id;
        const doc = db.collection("games").doc(req.params.game_id);
        await doc.update({
          playerTwoId: parseInt(playerId)
        });
        return res.status(204).json();
      }catch(error){
        return res.status(500).send(error);
      }
    })();
  })
  
  app.put('/api/doMove/:game_id/:player_id',(req, res) => {
    (async () => {
      try{
        const playerdoc = db.collection("players").doc(req.params.player_id);
        const playerItem = await playerdoc.get();
        const playerId = playerItem.id;
        const gameDoc = db.collection("games").doc(req.params.game_id);
        const gameItem = await gameDoc.get();
        const pos = req.body.pos;
        let webGame = gameItem.data();
        if(webGame.playerOneId === parseInt(playerId) && webGame.board[pos] === ' ' && webGame.actualTurn === webGame.playerOneId){
          webGame.board[pos] = 'X';
          webGame.actualTurn = webGame.playerTwoId;
        }else if(webGame.playerTwoId === parseInt(playerId) && webGame.board[pos] === ' ' && webGame.actualTurn === webGame.playerTwoId){
          webGame.board[pos] = 'O';
          webGame.actualTurn = webGame.playerOneId;
        }
        await gameDoc.update({
          board: webGame.board,
          actualTurn: webGame.actualTurn
        });
        return res.status(204).json();
      }catch(error){
        return res.status(500).send(error);
      }
    })();
  })
  
  app.get('/api/getBoard/:game_id', (req, res) => {
    (async() =>{
      try{
        const gameDoc = db.collection("games").doc(req.params.game_id);
        const gameItem = await gameDoc.get();
        let webGame = gameItem.data();
        return res.status(200).json(webGame.board);
      }catch(error){
        return res.status(500).send(error);
      }
    })();
  });
  
  app.get('/api/getAvailableGames/', async (req, res) => {
    const query = db.collection('games');
    const querySnapshot = await query.get();
    const docs = querySnapshot.docs;
    const response = docs.map((doc) => {
      if(doc.data().playerTwoId == null){
        return ({id: doc.id, actualTurn: doc.data().actualTurn});
      }else{
        return ;
      }
    }).filter(val => val != null);
    return res.status(200).json(response);
  });

exports.app = functions.https.onRequest(app);