const config = require('./config.json');
const express =require('express');
const path = require('path');
const exphbs = require('express-handlebars');
const app = express();
const database = require('./database');

app.engine('.hbs', exphbs({
    defaultLayout: 'main',
    extname: '.hbs',
    layoutsDir: path.join(__dirname, 'views/layouts')
}));

app.use(express.static(path.join(__dirname, 'public')));


app.set('view engine', '.hbs');
app.set('views', path.join(__dirname, 'views'));

app.get('/', (request,response) =>{
   response.render('home',{
       name: 'Roman'
   })
});

app.get('/alarms',(request, response) =>{
   response.render('alarm',{
       message: 'hello '
   })
});


app.get('/addArtist', (req,resp)=>{

    resp.render('addArtist')

});

// app.post('/addArtist', (req,resp)=>{
//    let ar = req.params.artist;
//    database.select("INSERT INTO artist(name) VALUES('" + ar + "')");
//     resp.send("Success");
// });

app.get('/task', (req,resp)=>{
   resp.render('index');
});

app.listen(config.port);
