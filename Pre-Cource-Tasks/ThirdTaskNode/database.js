const mysql = require('mysql');
const con = mysql.createConnection({
    host: "localhost",
    user: "prostrmk",
    password: "0",
    database: "kirill"
});

function select(sql) {
    let artists = [];
    con.connect(function (err) {
        if (err) throw err;
        con.query(sql, function (err, result, rows) {
            if (err) console.log(err);
            let length = Object.keys(result).length;
            for (let i = 0; i < length; i++) {
                artists.push({id: result[i].id, name: result[i].name});
            }
        })
    });
    console.log(artists);
    return artists;
}

function insert(sql){
    con.connect(function (err) {
        if (err) throw err;
        con.query(sql, function (err,result) {
            if (err) throw err;
            console.log(sql);
        });
    });
}

function work(){
    let arts = select("SELECT * FROM artist");
    console.log(arts)
}

work();

module.exports.select = select;

class Artist {
    constructor(id, name){
        this.id=id;
        this.name = name
    }
}