const config = require('./config.json');
const express =require('express');
const path = require('path');
const exphbs = require('express-handlebars');
const app = express();

app.engine('.hbs', exphbs({
    defaultLayout: 'main',
    extname: '.hbs',
    layoutsDir: path.join(__dirname, 'views/layouts')
}));

app.set('view engine', '.hbs');
app.set('views', path.join(__dirname, 'views'));

// app.use((request,response, next) =>{
//    console.log(request.headers);
//    next();
// });
//
// app.use((request, response, next) =>{
//    request.chance = Math.random();
//    next();
// });
//
app.get('/', (request,response) =>{
   response.render('home',{
       name: 'Roman'
   })
});
//
// app.use((err, request, response, next) => {
//     console.log(err);
//     response.status(500).send('Something broke!');
// });

app.listen(config.port);
