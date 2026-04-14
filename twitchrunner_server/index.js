const express = require('express')
const app = express()
const fs = require('fs')
const port = 7070

const authorizations = JSON.parse(fs.readFileSync('auth.json','utf8'))
console.log(authorizations)
const authorization = authorizations.authorization
const clientid = authorizations.clientid

app.use(express.json());
app.get('/getstream',async (req,res)=>{
    const id = req.body.id
    if(id){
        const getstream = await fetch(`https://api.twitch.tv/helix/streams?user_id=${id}`,{
            method:'GET',
            headers:{
                'Authorization':authorization,
                'Client-ID':clientid
            }
        })
        const result = await getstream.json();
        const data = result.data[0]
        if(data!=null){
            res.json({
                'stream':true
            })
        }else{
            res.json({
                'stream':false
            })
        }
    }else{
        res.json({
            'id':false
        })
    }
})
app.get('/getid',async (req,res)=>{
    const name = req.body.name
    if(name){
        const getid = await fetch(`https://api.twitch.tv/helix/users?login=${name}`,{
            method:'GET',
            headers:{
                'Authorization':authorization,
                'Client-Id':clientid
            }
        })
        const result = await getid.json();
        const id = result.data[0].id
        res.json({
            'id':id
        })
    }else{
        res.json({
            'name':false
        })
    }
})
app.listen(port,() => {
    console.log(`TwitchRunner start runninng at port:${port}`);
})