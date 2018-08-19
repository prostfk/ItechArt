const config = require('./config.json');
const express =require('express');
const path = require('path');
const exphbs = require('express-handlebars');
const app = express();
const artists = [
    {
        id: 1,
        name: "bfmv"
    },
    {
        id:2,
        name: "annisokay"
    },
    {
        id:3,
        name: "a day to remember"
    },
    {
        id:4,
        name: "wildways"
    }
];
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

app.get('/artists', (request, response)=>{
   response.send(artists)
});

app.get('/artists/:id', (request,response) =>{
    console.log(request.params.id);
    let artist = artists.find(function (artist) {
       return artist.id === Number(request.params.id)
    });
    console.log(artist);
    response.render('alarm', {
        message: artist.name
    })
});

app.get('/addArtist', (req,resp)=>{

    resp.render('addArtist')

});

app.post('/addArtist', (req,resp)=>{
   let ar = req.params.artist;
   artists.push({
       id:5,
       name: ar.toString()
   });
    resp.send("Success");
});


app.listen(config.port);
