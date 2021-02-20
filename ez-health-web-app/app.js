const express = require('express');
const bodyParser = require('body-parser');
const ejs = require('ejs');
const path = require('path');

const app = express()
const port = 3000

app.use(express.static('public'))

// View Engine Setup 
app.set("views", path.join(__dirname)) 
app.set("view engine", "ejs") 

// Body-parser middleware 
app.use(bodyParser.urlencoded({extended:true})) 
app.use(bodyParser.json()) 

app.get('/',(req,res) => {
    res.render('./public/index');
});

app.post('/saveData', (req, res) => { 
    console.log("userID: ", req.body.userID); 
    console.log("First Name: ", req.body.firstName); 
    console.log("Last Name: ", req.body.lastName);

    
}) 

app.listen(port,()  =>{
    console.log('App is listening to port '+ port);
});